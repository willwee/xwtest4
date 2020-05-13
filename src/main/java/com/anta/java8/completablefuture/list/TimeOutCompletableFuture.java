package com.anta.java8.completablefuture.list;

import ch.qos.logback.core.util.TimeUtil;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * CompletableFuture 超时模拟测试
 *
 * @author xiaowei
 * @date 2020-05-08
 **/
public class TimeOutCompletableFuture {


    @Test
    public void testTimeOut() {

        CompletableFuture<String> say = CompletableFuture.supplyAsync(()->{
            try {
                return sayHello("yy");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });


        try {
            say.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("你超时了。");
            e.printStackTrace();
        }

        say.exceptionally(e ->{

            System.out.println("异常是被捕获的.");
            e.printStackTrace();

            return "";

        });
    }

    private String sayHello(String name) throws InterruptedException {

        Thread.sleep(10000);
        return "hello " + name;

    }

}
