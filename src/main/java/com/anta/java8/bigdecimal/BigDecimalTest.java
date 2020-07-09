package com.anta.java8.bigdecimal;

import com.anta.java8.IWTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * 各个roundingMode详解如下：
 * ROUND_UP：非0时，舍弃小数后（整数部分）加1，比如12.49结果为13，-12.49结果为 -13
 * ROUND_CEILING：如果 BigDecimal 是正的，则做 ROUND_UP 操作；如果为负，则做 ROUND_DOWN 操作 （一句话：取附近较大的整数）
 * ROUND_FLOOR: 如果 BigDecimal 是正的，则做 ROUND_DOWN 操作；如果为负，则做 ROUND_UP 操作（一句话：取附近较小的整数）
 * ROUND_HALF_UP：四舍五入（取更近的整数）
 * ROUND_DOWN：直接舍弃小数
 * ROUND_HALF_DOWN：跟ROUND_HALF_UP 差别仅在于0.5时会向下取整
 * ROUND_HALF_EVEN：取最近的偶数
 * ROUND_UNNECESSARY：不需要取整，如果存在小数位，就抛ArithmeticException 异常
 *
 * @author xiaowei
 * @date 2020/5/28 13:07
 */
public class BigDecimalTest implements IWTest {
	/**
	 * Java中BigDecimal取整方法
	 */
	@Test
	public void test1() {
		BigDecimal bd = new BigDecimal("12.1");
		long l1 = bd.setScale(0, BigDecimal.ROUND_UP).longValue(); // 向上取整
		System.out.println("l1:" + l1);
		long l2 = bd.setScale(0, BigDecimal.ROUND_DOWN).longValue(); // 向下取整
		System.out.println("l2:" + l2);

		//* 对于正数而言，ROUND_UP  = ROUND_CEILING，ROUND_DOWN = ROUND_FLOOR
		long l3 = bd.setScale(3, RoundingMode.HALF_EVEN).longValue();
		System.out.println("l3:" + l3);
		BigDecimal l4 = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
		System.out.println("l4:" + l4);
		// 直接舍弃小数点后面的
		BigDecimal l5 = bd.setScale(0, BigDecimal.ROUND_DOWN);
		System.out.println("l5:" + l5);

		BigDecimal l6 = bd.setScale(0,RoundingMode.DOWN);
		System.out.println("l6:" + l6);
	}


	/**
	 * flag = -1,表示bigdemical小于bigdemical1；
	 * flag = 0,表示bigdemical等于bigdemical1；
	 * flag = 1,表示bigdemical大于bigdemical1；
	 * bigdecimal 大小比较
	 */
	@Test
	public void test2() {
		BigDecimal bigdemical = new BigDecimal("12.1");
		BigDecimal bigdemical1 = new BigDecimal("13.10");
		int flag = bigdemical.compareTo(bigdemical1);
		System.out.println("大小比较结果::"+flag);
	}
	@Test
	public void test3() {
		Date nn = new Date();
		System.out.println(nn.getTime());
	}

	@Test
	public void test5() {
		int a  = 10;
		int b = 11;
		System.out.println("大小比较结果::"+(a+b));
	}

}
