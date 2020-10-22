package com.anta.java8.time;

import com.anta.java8.IWTest;
import org.junit.Test;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * 系统时间获取测试
 */
public class TimeStamp implements IWTest {

    /**
     * 循环一亿次的时间对比结果
     * System.currentTimeMillis获取方式用时[827] 如果要到秒级 要除 1000
     * Calendar.getInstance().getTimeInMillis获取方式用时[32469]
     * new Date().getTime获取方式用时[4]
     * Instant.now().getEpochSecond获取方式用时[9]
     */
    @Test
    public void testTimeStamp(){
        // 循环1亿次
        int loopNum = 100000000;
        // 测试System.currentTimeMillis()
        long t1 = System.currentTimeMillis();
        // java8方式循环 1万次
        IntStream.range(0,loopNum).forEach(i -> System.currentTimeMillis());
        long t2 = System.currentTimeMillis();
        System.out.printf("System.currentTimeMillis获取方式用时[%s]\n",t2-t1);

        //
        long t3 = System.currentTimeMillis();
        // java8方式循环 1万次
        IntStream.range(0,loopNum).forEach(i -> Calendar.getInstance().getTimeInMillis());
        long t4 = System.currentTimeMillis();
        System.out.printf("Calendar.getInstance().getTimeInMillis获取方式用时[%s]\n",t4-t3);

        long t5 = System.currentTimeMillis();
        // java8方式循环 1万次
        IntStream.range(0,10000).forEach(i -> new Date().getTime());
        long t6 = System.currentTimeMillis();
        System.out.printf("new Date().getTime获取方式用时[%s]\n",t6-t5);

        long t7 = System.currentTimeMillis();
        // java8方式循环 1万次
        IntStream.range(0,10000).forEach(i -> Instant.now().getEpochSecond());
        long t8 = System.currentTimeMillis();
        System.out.printf("Instant.now().getEpochSecond获取方式用时[%s]\n",t8-t7);
    }

    @Test
    public void test2(){
        // 能根据时间戳获取正确的时间
        Long nowtime = Instant.now().getEpochSecond();

        Date date =  new Date(nowtime * 1000);

        System.out.println(nowtime);

        System.out.println(date);

        System.out.println(date.getTime());

        System.out.println(date.getTime()/1000);

    }
}
