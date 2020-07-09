package com.anta.java8.time;

import com.anta.java8.lang.LongUtilBs;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * 日期工具类(百胜)
 *
 * @author hangwen
 */
public class DateUtilBs {


	/**
	 * @param date
	 * @param day
	 * @return 时间
	 * @throws ParseException
	 */
	public static Date minusMillisecond(Date date, long millisecond) throws ParseException {
		long minus = date.getTime() - millisecond;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(minus);
		Date redate = format.parse(d);
		return redate;
	}

	/**
	 * 获取调整日期
	 *
	 * @param originalDate 被调整的日期，null表示当前时间
	 * @param type         {@link Calendar}类定义的各种常量，年、月、周、日、时、分、秒、毫秒
	 * @param value        调整值 正数表示日期之后，负数表示之前
	 * @return
	 */
	public static Date adjust(Date originalDate, int type, int value) {
		Calendar cal = Calendar.getInstance();

		if (originalDate != null) {
			cal.setTime(originalDate);
		}

		if (value != 0) {
			cal.add(type, value);
		}

		return cal.getTime();
	}

	/**
	 * 任意设置当前时间的时分秒
	 */
	public static Date setTime(Date date, int hour, int minute, int second, int millisecond) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, millisecond);

		return calendar.getTime();
	}

	/**
	 * 任意设置当前时间的时分秒
	 */
	public static Date setTime(int year, int month, int day, int hour, int minute, int second, int millisecond) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);

		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, millisecond);

		return calendar.getTime();
	}

	/**
	 * 比较两个时间的年月日是否相同,不比较时间部分
	 *
	 * @param 传入两个不同的时间
	 * @return
	 */
	public static boolean compareDateValue(Date d1, Date d2) {
		if (d1 == null && d2 == null) {
			return true;
		}

		if (d1 != null && d2 == null) {
			return false;
		}

		if (d1 == null && d2 != null) {
			return true;
		}

		int year1 = getYearValue(d1);
		int year2 = getYearValue(d2);

		int monthValue1 = getMonthValue(d1);
		int monthValue2 = getMonthValue(d2);

		int dayValue1 = getDayValue(d1);
		int dayValue2 = getDayValue(d2);

		if (year1 == year2 && monthValue1 == monthValue2 && dayValue1 == dayValue2) {
			return true;
		}

		return false;
	}

	/**
	 * 获取当前日期的年份
	 */
	public static int getYearValue(Date d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取当前日期的月份
	 */
	public static int getMonthValue(Date d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前日期的日份
	 */
	public static int getDayValue(Date d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 转换日期,如果参数是String类型，按"yyyy-MM-dd HH:mm:ss"转换
	 *
	 * @param val
	 * @return
	 * @throws ParseException
	 * @see DateUtil::format
	 */
	public static Date convertToDate(Object val) {
		return convertToDate(val, DEF_DATETIME_FMT);
	}

	/**
	 * 转换日期
	 *
	 * @param val
	 * @param format 如果参数是String类型，按此参数转换 <li>yyyy:年份 <li>MM:月份 <li>dd:日期 <li>HH:小时
	 *               <li>mm:分钟 <li>ss:秒 <li>ms:毫秒
	 * @return
	 */
	public static Date convertToDate(Object val, String format) {
		try {
			return innerConvertToDate(val, format);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 按格式化字符串："yyyy-MM-dd HH:mm:ss" 格式化日期对象
	 *
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, DEF_DATETIME_FMT);
//		return formatDefault(date);
	}

	/**
	 * 指定格式化字符串，格式化日期对象
	 *
	 * @param date
	 * @param format <li>yyyy:年份 <li>MM:月份 <li>dd:日期 <li>HH:小时 <li>mm:分钟 <li>ss:秒
	 *               <li>ms:毫秒
	 * @return
	 */
	public static String format(Date date, String format) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 根据年和月拿到当月的天数
	 *
	 * @param year  年
	 * @param month 月
	 * @return
	 */
	public static int getDaysInMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1, 0, 0, 0);

		return cal.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 获取两个日期之间的相隔天数
	 *
	 * @param Date date1
	 * @param Date date2
	 * @return days date2 - date1 的天数
	 */
	public static int getDifferDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		clearTimeField(cal1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		clearTimeField(cal2);

		long between_days = (cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (1000 * 3600 * 24);
		return (int) between_days;
	}

	/**
	 * @param date1
	 * @param date2
	 * @return
	 * @see DateUtil#getDifferDays(Date, Date)
	 */
	@Deprecated
	public static int getDifferDays(Object date1, Object date2) {
		Date d1 = convertToDate(date1);
		Date d2 = convertToDate(date2);

		return getDifferDays(d1, d2);
	}

	/**
	 * 获取两个日期之间的相隔月数
	 *
	 * @param Date date1
	 * @param Date date2
	 * @return days date2 - date1 的月数
	 */
	public static int getDifferMonths(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		long between_months = (cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR)) * 12
				+ (cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH));
		return (int) between_months;
	}

	/**
	 * getDifferMinutes
	 *
	 * @param date1
	 * @param date2
	 * @return date2 - date1
	 */
	public static int getDifferMinutes(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		long between_Minutes = (cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (1000 * 60);
		return (int) between_Minutes;
	}

	/**
	 * 获取当月第一天
	 *
	 * @return
	 */
	public static Date getFirstDayOfMonth() {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.DAY_OF_MONTH, 1);
		clearTimeField(cal);

		return cal.getTime();
	}

	/**
	 * 获取本周一
	 *
	 * @return
	 */
	public static Date getFirstDayOfWeek() {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		clearTimeField(cal);

		return cal.getTime();
	}

	/**
	 * 获取当月最后一天
	 *
	 * @return
	 */
	public static Date getLastDayOfMonth() {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		clearTimeField(cal);

		return cal.getTime();
	}

	/**
	 * 获取本周日
	 *
	 * @return
	 */
	public static Date getLastDayOfWeek() {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		cal.add(Calendar.DATE, 1);
		clearTimeField(cal);

		return cal.getTime();
	}

	/**
	 * 获取当天日期与时间
	 *
	 * @return
	 */
	public static Date getNow() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	/**
	 * 获取当天日期，不包括时间部分
	 *
	 * @return
	 */
	public static Date getToday() {
		Calendar cal = Calendar.getInstance();
		clearTimeField(cal);
		return cal.getTime();
	}

	/**
	 * 判断参数是否符合日期正则
	 * <p>
	 *
	 * <li>(1)能匹配的年月日类型有： 2014年4月19日 2014年4月19号 2014-4-19 2014/4/19 2014.4.19
	 * <li>(2)能匹配的时分秒类型有： 15:28:21 15:28 5:28 pm 15点28分21秒 15点28分 15点
	 * <li>(3)能匹配的年月日时分秒类型有： (1)和(2)的任意组合，二者中间可有任意多个空格
	 *
	 * @param date
	 * @return
	 */
	public static boolean isMatchDatePattern(String date) {
		return datePattern.matcher(date).matches();
	}

	private static void clearTimeField(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}

	private static Date innerConvertToDate(Object val, String format) throws ParseException {
		if (val instanceof java.sql.Date) {
			return new Date(((java.sql.Date) val).getTime());
		} else if (val instanceof Date) {
			return (Date) val;
		}

		if (val instanceof Integer || val instanceof Long) {
			return new Date(LongUtilBs.parse(val));
		} else if (val != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(val.toString());
		}

		return null;
	}

	private static final Pattern datePattern = Pattern
			.compile(
					"(\\d{1,4}[-|\\/|年|\\.]\\d{1,2}[-|\\/|月|\\.]\\d{1,2}([日|号])?(\\s)*(\\d{1,2}([点|时])?((:)?\\d{1,2}(分)?((:)?\\d{1,2}(秒)?)?)?)?(\\s)*(PM|AM)?)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DEF_DATETIME_FMT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy-MM-dd
	 */
	public static final String DEF_DATE_FMT = "yyyy-MM-dd";

	/**
	 * yyyy-MM-dd
	 */
	public static final String DEF_DATE_FMT3 = "yyyyMMdd";

	/**
	 * yyyy-MM-dd-HHmmss
	 */
	public static final String DEF_DATETIME_FMT2 = "yyyy-MM-dd-HHmmss";

	/**
	 * HH:mm:ss
	 */
	public static final String DEF_TIME_FMT = "HH:mm:ss";

	/**
	 * HH:mm:ss
	 */
	public static final String DEF_TIME_FMT2 = "HHmmss";

	/**
	 * "yyyy-MM-dd HH:mm
	 */
	public static final String DEF_DATETIME_FMT3 = "yyyyMMddHHmmss";
	public static final String DEF_DATETIME_FMT4 = "yyyy-MM-dd HH:mm";

	public static final String RANGE_DEFAULTTIME = "00:00:00" + " " + "23:59:59";
	public static final String START_DEFAULTTIME = "00:00:00";
	public static final String END_DEFAULTTIME = "23:59:59";


	private static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>();

	private static SimpleDateFormat getDateFormat() {
		SimpleDateFormat dateFormat = local.get();
		if (dateFormat == null) {
			dateFormat = new SimpleDateFormat(DEF_DATETIME_FMT);
			local.set(dateFormat);
		}
		return dateFormat;
	}

	public static String formatDefault(Date date) {
		return getDateFormat().format(date);
	}

	public static Date parseDefault(String dateStr) throws ParseException {
		return getDateFormat().parse(dateStr);
	}


	// 测试开始 start
	@Test
	public void test() {

//		long s1 = Instant.now().toEpochMilli();
//		IntStream.range(0, 100).parallel().forEach(v -> {
//			String s = DateUtilBs.format(new Date());
//		});
//
//		System.out.println(Instant.now().toEpochMilli() - s1);


		List<String> ds = Collections.synchronizedList(new ArrayList<>());
		long s2 = Instant.now().toEpochMilli();
		LocalDate localDate = LocalDate.of(2019, 07, 15);
		Date startDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		LocalDate localDate2 = LocalDate.of(2020, 07, 15);
		Date endDate = Date.from(localDate2.atStartOfDay(ZoneId.systemDefault()).toInstant());
		IntStream.range(0, 1000).parallel().forEach(v -> {
			ds.add(Thread.currentThread().getName() + ":::" + DateUtilBs.formatDefault(randomDate(startDate, endDate)));
		});

		System.out.println(Instant.now().toEpochMilli() - s2);
		ds.stream().forEach(System.out::println);

		// apache自带处理
//		long s3 = Instant.now().toEpochMilli();
//		IntStream.range(0, 100).parallel().forEach(v -> {
//			String s = DateFormatUtils.format(new Date(), DEF_DATETIME_FMT);
//		});
//
//		System.out.println(Instant.now().toEpochMilli() - s3);

	}


	/**
	 * 按指定日期范围随机生成时间
	 *
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static Date randomDate(Date beginDate, Date endDate) {
		if (beginDate.getTime() >= endDate.getTime()) {
			return new Date();
		}
		long date = random(beginDate.getTime(), endDate.getTime());
		return new Date(date);
	}

	public static long random(long begin, long end) {
		long rtn = begin + (long) (Math.random() * (end - begin));
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}


	// end
}
