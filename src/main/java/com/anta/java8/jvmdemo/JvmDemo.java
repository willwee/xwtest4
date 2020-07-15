package com.anta.java8.jvmdemo;

import com.anta.java8.IWTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author xiaowei
 * @date 2020/7/1 15:25
 */
public class JvmDemo implements IWTest {

	private int i;
	private String b = "0";
	private static String bb = "11";
	private static int ii;
	private final static String bbb = "11";
	private final static char cbb = 1;
	private final static byte bybb = 1;
	private static final short siii = 99;
	private static final int iii = 99;
	private static final long liii = 100L;
	private static final double dd = 96.0;
	private static final float fdd = 98;
	private static final boolean bii = false;
	/**
	 * VM Args：-Xms64m -Xmx64m -XX:+HeapDumpOnOutOfMemoryError
	 * <p>
	 * -Xms512m -Xmx512m -Xmn128m -XX:+HeapDumpOnOutOfMemoryError
	 */
	@Test
	public void test() {

//		CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> testSimpleObject());

		CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> testBigObject());

//		CompletableFuture.allOf(f1,f2).join();

//		f1.exceptionally(e -> {
//			e.printStackTrace();
//			return null;
//		});

		f2.exceptionally(e -> {
			e.printStackTrace();
			return null;
		});

//		CompletableFuture.anyOf(f1,f2).join();
//		CompletableFuture.allOf(f1).join();
		CompletableFuture.allOf(f2).join();

		System.out.println("test git commit");

		System.out.println("out of service.");

	}

	private void testBigObject() {
		List<StaticObject> list = new ArrayList<>();
		int i = 1;

		//不断的向堆中添加对象
		while (true) {


			try {
				StaticObject so = new StaticObject();
				i++;
				so.setI(i);
				list.add(new StaticObject());
				System.out.println(Thread.currentThread().getName() + "big-thread" + so.getI());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	private void testSimpleObject() {
		int i = 1;

		while (true) {
			try {
				i++;
				System.out.println(Thread.currentThread().getName() + "simple-thread" + i);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	public void bytesParse(int a, String b) {
		int c = 0;
		int d = 4;
		d = c + a;
		System.out.println("结果为：" + b + "::" + d);

	}

}
