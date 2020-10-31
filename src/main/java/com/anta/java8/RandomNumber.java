package com.anta.java8;

import com.anta.java8.time.DateUtilBs;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
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

    @Test
    public void test2(){
        String channelStrs = "631915,631917,631918";
        if (StringUtils.isNotBlank(channelStrs)) {
            Set<Long> ss = Arrays.stream(channelStrs.split(",")).map(v -> Long.valueOf(v)).collect(Collectors.toSet());
            System.out.println(ss);
            System.out.println(ss.size());
        }
    }


    @Test
    public void test3(){
        int i = 11/10;

        String sql = "select categorytreeid, categorytreenodeid, %s from %s where %s in (%s)";
        int size = 4;
        String valuePlaceholder = IntStream.range(0, size).mapToObj(s -> "?").collect(Collectors.joining(","));

        sql = String.format(sql, "filed", "tableName", "filed", valuePlaceholder);

        System.out.println(sql);


    }
}
