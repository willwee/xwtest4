package com.anta.java8.stream;

import com.anta.java8.IWTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xiaowei
 * @date 2020/9/11 8:56
 */
public class ListToArray implements IWTest {

	@Test
	public void arrayToList(){
      //数组转换为List
		String[] arrays = {"a", "b", "c"};
		List<String> listStrings = Stream.of(arrays).collect(Collectors.toList());

		System.out.println(listStrings.size());

	}

	@Test
	public void listToArray(){
		//数组转换为List
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		String[] strings = list.stream().toArray(String[]::new);
		System.out.println(strings.length);

	}

	@Test
	public void test2(){
		List<Long> ss = Arrays.asList(12L);

		ss.stream().forEach(System.out::println);
	}

}
