package com.anta.java8.lsdh;

import com.anta.java8.IWTest;
import org.junit.Test;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xiaowei
 * @date 2020/10/13 10:43
 */
public class lsdhTest implements IWTest {

	static class A {
		int a = 0;
		int b = 1;
	}

	static class B extends A {
		int c = 1;
		double d = 2.2;
	}

	static class C {
		public int CCC(List<A> aList) {
			System.out.println();
			return 1;
		}
	}


	public static void main(String[] args) {

		C c = new C();
		List<A> blist = new ArrayList<>();
		B b = new B();
		blist.add(b);
		c.CCC(blist);

	}
	@Test
	public void test1(){
		Assert.hasLength(null,"商店id不能为空！");
	}

	@Test
	public void test100(){
		Integer i = 7;
		System.out.println(i == 7);
	}

	@Test
	public void  test2(){

		Set<String> retailNameList = new HashSet<>();
		retailNameList.add("AT__00LT0R");
		retailNameList.add("AT__00LT0T");
		retailNameList.add("AT__00LT0A");
		retailNameList.add("AT__00LT0N");

		Set<String> lgortSet =
				retailNameList.stream().map(v -> v.substring(v.length() - 4)).collect(Collectors.toSet());

		System.out.println(lgortSet);


	}

	@Test
	public void test3(){
		System.out.println(LocalDate.now().atStartOfDay(ZoneOffset.systemDefault()).toInstant().getEpochSecond());
	}
}
