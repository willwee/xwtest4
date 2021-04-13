package com.anta.java8.stringformat;

import com.anta.java8.IWTest;
import org.junit.Rule;
import org.junit.Test;

import java.text.MessageFormat;

/**
 * @author xiaowei
 * @date 2021/4/13 11:16
 */
public class MessageFormatTest implements IWTest {


	/**
	 * MessageFormat 貌似是跟里面的参数{0} 有关系，虽然可以忽略类型，但是必须要标注序号 [可能跟messageFormat的版本有关系]
	 */
	@Test
	public void test(){
		String url = "http://xxx.xxx.xxx?name={0}&age={1}";
		String path = MessageFormat.format(url, "张三", 18);
		System.out.println(path);//http://xxx.xxx.xxx?name=张三&age=18
	}


	/**
	 * 性能比较
	 */
	@Test
	public void test2(){

		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			String s = "Hi " + i + "; Hi to you " + i * 2;
		}
		long end = System.currentTimeMillis();
		System.out.println("Concatenation = " + ((end - start)) + " millisecond");

		start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			String s = String.format("Hi %s; Hi to you %s", i, +i * 2);
		}
		end = System.currentTimeMillis();
		System.out.println("Format = " + ((end - start)) + " millisecond");

		start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			String s = MessageFormat.format("Hi %s; Hi to you %s", i, +i * 2);
		}
		end = System.currentTimeMillis();
		System.out.println("MessageFormat = " + ((end - start)) + " millisecond");

		start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			StringBuilder bldString = new StringBuilder("Hi ");
			bldString.append(i).append("; Hi to you ").append(i * 2).toString();
		}
		end = System.currentTimeMillis();
		System.out.println("StringBuilder = " + ((end - start)) + " millisecond");

	}
}
