package com.anta.java8;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author xiaowei
 * @date 2020/6/30 13:20
 */
public class OptionalTest implements IWTest {

	@Test
	public void  test1(){

		// 传统写法
		Person person = null;
		String brand = null;
		if (null != person){
			if (null != person.getCar()){
				if (null != person.getCar().getWheel()){
					brand = person.getCar().getWheel().getBrand();
				}
			}
		}

		// java8 新的写法
		brand = Optional.ofNullable(person) //转换为Optional进行包裹
			    .map(p -> p.getCar()) //获取Car对象
		        .map(car -> car.getWheel()) //获取Wheel对象
				.map(wheel -> wheel.getBrand()) //获取brand
		        .orElse("玛莎拉蒂"); //如果中间有对象为null，则返回默认值"玛莎拉蒂"

	}


	@Test
	public void test2() {
		StringBuilder sql = new StringBuilder("select s.order_sn, s.fx_sap_status ");
		sql.append("from stm_retail_settle_status s ");
		sql.append("join stm_retail_settle r on s.order_sn = r.order_sn ");
		sql.append("where s.push_system in ('FX_SAP', 'ACE') ");
		sql.append("and s.fx_sap_status in (0, 2) ");
		sql.append("and r.store_channel <> '4' ");
		sql.append("and r.warehouse_type <> '1' ");
		System.out.println(sql.toString());
	}


	@Test
	public void test3(){
		String ss = "asdf,dfasd";

		// 加盟商货通改造 异步插入待处理中间表
		CompletableFuture.runAsync(()->{
			this.testAsync();
		});

		String[] ssa = ss.split(",");
		System.out.println(ssa.length);
	}

	private void testAsync(){
		System.out.println("sdfsd");
	}


	@Test
	public void test4(){
		String ss = "asdf,dfasd";
		List<String> ss2  = Arrays.stream(StringUtils.split(ss,",")).collect(Collectors.toList());

		List<String> ss3 = ss2.stream().filter(v -> "asdf1".equals(v)).collect(Collectors.toList());


		System.out.println(String.format("ss2 大小为 %s,ss3大小为 %s",ss2.size(),ss3.size()));
	}

	@Test
	public void test5(){
		System.out.println(String.format("sap客户档案 %s映射为空，请检查！","aap"));
	}

	static class Wheel {
		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		private String brand;
	}

	static class Car {
		public Wheel getWheel() {
			return wheel;
		}

		public void setWheel(Wheel wheel) {
			this.wheel = wheel;
		}
		private Wheel wheel;
	}

	static class Person {
		public Car getCar() {
			return Car;
		}

		public void setCar(Car Car) {
			this.Car = Car;
		}

		private Car Car;
	}

}
