package com.anta.java8.completablefuture.list;

import com.anta.java8.IWTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaowei
 * @date 2020/7/7 18:05
 */
public class ListDataComb implements IWTest {

	@Test
	public void testDecimal(){
		BigDecimal bb = new BigDecimal("-1233");
		BigDecimal bb2 = new BigDecimal("123");

		System.out.println(bb.add(bb2));
	}

	/**
	 * java8 list合并相同属性的值
	 */
	@Test
	public void test() {

		List<Student> list = new ArrayList<>();
		list.add(new Student("张三", "16", 80));
		list.add(new Student("张三", "16", 70));
		list.add(new Student("张三", "16", -90));
		list.add(new Student("张三", "16", 60));
		list.add(new Student("张三", "16", 50));
		list.add(new Student("李四", "18", 80));

		List<Student> stList = new ArrayList<>();
		list.parallelStream().collect(Collectors.groupingBy(v->v.getName()+v.getAge(),Collectors.toList())).forEach((id,transfer) -> {
           transfer.stream().reduce((a,b) -> new Student(a.getName(),a.getAge(),a.getScore()+b.getScore())).ifPresent(stList::add);
		});
		System.out.println("stuList:::"+stList);
	}

	private class Student{
		private String name;
		private String age;
		private int score;


		public Student(String name,String age,int score) {
			this.name = name;
			this.age = age;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}
	}
}
