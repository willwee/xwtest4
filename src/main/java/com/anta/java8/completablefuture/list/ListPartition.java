package com.anta.java8.completablefuture.list;

import com.google.common.collect.Lists;
import com.sun.scenario.effect.ImageData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 功能描述
 *
 * @author xiaowei
 * @date 2020-05-03
 **/
public class ListPartition {


//      parts.stream().forEach(list -> {
//        process(list);
//    });

    public static void main(String[] args) {
        //使用guava对list进行分割
        List<String> users = Arrays.asList("01", "02", "03", "04", "05", "06","07");
        //按每50个一组分割
        List<List<String>> parts = Lists.partition(users, 3);

        parts.parallelStream().forEach(System.out::println);

        parts.parallelStream().forEach(l->l.parallelStream().forEach(v->{
            CompletableFuture<String> s = CompletableFuture.supplyAsync(() -> timeOutPrintStr("hello "));
            CompletableFuture<String> s2 = CompletableFuture.supplyAsync(() -> timeOutPrintStr("world"));

            CompletableFuture.allOf(s, s2);

            s.exceptionally(e -> {
                e.printStackTrace();
                return null;
            });
        }));
    }

    private static String timeOutPrintStr(String s) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":::" + s);
        return s;
    }

    private static String printJoinStr(String s) {
        System.out.println(Thread.currentThread().getName() + ":::" + s);
        return s;
    }


    @Test
    public void testAllOf2() {
        // 将异步方法变成同步方法执行
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");

        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");


        CompletableFuture.allOf(future1, future2, future3)
                .thenApply((v) -> Stream.of(future1, future2, future3)
                        .map(CompletableFuture::join)
                        .collect(Collectors.joining(" ")))
                .thenAccept(System.out::println);

    }

    // 关于parallelStream并发安全的思考
    @Test
    public void testParallelStream() {

    }

    /**
     * JAVA8 处理 其中一个失败就全部失败  参考
     * # allOf 如果其中一个失败了如何快速结束所有？
     * 默认情况下，allOf 会等待所有的任务都完成，即使其中有一个失败了，也不会影响其他任务继续执行。但是大部分情况下，一个任务的失败，往往意味着整个任务的失败
     * ，继续执行完剩余的任务意义并不大。在 谷歌的 Guava 的 allAsList 如果其中某个任务失败整个任务就会取消执行:
     * 一种做法就是对 allOf 数组中的每个 CompletableFuture 的 exceptionally 方法进行捕获处理：如果有异常，那么整个 allOf 就直接抛出那个异常:
     * http://arganzheng.life/writing-asynchronous-code-with-completablefuture.html
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void testAllOfException() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("-- future1 -->");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("<-- future1 --");
            return "Hello";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("-- future2 -->");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("<-- future2 --");
            throw new RuntimeException("Oops!");
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("-- future3 -->");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("<-- future3 --");
            return "world";
        });

        // CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
        // combinedFuture.join();

        // 其中一个处理失败 就全部失败
        CompletableFuture<Void> allWithFailFast = CompletableFuture.allOf(future1, future2, future3);
        Stream.of(future1, future2, future3).forEach(f -> f.exceptionally(e -> {
            allWithFailFast.completeExceptionally(e);
            return null;
        }));

        allWithFailFast.join();

    }

    // 超时案例start
    // java8中超时需要自己来处理  java9以后 可以直接有超时的参数设置
    //ava 8 的 CompletableFuture 并没有 timeout 机制，虽然可以在 get 的时候指定 timeout，但是我们知道get 是一个同步堵塞的操作
    // 。怎样让 timeout 也是异步的呢？Java 8 内有内建的机制支持，一般的实现方案是启动一个 ScheduledThreadpoolExecutor 线程在 timeout
    // 时间后直接调用 futurn.completeExceptionally(new TimeoutException())，然后用 acceptEither() 或者 applyToEither 看是先计算完成还是先超时：

    static final ScheduledThreadPoolExecutor delayer2;
    static {
        (delayer2 = new ScheduledThreadPoolExecutor(
                1, new Delayer.DaemonThreadFactory())).
                setRemoveOnCancelPolicy(true);
    }

    @Test
    public void testTimeOut(){
        CompletableFuture.supplyAsync(() -> timeOutPrintStr("LDN - NYC"))
                .acceptEither(timeoutAfter(1, TimeUnit.SECONDS),
                        amount -> System.out.println("The price is: " + amount + "GBP"));
    }

    public <T> CompletableFuture<T> timeoutAfter(long timeout, TimeUnit unit) {
        CompletableFuture<T> result = new CompletableFuture<T>();
        delayer2.schedule(() -> result.completeExceptionally(new TimeoutException()), timeout, unit);
        return result;
    }

    /** * Singleton delay scheduler, used only for starting and * cancelling tasks. */
    static final class Delayer {
        static ScheduledFuture<?> delay(Runnable command, long delay,
                                        TimeUnit unit) {
            return delayer.schedule(command, delay, unit);
        }

        static final class DaemonThreadFactory implements ThreadFactory {
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName("CompletableFutureDelayScheduler");
                return t;
            }
        }

        static final ScheduledThreadPoolExecutor delayer;
        static {
            (delayer = new ScheduledThreadPoolExecutor(
                    1, new DaemonThreadFactory())).
                    setRemoveOnCancelPolicy(true);
        }
    }

    // 超时案例 end

    /**
     * 测试List里面装在异步对象测试
     */
    @Test
    public void testListCompletableFuture() {
        List<String> printInfos = new ArrayList<>();
        printInfos.add("hello");
        printInfos.add("world");
        CompletableFuture[] cfs = printInfos.stream()
                .map(ii -> CompletableFuture.runAsync(
                        () -> printStr(ii)))
                .toArray(CompletableFuture[]::new);
            CompletableFuture.allOf(cfs).join();// 这个就是要等待所有的cfs异步任务处理结束后，才会继续往下执行。有阻塞。如有必要需要考虑超时问题
//            System.out.println("合并后的样子::"+CompletableFuture.allOf(cfs).get());  //无法获取值
//            CompletableFuture.allOf(cfs).get().join(); // 无法处理

        Arrays.stream(cfs).map(cf-> {
            try {
                return cf.get(); // null 应该是CompletableFuture.allOf 后 异步任务都已经结束了.
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }).forEach(System.out::println);
    }

    public String printStr(String str) {
        System.out.println(Thread.currentThread().getName() + ":::" + str);
        return str;
    }
}
