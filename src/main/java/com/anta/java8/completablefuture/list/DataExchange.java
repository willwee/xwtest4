package com.anta.java8.completablefuture.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 数据结构转换
 *
 * @author xiaowei
 * @date 2020-05-06
 **/
public class DataExchange {

    public static void main(String[] args){
        String str = "1,2,3";
        int[] a = Arrays.stream(str.split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
        int[] src = {1,2,3,4,5,6,7,8,9,10};
        List<Integer> list = Arrays.stream( src ).boxed().collect(Collectors.toList());


        for (int i=0; i<a.length; i++){
            System.out.println(a[i]);
        }

        for (int i=0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    @Test
    public void testDataConvert() {

        String[] arrays = new String[]{"a", "b", "c"};
        List<String> listStrings = Stream.of(arrays).collect((Collectors.toList()));

        listStrings.parallelStream().forEach(System.out::println);


        String[] ss = listStrings.stream().toArray(String[]::new);

        List<Long> goodsIds = new ArrayList<>();
        goodsIds.add(10L);
        goodsIds.add(11L);

    }

}
