package com.anta.java8.objectcopy;

import com.anta.java8.IWTest;
import com.anta.java8.beans.Copy1;
import com.anta.java8.beans.Copy1Complex;
import com.anta.java8.beans.Copy2;
import com.anta.java8.beans.Copy2Complex;
import com.github.jsonzou.jmockdata.JMockData;
import org.junit.Test;
import org.springframework.cglib.beans.BeanCopier;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 拷贝对象测试
 * 你能看到的框架 基本都是浅拷贝 如  Apache- BeanUtils / Spring-  BeanUtils /Cglib-
 * BeanCopier
 * @author xiaowei
 * @date 2021/4/12 16:24
 */
public class BeanCopierTest implements IWTest {

	// 循环拷贝的正确写法

	/**
	 * 简单批量拷贝
	 */
	@Test
	public void test1(){

		// 1-100内随机返回10个
		List<Copy1> copy1s = new ArrayList<>();
		Random random = new Random();

		random.ints(1,100).limit(10).forEach(value -> {
			Copy1 randomMock = JMockData.mock(Copy1.class);
			copy1s.add(randomMock);
		});

		System.out.println(MessageFormat.format("创建的数据名称为 {0} 大小为{1}","制造数据",copy1s.size()));

		// 这个最好是做缓存，当然最基本的是要放在循环外面做这个事情
		BeanCopier copier = BeanCopier.create(Copy1.class, Copy2.class, false);

		List<Copy2> copy2s = new ArrayList<>();
		copy1s.stream().forEach(copy1 -> {
			Copy2 copy2 = new Copy2();
			copier.copy(copy1,copy2,null);
			copy2s.add(copy2);
		});

		System.out.println(MessageFormat.format("创建的数据名称为 {0} 大小为{1}","制造数据2",copy2s.size()));


	}


	/**
	 * 当是简单属性时，这些框架都能保证没有影响
	 * 当是javabean属性里面有引用类复杂属性时，就能体现深、浅拷贝
	 */
	@Test
	public void test2(){
		Copy1 copy1 = JMockData.mock(Copy1.class);
		System.out.println(MessageFormat.format("copy1数据内容{0}",copy1.toString()));
		BeanCopier copier = BeanCopier.create(Copy1.class, Copy2.class, false);
		Copy2 copy2 = new Copy2();
		copier.copy(copy1,copy2,null);
		System.out.println(MessageFormat.format("copy2数据内容{0}",copy2.toString()));
		copy1.setStr1("修改修改修改修改");
		System.out.println(MessageFormat.format("copy1-1数据内容{0}",copy1.toString()));
		System.out.println(MessageFormat.format("copy2-1数据内容{0}",copy2.toString()));
	}

	/**
	 * 复杂对象拷贝[体现出差异 这些框架级别的拷贝都是浅拷贝]
	 *  》》 可以看到 执行结果：简单属性 都是没有影响 类似深拷贝
	 *  赋值的引用型属性，是浅拷贝，修改源文件，会导致拷贝后的受到影响
	 */
	@Test
	public void test3(){
		Copy1Complex copy1 = JMockData.mock(Copy1Complex.class);
		System.out.println(MessageFormat.format("copy1数据内容{0}",copy1.toString()));
		BeanCopier copier = BeanCopier.create(Copy1Complex.class, Copy2Complex.class, false);
		Copy2Complex copy2 = new Copy2Complex();
		copier.copy(copy1,copy2,null);
		System.out.println(MessageFormat.format("copy2数据内容{0}",copy2.toString()));
		copy1.setStr1("修改修改修改修改");
		copy1.getCommon().setA("修改修改修改修改");
		System.out.println(MessageFormat.format("copy1-1数据内容{0}",copy1.toString()));
		System.out.println(MessageFormat.format("copy2-1数据内容{0}",copy2.toString()));
	}

}
