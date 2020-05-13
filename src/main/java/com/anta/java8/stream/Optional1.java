package com.anta.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Benjamin Winterberg
 */
public class Optional1 {

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