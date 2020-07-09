package com.anta.java8.time;

import com.anta.java8.IWTest;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 时间转换
 *
 * @author xiaowei
 * @date 2020/5/20  14:27
 **/
public class DateTrans implements IWTest {


    @Test
    public void test51() {
        System.out.println(Instant.now().getEpochSecond());
        System.out.println(Instant.now().toEpochMilli());
    }

    /**
     * 把当前时间格式为指定格式
     */
    @Test
    public void test5() {
        //获得当前时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format = ldt.format(dtf);
        System.out.println(format);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate ldt4 = LocalDate.parse("20170928",df);
        System.out.println("LocalDateTime转成String类型的时间："+ldt4);
        DateTimeFormatter df5 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ldt5 = LocalDate.parse("2017-09-28",df5);
        System.out.println("类型转换："+ldt5);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = ldt5.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());
        System.out.println("date:::"+date);
    }

    /**
     * 把当前时间格式为指定格式
     */
    @Test
    public void test15() {
        //获得当前时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");
        String format = ldt.format(dtf);
        System.out.println(format);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59");
        String format2 = ldt.format(dtf2);
        System.out.println(format2);
        LocalDate localDate = LocalDate.of(2019, 07, 15);
        String format3 = localDate.format(dtf2);
        System.out.println(format3);

    }

    /**
     * 获取指定的时间  获取2个时间差
     */
    @Test
    public void test16() {
        //LocalDate.now == new Date()
        LocalDate localDate = LocalDate.now();
        //LocalDate.now 实际调用了这个方法
        localDate = LocalDate.now(Clock.systemDefaultZone());
        //指定时间
        localDate = LocalDate.of(2019, 07, 15);
        //判断是不是闰年
        boolean isLeapYear = localDate.isLeapYear();
        System.out.printf("%d是不是闰年？，%s\n", localDate.getYear(), isLeapYear ? "是" : "否");
        //两天后
        LocalDate twoDaysLate = localDate.plusDays(2);
        System.out.println("两天后:" + twoDaysLate);
        //两周后
        LocalDate twoWeeksLate = localDate.plusMonths(2);
        System.out.println("两周后:" + twoDaysLate);
        //两月后
        LocalDate twoMonthsLate = localDate.plusMonths(2);
        System.out.println("两月后:" + twoMonthsLate);
        //两年后
        LocalDate twoYearsLate = localDate.plusYears(2);
        System.out.println("两年后:" + twoYearsLate);
        //两天前
        LocalDate twoDaysBefore = localDate.minusDays(2);
        System.out.println("两天前:" + twoDaysBefore);
        //两周前
        LocalDate twoWeeksBefore = localDate.minusWeeks(2);
        System.out.println("两周前:" + twoWeeksLate);
        //两月前
        LocalDate twoMonthsBefore = localDate.minusMonths(2);
        System.out.println("两月前" + twoMonthsBefore);
        //两年前
        LocalDate twoYearsBefore = localDate.minusYears(2);
        System.out.println("两年前" + twoYearsBefore);
        LocalDate anyTime = LocalDate.now().withMonth(10).withDayOfMonth(1);
        System.out.println("指定日期：" + anyTime);
        //今年的第一天
        LocalDate firstDayOfYear = localDate.with(TemporalAdjusters.firstDayOfYear());
        System.out.println("今年的第一天：" + firstDayOfYear);
        //本月的最后一天, 类似的API还有很多
        LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("本月的最后一天:" + lastDayOfMonth);
        //获取两个LocalDate的时间间隔
        Period period = localDate.until(lastDayOfMonth);
        System.out.println("离本月最后一天还有多少天？" + period.getDays());

        //通过Period 设置时间，增减时间, localdate 其实也是加减的Period
        LocalDate minus = localDate.minus(Period.parse("P1Y2M3D"));
        System.out.println(minus);
        //判断当前时间是否在某个时间之前
        boolean isBefore = localDate.isBefore(minus);
        System.out.println(localDate + "是否在" + minus + "之前");

        //获取一天最开始的时间
        LocalDateTime localDateTime = localDate.atStartOfDay();
        System.out.println("一天的初始时间" + localDateTime);

        //指定某天的具体时间
        LocalDateTime atTime = localDate.atTime(5, 6, 7);
        System.out.println("指定一天的某个时间" + atTime);

    }


    // 01. java.util.Date --> java.time.LocalDateTime
    public void UDateToLocalDateTime() {
        java.util.Date date = new java.util.Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
    }

    // 02. java.util.Date --> java.time.LocalDate
    public void UDateToLocalDate() {
        java.util.Date date = new java.util.Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
    }

    // 03. java.util.Date --> java.time.LocalTime
    public void UDateToLocalTime() {
        java.util.Date date = new java.util.Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalTime localTime = localDateTime.toLocalTime();
    }


    // 04. java.time.LocalDateTime --> java.util.Date
    public void LocalDateTimeToUdate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        java.util.Date date = Date.from(instant);
    }


    // 05. java.time.LocalDate --> java.util.Date
    public void LocalDateToUdate() {
        LocalDate localDate = LocalDate.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        java.util.Date date = Date.from(instant);
    }

    // 06. java.time.LocalTime --> java.util.Date
    public void LocalTimeToUdate() {
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        java.util.Date date = Date.from(instant);
    }


    /**
     * 把指定字符串格式化为日期
     */
    @Test
    public void test6() {
        String str1 = "2018-07-05 12:24:12";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(str1, dtf);
        LocalDate dateParse = LocalDate.parse(str1, dtf);
        System.out.println(parse);
    }

    /**
     * 把指定字符串格式化为日期
     */
    @Test
    public void test7() {
        String str1 = "2018-07-05";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime parse = LocalDateTime.parse(str1, dtf);
        System.out.println(parse);
    }

    @Test
    public void test8() {
        System.out.println("形如2020-5-20str:" + DateUtils8.getNowLocalTime_medium());
        System.out.println("形如2020-5-20str:" + DateUtils8.getNowLocalTime_long());
        System.out.println("形如2020-5-20str:" + DateUtils8.getNowLocalTime_shot());
        System.out.println("形如2020-5-20str:" + DateUtils8.getNowLocalTime_shot());
    }


    @Test
    public void test9() throws ParseException {
        Date date = new SimpleDateFormat("yyyyMMdd").parse("20200520");
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String dateStr2 = new SimpleDateFormat("yyyyMMdd").format(date);
        // 2020-05-20
        System.out.println(dateStr);
        // 20200520
        System.out.println(dateStr2);
    }


}
