package com.anta.java8.lambda;

import com.anta.java8.IWTest;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author xiaowei
 * @date 2020/6/11 19:33
 */
public class ListSample implements IWTest {

	/**
	 * 字符串list按某个属性去重
	 */
	@Test
	public void test1() {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("2");
		list.add("3");
		List unique = list.stream().distinct().collect(Collectors.toList());
		unique.stream().forEach(System.out::println);
	}

	@Test
	public void test() {

		List<Person> persons = this.createPersons();

		// 根据name,sex两个属性去重
		List<Person> unique = persons.stream().collect(
				Collectors.collectingAndThen(
						Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getName() + ";" + o.getSex()))), ArrayList::new)
		);


		unique.stream().forEach(v -> {
			System.out.println(v.getId());
		});

	}

	@Test
	public void testMapTraverse() {
		List<Person> persons = this.createPersons();
		Map<String, List<Person>> maps = new HashMap<>();
		maps.put("person1", persons);

		maps.values().stream().forEach(v -> {
			System.out.println(v.get(0).getId());
		});


		maps.entrySet().stream().forEach(v -> {
			System.out.println(v.getKey());
			System.out.println(v.getValue().get(0).getId());
		});
	}

	private List<Person> createPersons() {

		Person person1 = new Person();
		person1.setName("xw");
		person1.setSex("man");
		person1.setId("02");

		Person person = new Person();
		person.setName("xw");
		person.setSex("man");
		person.setId("04");


		Person person2 = new Person();
		person2.setName("xw1");
		person2.setSex("man");
		person2.setId("03");

		List<Person> persons = new ArrayList<>();
		persons.add(person);
		persons.add(person1);
		persons.add(person2);
		return persons;
	}

	@Test
	public void test3() {

		Person person1 = new Person();
		person1.setName("xw");
		person1.setSex("man");
		person1.setId("02");

		Person person = new Person();
		person.setName("xw");
		person.setSex("man");
		person.setId("04");


		Person person2 = new Person();
		person2.setName("xw1");
		person2.setSex("man");
		person2.setId("03");

		List<Person> persons = new ArrayList<>();
		persons.add(person);
		persons.add(person1);
		persons.add(person2);

		persons.parallelStream().filter(distinctByKey(Person::getName))
				.forEach(v -> {
					System.out.println(v.getId());
				});


	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}


	// Person 对象
	class Person {
		private String id;

		private String name;

		private String sex;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}
	}
}
