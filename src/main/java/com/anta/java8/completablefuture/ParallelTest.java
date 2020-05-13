package com.anta.java8.completablefuture;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * 并行获取各个数据源的数据合并成一个数据组合
 *
 * @author xiaowei
 * @date 2020-04-29
 **/
public class ParallelTest {
    /**
     * 获取基本信息
     *
     * @return
     */
    public String getProductBaseInfo(String productId) throws InterruptedException {
        Thread.sleep(60000);//超时测试，如果没有设置处理时限，所有的请求都会阻塞在这里
        return productId + "商品基础信息";
    }

    /**
     * 获取详情信息
     *
     * @return
     */
    public String getProductDetailInfo(String productId) {
        System.out.println("getProductDetailInfo");
        return productId + "商品详情信息";
    }

    /**
     * 获取sku信息
     *
     * @return
     */
    public String getProductSkuInfo(String productId) {
        System.out.println("getProductSkuInfo");
        return productId + "商品sku信息";
    }

    /**
     * 取得一个商品的所有信息（基础、详情、sku）
     *
     * @param productId
     * @return
     */
    public String getAllInfoByProductId(String productId) {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                return getProductBaseInfo(productId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> getProductDetailInfo(productId));
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> getProductSkuInfo(productId));

        //等待三个数据源都返回后，再组装数据。这里会有一个线程阻塞
        CompletableFuture.allOf(f1, f2, f3).join();
        try {
            String ss = f1.get(1, TimeUnit.SECONDS); //超时测试
            String baseInfo = f1.get();
            String detailInfo = f2.get();
            String skuInfo = f3.get();
            return baseInfo + "" + detailInfo + skuInfo;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *   并行获取数据的计算
     */
    @Test
    public void testGetAllInfoParalleByProductId() throws ExecutionException, InterruptedException {
        ParallelTest test = new ParallelTest();
        String info = test.getAllInfoByProductId("1111");
        Assert.assertNotNull(info);
    }
    /**
     *   同步获取执行的处理
     */
    @Test
    public void testGetAllInfoDirectly() throws ExecutionException, InterruptedException {
        ParallelTest test = new ParallelTest();
        String info1 = getProductBaseInfo("1111");
        String info2 = getProductDetailInfo("1111");
        String info3 = getProductSkuInfo("1111");
        String info=info1 + "" + info2 + info3;
        Assert.assertNotNull(info);
    }

    @Test
    public void testAsyn(){

//        List<CompletableFuture<List<AutoRefundExport>>> collect = conditions.stream().map(c -> CompletableFuture.supplyAsync(() -> {
//            AutoRefund[] autoRefunds = autoRefundService.queryPage(token,c.getSelector(),c.getPageSize(),c.getPageIndex());
//            List<AutoRefundExport> autoRefundExports = new ArrayList<>();
//            if(autoRefunds != null){
//                Arrays.stream(autoRefunds).forEach(autoRefund -> {
//                    AutoRefundExport autoRefundExport = new AutoRefundExport();
//                    beanCopier.copy(autoRefund,autoRefundExport,null);
//                    autoRefundExports.add(autoRefundExport);
//                });
//            }
//            return autoRefundExports;
//        }, executor)).collect(Collectors.toList());
//
//        List<List<AutoRefundExport>> results = collect.stream().map(CompletableFuture::join).collect(Collectors.toList());
//
//        for (List<AutoRefundExport> result : results) {
//            dataResult.addAll(result);
//        }

        // 可以模拟上面的代码 做下测试

//        List<String> conditions = Arrays.asList("1","2","3");
//        List<CompletableFuture<List<String>>> collect = conditions.stream().map(c -> CompletableFuture.supplyAsync((c) -> {
//
//            return getStringTest(c);
//        })).collect(Collectors.toList());
//
//        List<List<String>> results = collect.stream().map(CompletableFuture::join).collect(Collectors.toList());
//
//        for (List<String> result : results) {
//            System.out.println(result);
//        }
    }

    /**
     * 获取sku信息
     *
     * @return
     */
    public String getStringTest(String uu) {
        System.out.println("getStringTest");
        return uu ;
    }

    public void testParallelThreads() {
        List<String> ss = Arrays.asList("001","002","003");
        ss.parallelStream();
    }
}

