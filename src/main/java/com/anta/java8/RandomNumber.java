package com.anta.java8;

import com.anta.java8.time.DateUtilBs;
import org.junit.Test;

import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 生成随机数
 *
 * @author xiaowei
 * @date 2020/5/15  9:45
 **/
public class RandomNumber implements IWTest {
    /**
     * 生成100以内的随机数
     */
    @Test
    public void testIntRandom() {
        Random random = new Random();
        IntStream intStream = random.ints(0,5);
//        intStream.limit(20).forEach(System.out::print);
        Date kk = new Date();
        Date startTime = DateUtilBs.convertToDate(kk.toString(), DateUtilBs.DEF_DATE_FMT);
        System.out.println(startTime);
    }
}
