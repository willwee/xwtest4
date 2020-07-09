package com.anta.java8.string;

import com.anta.java8.IWTest;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiaowei
 * @date 2020/5/25 9:12
 */
public class StringTest implements IWTest {

    @Test
    public void test1() {
        String onlineFlag = "线下积分1";
        if(onlineFlag.equals("线下积分")){
            System.out.println("you pass:");
        }
    }

    @Test
    public void test2(){
        String orderFromStr = "普通订单,线上积分,线下积分,内购订单,导购订单,";
        List<String> orderFromList = Arrays.asList(orderFromStr.split(","));
        System.out.println("转换后的大小："+orderFromList.size());
        if (orderFromList != null && !orderFromList.isEmpty()) {
            orderFromList.stream().forEach(System.out::println);
        }
    }

    /**
     * 将空值 装入list 相当于list.add("");  然后mapper sql拼接的时候会拼入
     */
    @Test
    public void test3(){
        String orderFromStr = "普通订单,线上积分,线下积分,内购订单,导购订单,";
        List<String> orderFromList =
                Arrays.asList(org.apache.commons.lang3.StringUtils.splitByWholeSeparatorPreserveAllTokens(orderFromStr,","));
        System.out.println("转换后的大小："+orderFromList.size());
        if (orderFromList != null && !orderFromList.isEmpty()) {
            orderFromList.stream().forEach(System.out::println);
        }
    }
}
