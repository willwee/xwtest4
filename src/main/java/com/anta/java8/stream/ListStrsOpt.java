package com.anta.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* List STR 相关操作
* @author xiaowei
* @date 2020/6/3 17:36
**/
public class ListStrsOpt {

    public static void main(String[] args) {

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");


        // filtering

        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

        // "aaa2", "aaa1"


        // sorting

        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

        // "aaa1", "aaa2"


        // mapping

        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);

        // "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"


        // matching

        boolean anyStartsWithA = stringCollection
                .stream()
                .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA = stringCollection
                .stream()
                .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ = stringCollection
                .stream()
                .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);      // true


        // counting

        long startsWithB = stringCollection
                .stream()
                .filter((s) -> s.startsWith("b"))
                .count();

        System.out.println(startsWithB);    // 3


        // reducing

        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);
        // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"


    }

    @Test
    public void test1(){
        List<String> test = new ArrayList<>();
        test.add("11");
        test.add("22");
        test.add("33");
        System.out.println();
        List<String> newList = new ArrayList<>();
        List<String> list = test.parallelStream().filter(v ->
        {
            if(v.equals("33")){
                newList.add(v);
                newList.add("44");
                return false;
            }else{
                return true;
            }

        }).collect(Collectors.toList());

        System.out.println("test的大小:"+test.size());
        System.out.println("过滤后大小:"+list.size());
        System.out.println("newStr:"+newList.size());

        list.addAll(0,newList);

        list.stream().forEach(System.out::println);
    }

    @Test
    public void test2(){
         Long brandId =0L;
        System.out.println(brandId!=0);
    }

}
