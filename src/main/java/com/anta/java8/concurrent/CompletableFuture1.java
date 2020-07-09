package com.anta.java8.concurrent;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述
 *
 * @author xiaowei
 * @date 2020/5/26 14:04
 **/
public class CompletableFuture1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();

        future.complete("42");

        future
                .thenAccept(System.out::println)
                .thenAccept(v -> System.out.println("done"));

    }

    /**
     * 上面的代码,我们当然可以先调用future.join()先得到任务A的返回值,然后再拿返回值做入参去执行任务B,
     * 而thenApply的存在就在于帮我简化了这一步,我们不必因为等待一个计算完成而一直阻塞着调用线程，
     * 而是告诉CompletableFuture你啥时候执行完就啥时候进行下一步. 就把多个任务串联起来了.
     */
    @Test
    public void test2() {

        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> "hello");
        // 将第一个结果作为第二个异步任务的参数
        CompletableFuture<String> futureB = futureA.thenApply(s -> s + " world");

        CompletableFuture<String> future3 = futureB.thenApply(String::toUpperCase);
        // 合并3个结果集
        System.out.println(future3.join());
    }

    /**
     * 功能:结合两个CompletionStage的结果，进行转化后返回
     * 场景:需要根据商品id查询商品的当前价格,分两步,查询商品的原始价格和折扣,这两个查询相互独立,
     * 当都查出来的时候用原始价格乘折扣,算出当前价格. 使用方法:thenCombine(..)
     */
    @Test
    public void test3(){
        CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(() -> 100d);
        CompletableFuture<Double> futureDiscount = CompletableFuture.supplyAsync(() -> 0.8);
        CompletableFuture<Double> futureResult = futurePrice.thenCombine(futureDiscount, (price, discount) -> price * discount);
        //最终价格为:80.0
        System.out.println("最终价格为:" + futureResult.join());
    }


    /**
     * 功能:执行两个CompletionStage的结果,那个先执行完了,就是用哪个的返回值进行下一步操作
     * 场景:假设查询商品a,有两种方式,A和B,但是A和B的执行速度不一样,我们希望哪个先返回就用那个的返回值.
     */
    @Test
    public void test4(){
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "通过方式A获取商品a";
        });
        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "通过方式B获取商品a";
        });
        //结果:通过方式A获取商品a
        CompletableFuture<String> futureC = futureA.applyToEither(futureB, product -> "结果:" + product);
        System.out.println(futureC.join());
    }

    /**
     *  功能:当运行出现异常时,调用该方法可进行一些补偿操作,如设置默认值.
     *
     * 　场景:异步执行任务A获取结果,如果任务A执行过程中抛出异常,则使用默认值100返回.
     *  上面代码展示了正常流程和出现异常的情况,可以理解成catch,根据返回值可以体会下.
     */
    @Test
    public void test5(){
        CompletableFuture<String> futureA = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "futureA result:" + s)
                .exceptionally(e -> {
                    System.out.println(e.getMessage()); //java.lang.ArithmeticException: / by zero
                    return "futureA result: 100";
                });
        CompletableFuture<String> futureB = CompletableFuture.
                supplyAsync(() -> "执行结果:" + 50)
                .thenApply(s -> "futureB result:" + s)
                .exceptionally(e -> "futureB result: 100");
        //futureA result: 100
        System.out.println(futureA.join());
        //futureB result:执行结果:50
        System.out.println(futureB.join());
    }

    /**
     * allOf:当所有的CompletableFuture都执行完后执行计算
     *
     * anyOf:最快的那个CompletableFuture执行完之后执行计算
     *
     * 场景二:查询一个商品详情,需要分别去查商品信息,卖家信息,库存信息,订单信息等,这些查询相互独立,在不同的服务上,假设每个查询都需要一到两秒钟,要求总体查询时间小于2秒.
     *
     */
    @Test
    public void test6(){
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        long start = System.currentTimeMillis();
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "商品详情";
        },executorService);

        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "卖家信息";
        },executorService);

        CompletableFuture<String> futureC = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "库存信息";
        },executorService);

        CompletableFuture<String> futureD = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + 15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "订单信息";
        },executorService);

        CompletableFuture<Void> allFuture = CompletableFuture.allOf(futureA, futureB, futureC, futureD);
        allFuture.join();

        System.out.println(futureA.join() + futureB.join() + futureC.join() + futureD.join());
        System.out.println("allOf总耗时:" + (System.currentTimeMillis() - start));

    }

    @Test
    public void test7(){
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        long start = System.currentTimeMillis();
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "商品详情";
        },executorService);

        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "卖家信息";
        },executorService);

        CompletableFuture<String> futureC = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "库存信息";
        },executorService);

        CompletableFuture<String> futureD = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + 15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "订单信息";
        },executorService);

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(futureA,futureB,futureC,futureD);
        System.out.println(anyOfFuture.join());
        System.out.println("anyOf总耗时:" + (System.currentTimeMillis() - start));
    }

}
