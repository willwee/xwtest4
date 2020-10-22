package com.anta.java8.completablefuture.list;

import com.anta.java8.IWTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaowei
 * @date 2020/7/24 15:23
 */
public class ListLambda implements IWTest {

	@Test
	public void test1(){
		List<String> setMethodNames = new ArrayList<>();
		setMethodNames.add("setMmmmm");
		setMethodNames.add("setUioi");
		setMethodNames.add("setIooo");
		setMethodNames.add("setOoo");

		boolean b = setMethodNames.stream().anyMatch((setMethodName) -> setMethodName.substring(3) == "20");

	}

	@Test
	public void test2(){
		String s = "set";
		System.out.println(s.length());
		System.out.println(s.substring(3));
	}
}
