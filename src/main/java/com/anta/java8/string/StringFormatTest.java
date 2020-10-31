package com.anta.java8.string;

import com.anta.java8.IWTest;
import org.junit.Test;
import org.springframework.context.annotation.Primary;

import java.util.Date;

/**
 * @author xiaowei
 * @date 2020/10/29 8:24
 */
public class StringFormatTest implements IWTest {

	@Test
	public void test1(){
		String userName="XXX";
		String userProvince="上海";
		int userAge=21;
		String userSex="男";

		// $ 相当于变量 符号
		String string = " %1$s  用户来自 %2$s   年龄 %3$d  性别  %4$s   ";
		String userInfo=String.format(string,userName,userProvince,userAge,userSex);
		System.out.println(userInfo);
	}

	@Test
	public void test2(){
		String str=null;
		str=String.format("Hi,%s", "小超");
		System.out.println(str);
		str=String.format("Hi,%s %s %s", "小超","是个","大帅哥");
		System.out.println(str);
		System.out.printf("字母c的大写是：%c %n", 'C');
		System.out.printf("布尔结果是：%b %n", "小超".equals("帅哥"));
		System.out.printf("100的一半是：%d %n", 100/2);
		System.out.printf("100的16进制数是：%x %n", 100);
		System.out.printf("100的8进制数是：%o %n", 100);
		System.out.printf("50元的书打8.5折扣是：%f 元%n", 50*0.85);
		System.out.printf("上面价格的16进制数是：%a %n", 50*0.85);
		System.out.printf("上面价格的指数表示：%e %n", 50*0.85);
		System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50*0.85);
		System.out.printf("上面的折扣是%d%% %n", 85);
		System.out.printf("字母A的散列码是：%h %n", 'A');
	}

	@Test
	public void test3(){
		System.out.printf("50元的书打8.5折扣是：%d 元%n", 80);
		System.out.printf("年-月-日 HH:MM:SS 格式：%tF %<tT%n",new Date());
	}

	@Test
	public void test4(){
		String str=null;
		//$使用  相当于变量的感觉   String.format("格式参数$的使用：%1$d,%1$s", 99,"abc")  结果  格式参数$的使用：99,99
		// String.format("格式参数$的使用：%1$d,%2$s", 99,"abc")  结果  格式参数$的使用：99,abc
		str=String.format("格式参数$的使用：%1$d,%1$s", 99,"abc");
		System.out.println(str);
		//+使用
		System.out.printf("显示正负数的符号：%+d与%d%n", 99,-99);
		//补O使用
		System.out.printf("最牛的编号是：%03d%n", 7);
		//空格使用
		System.out.printf("Tab键的效果是：% 8d%n", 7);
		//.使用
		System.out.printf("整数分组的效果是：%,d%n", 9989997);
		//空格和小数点后面个数
		System.out.printf("一本书的价格是：% 50.5f元%n", 49.8);
	}

	@Test
	public void test5(){
		String s = "XNSZ";
		System.out.println(s.substring(s.length()-4));
	}
}
