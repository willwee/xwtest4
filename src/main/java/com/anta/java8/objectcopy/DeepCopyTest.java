package com.anta.java8.objectcopy;

import com.anta.java8.IWTest;
import com.anta.java8.beans.Copy1;
import com.anta.java8.beans.Copy1Complex;
import com.anta.java8.beans.Copy2;
import com.anta.java8.beans.Copy2Complex;
import com.github.jsonzou.jmockdata.JMockData;
import com.google.gson.Gson;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 对比了其他框架，最终选型  使用Gson的JSON系列化
 *
 * @author xiaowei
 * @date 2021/4/13 11:26
 */
public class DeepCopyTest implements IWTest {

	/**
	 * 批量深度拷贝
	 */
	@Test
	public void test1() {

		// 1-100内随机返回10个
		List<Copy1> copy1s = new ArrayList<>();
		Random random = new Random();

		random.ints(1, 100).limit(10).forEach(value -> {
			Copy1 randomMock = JMockData.mock(Copy1.class);
			copy1s.add(randomMock);
		});
		System.out.println(MessageFormat.format("创建的数据名称为 {0} 大小为{1}", "制造数据", copy1s.size()));

		List<Copy2> copy2List = new ArrayList<>();
		Gson gson = new Gson();
		copy1s.stream().forEach(copy1 -> {
			System.out.println(copy1.toString());
			Copy2 jsonBean = gson.fromJson(gson.toJson(copy1), Copy2.class);
			copy2List.add(jsonBean);
		});
		System.out.println(MessageFormat.format("创建的数据2名称为 {0} 大小为{1}", "制造数据", copy2List.size()));

		copy2List.stream().forEach(copy2 -> {
			System.out.println(copy2);
		});
	}

	/**
	 * 单笔深度拷贝 【简单属性没法看出区别】
	 */
	@Test
	public void test2() {
		Copy1 copy1 = JMockData.mock(Copy1.class);
		System.out.println(MessageFormat.format("原始数据内容{0}", copy1.toString()));
		Gson gson = new Gson();
		Copy2 copy2 = gson.fromJson(gson.toJson(copy1), Copy2.class);
		System.out.println(MessageFormat.format("copy2数据内容{0}", copy2.toString()));
		copy1.setStr1("修改修改");
		System.out.println(MessageFormat.format("copy1-1数据内容{0}", copy1.toString()));
		System.out.println(MessageFormat.format("copy2-1数据内容{0}", copy2.toString()));
	}

	/**
	 * 单笔深度拷贝 【复杂引用类属性 可以看出区别 深拷贝/浅拷贝 区别】
	 */
	@Test
	public void test3() {
		Copy1Complex copy1 = JMockData.mock(Copy1Complex.class);
		System.out.println(MessageFormat.format("copy1数据内容{0}", copy1.toString()));
		Gson gson = new Gson();
		Copy2Complex copy2 = gson.fromJson(gson.toJson(copy1), Copy2Complex.class);
		System.out.println(MessageFormat.format("copy2数据内容{0}", copy2.toString()));
		copy1.setStr1("修改修改修改修改");
		copy1.getCommon().setA("修改修改修改修改");
		System.out.println(MessageFormat.format("copy1-1数据内容{0}", copy1.toString()));
		System.out.println(MessageFormat.format("copy2-1数据内容{0}", copy2.toString()));
	}
}
