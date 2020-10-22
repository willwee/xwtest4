package com.anta.java8.optional;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Benjamin Winterberg
 */
public class Optional1 {


    //Optional.of(T value),当value值为null时，会报NullPointerException异常
    //Optional.ofNullable(T value),当value值为null时，会直接返回一个EMPTY对象。
    //ifPresent(T value),就是value值不为空时，做的一些操作。
    //isPresent(T value),判断value值是否为空。
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("bam");

        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
    }

    @Test
    public void testListNull() {
//        String[] antaGoodss = null;
        String[] antaGoodss = new String[]{};
        Optional.ofNullable(antaGoodss).ifPresent(antaGoods -> {
            Arrays.stream(antaGoods).forEach(antaGood ->{
                System.out.println(antaGood);
            });
        });
    }

}