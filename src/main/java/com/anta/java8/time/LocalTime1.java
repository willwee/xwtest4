package com.anta.java8.time;

import com.alibaba.excel.util.DateUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * @author Benjamin Winterberg
 */
public class LocalTime1 {

	public static void main(String[] args) {

		// get the current time
		Clock clock = Clock.systemDefaultZone();
		long t0 = clock.millis();
		System.out.println(t0);

		Instant instant = clock.instant();
		Date legacyDate = Date.from(instant);


		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");

		System.out.println(zone1.getRules());
		System.out.println(zone2.getRules());

		// time
		LocalTime now1 = LocalTime.now(zone1);
		LocalTime now2 = LocalTime.now(zone2);

		System.out.println(now1);
		System.out.println(now2);

		System.out.println(now1.isBefore(now2));  // false

		long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
		long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
		System.out.println(hoursBetween);
		System.out.println(minutesBetween);


		// create time

		LocalTime now = LocalTime.now();
		System.out.println(now);

		LocalTime late = LocalTime.of(23, 59, 59);
		System.out.println(late);

		DateTimeFormatter germanFormatter =
				DateTimeFormatter
						.ofLocalizedTime(FormatStyle.SHORT)
						.withLocale(Locale.GERMAN);

		LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
		System.out.println(leetTime);


		// to legacy date


	}

	@Test
	public void test1() {
		LocalDate startDate = LocalDate.of(1993, Month.OCTOBER, 19);
		System.out.println("开始时间  : " + startDate);

		LocalDate endDate = LocalDate.of(2017, Month.JUNE, 16);
		System.out.println("结束时间 : " + endDate);

		long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);
		System.out.println("两天之间的差在天数   : " + daysDiff);

	}

	/**
	 * 构造<br>
	 * 在前的日期做为起始时间，在后的做为结束时间
	 *
	 * @param begin 起始时间
	 * @param end   结束时间
	 * @param isAbs 日期间隔是否只保留绝对值正数
	 * @since 3.1.1
	 */
	public static long hoursBetween(Date begin, Date end) {
		Assert.notNull(begin, "Begin date is null !");
		Assert.notNull(end, "End date is null !");

		// 间隔只为正数的情况下，如果开始日期晚于结束日期,提示
		Assert.isTrue(begin.before(end), "Begin date must before End !");

		long diff = end.getTime() - begin.getTime();
		return diff / DateUnit.HOUR.getMillis();
	}

	@Test
	public void test2() throws ParseException {

		System.out.println(LocalTime1.hoursBetween(DateUtils.parseDate("2021-04-06 00:18:00"), new Date()));

		System.out.println("abs");

		System.out.println();

	}


}