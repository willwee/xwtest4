package com.anta.java8.optional;

import com.anta.java8.IWTest;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional test 案例
 * 直接查看出处  https://blog.csdn.net/zjhred/article/details/84976734
 * 1.of / ofNullable 的区别是  of(T value)会报NullPointerException异常；ofNullable(T value)不会throw Exception，ofNullable(T value)直接返回一个EMPTY对象。
 * 2.flatMap 和map 在使用上的区别是 针对的对象不一样。flatMap针对的是 User2 ,map 针对的是User这种结构
 * 3.
 * @author xiaowei
 * @date 2020/8/28 11:09
 */
public class OptionalTest implements IWTest {

	class User {
		private String name;
		public String getName() {
			return name;
		}
	}


	class User2 {
		private String name;
		public Optional<String> getName() {
			return Optional.ofNullable(name);
		}
	}

	/**
	 * test Optional  map
	 */
	@Test
	public void test1() {
		User user = new User();
		String city = Optional.ofNullable(user).map(u-> u.getName()).get();
	}


	public void test2(){
		User2 user = new User2();
		String city = Optional.ofNullable(user).flatMap(u-> u.getName()).get();
	}


}
