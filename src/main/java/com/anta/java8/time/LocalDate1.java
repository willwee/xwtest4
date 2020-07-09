package com.anta.java8.time;

import com.anta.java8.IWTest;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * @author Benjamin Winterberg
 */
public class LocalDate1 implements IWTest {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);

        System.out.println(today);
        System.out.println(tomorrow);
        System.out.println(yesterday);

        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek);    // FRIDAY

        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.MEDIUM)
                        .withLocale(Locale.GERMAN);

        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
        System.out.println(xmas);   // 2014-12-24


    }

    /**
     * LocalDate 转时间戳
     */
    @Test
    public void test() {
        LocalDate today = LocalDate.now();
        System.out.println( today.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli());

        System.out.println( today.atStartOfDay(ZoneOffset.systemDefault()).toInstant().toEpochMilli());
    }

}