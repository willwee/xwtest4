package com.anta.java8.jmockdatatest;

import com.anta.java8.IWTest;
import com.anta.java8.beans.Copy1;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * 随机测试数据
 * @author xiaowei
 * @date 2021/4/12 17:52
 */
public class JmockDataTest implements IWTest {
	@Test
	public void testBaseType(){
		// 基础数据类型
		System.out.println(JMockData.mock(byte.class));
		System.out.println(JMockData.mock(int.class));
		System.out.println(JMockData.mock(long.class));
		System.out.println(JMockData.mock(double.class));
		System.out.println(JMockData.mock(float.class));
		System.out.println(JMockData.mock(String.class));
		System.out.println(JMockData.mock(BigDecimal.class));

		// 基础数据类型的数组
		System.out.println(JMockData.mock(byte[].class));
		System.out.println(JMockData.mock(int[].class));
		System.out.println(JMockData.mock(long[].class));
		System.out.println(JMockData.mock(double[].class));
		System.out.println(JMockData.mock(float[].class));
		System.out.println(JMockData.mock(String[].class));
		System.out.println(JMockData.mock(BigDecimal[].class));
	}

	/**
	 * java bean 测试
	 */
	@Test
	public void testJavaBean(){
		Copy1 mock = JMockData.mock(Copy1.class);
		System.out.println(mock);
	}

	@Test
	public void testJavaBeanWithConfig() {

		MockConfig mockConfig =
				new MockConfig()
						.subConfig("db1")
						// 设置 int 的范围
						.intRange(1, 100)
						.subConfig("str1")
						// 设置生成邮箱正则
						.stringRegex("[a-z0-9]{5,15}\\@\\w{3,5}\\.[a-z]{2,3}")
						.globalConfig();

		Copy1 mock = JMockData.mock(Copy1.class, mockConfig);
		System.out.println(mock);
	}




}
