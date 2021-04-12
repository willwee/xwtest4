package com.anta.java8.stream;

import com.anta.java8.IWTest;
import com.anta.java8.string.StringFormatTest;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xiaowei
 * @date 2020/6/15 19:26
 */
public class ListToMap implements IWTest {

//	常用方式
//	代码如下：

	public Map<Long, String> getIdNameMap(List<Account> accounts) {
		return accounts.stream().collect(Collectors.toMap(Account::getId, Account::getUserName));
	}
//	收集成实体本身map
//	代码如下：

	public Map<Long, Account> getIdAccountMap(List<Account> accounts) {
		return accounts.stream().collect(Collectors.toMap(Account::getId, account -> account));
	}
//	account -> account是一个返回本身的lambda表达式，其实还可以使用Function接口中的一个默认方法代替，使整个方法更简洁优雅：

	public Map<Long, Account> getIdAccountMap(List<Account> accounts, String type) {
		return accounts.stream().collect(Collectors.toMap(Account::getId, Function.identity()));
	}
//	重复key的情况
//	代码如下：

	public Map<String, Account> getNameAccountMap(List<Account> accounts) {
		return accounts.stream().collect(Collectors.toMap(Account::getUserName, Function.identity()));
	}
//	这个方法可能报错（java.lang.IllegalStateException: Duplicate key），因为name是有可能重复的。toMap有个重载方法，可以传入一个合并的函数来解决key冲突问题：

	public Map<String, Account> getNameAccountMap(List<Account> accounts, String type) {
		return accounts.stream().collect(Collectors.toMap(Account::getUserName, Function.identity(),
				(key1, key2) -> key2));
	}

	//	这里只是简单的使用后者覆盖前者来解决key重复问题。还有一种分组的方法：
	public void test() {
		List<ActivityUserMissionDO> activityUserMissionDos = new ArrayList<>();
		Map<Long, List<ActivityUserMissionDO>> map = activityUserMissionDos.stream().collect(Collectors.groupingBy(ActivityUserMissionDO::getParentModuleId));
	}


	/**
	 * 指定具体收集的map
	 * toMap还有另一个重载方法，可以指定一个Map的具体实现，来收集数据：
	 * 如果重复选择保留一个
	 * @param accounts
	 * @param type
	 * @return
	 */
	public Map<String, Account> getNameAccountMap(List<Account> accounts, Long type) {
		return accounts.stream().collect(Collectors.toMap(Account::getUserName, Function.identity(),
				(key1, key2) -> key2, LinkedHashMap::new));
	}


	/**
	 * 指定具体收集的map
	 * toMap还有另一个重载方法，可以指定一个Map的具体实现，来收集数据：
	 * 如果重复选择保留一个
	 * @param accounts
	 * @param type
	 * @return
	 */
	public Map<String, Account> getNameAccountMapUniq(List<Account> accounts, Long type) {
		return accounts.stream().collect(Collectors.toMap(Account::getUserName, Function.identity(),
				(key1, key2) -> key2));
	}


	private class ActivityUserMissionDO {
		private Long parentModuleId;

		public Long getParentModuleId() {
			return parentModuleId;
		}

		public void setParentModuleId(Long parentModuleId) {
			this.parentModuleId = parentModuleId;
		}
	}

	@Test
	public void test1(){

		List<Account> tt = new ArrayList<>();

		Account t1 = new Account();
		t1.setId(1L);
		t1.setAreaId(1L);
		t1.setUserName("xx1");
		tt.add(t1);

		Account t2 = new Account();
		t2.setId(1L);
		t2.setAreaId(2L);
		t2.setUserName("xx2");
		tt.add(t2);

		Account t3 = new Account();
		t3.setId(3L);
		t3.setAreaId(1L);
		t3.setUserName("xx1");
		tt.add(t3);

		Account t4 = new Account();
		t4.setId(4L);
		t4.setAreaId(1L);
		t4.setUserName("xx1");
		tt.add(t4);

		Map<Long,Account> map = tt.stream().collect(Collectors.toMap(Account::getId,Function.identity(),
				(item1, item2) -> item2));

		System.out.println(tt.size());


	}


	private class Account {

		private Long areaId;

		private String userName;

		private Long id;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public Long getAreaId() {
			return areaId;
		}

		public void setAreaId(Long areaId) {
			this.areaId = areaId;
		}
	}


	@Test
	public void test19(){
		List<Data> ll = new ArrayList<>();
		Data d1 = new Data("jjwhFXSC","6901776911382",1604648346L);
		Data d2 = new Data("jjwhFXSC","6901776911382",1604648287L);
		Data d3 = new Data("jjwhFXSC","6901776911382",1604648226L);
		Data d4 = new Data("jjwhFXSC","6919370887835",1604648346L);
		Data d5 = new Data("jjwhFXSC","6919370887835",1604648347L);
//		Data d6 = new Data("CK_SF_QZC_2_SX","6919370887835",1604648347L);
//		Data d7 = new Data("CK_SF_QZC_2_SX","6901776911382",1604648346L);
		ll.add(d1);
		ll.add(d2);
		ll.add(d3);
		ll.add(d4);
		ll.add(d5);
//		ll.add(d6);
//		ll.add(d7);

		Map<String,List<Data>> list = ll.stream().collect(Collectors.groupingBy(Data::getBarcode));

		list.forEach((k,v) -> {
			System.out.println(MessageFormat.format("遍历map.key{0},values{1}",k,v.size()));
		});

		System.out.println("");

	}


	@Test
	public void test20(){
		List<Map<String,Object>> ll = new ArrayList<>();

		Map<String,Object> l1 = new HashMap();
		l1.put("ordser_sn","111");
		l1.put("ordser_sn1","111");
		l1.put("ordser_sn2","111");
		l1.put("ordser_sn3","111");
		Map<String,Object> l2 = new HashMap();
		l2.put("ordser_sn","111");
		l2.put("ordser_sn1","111");
		l2.put("ordser_sn2","111");
		l2.put("ordser_sn3","111");
		Map<String,Object> l3 = new HashMap();
		l3.put("ordser_sn","112");
		l3.put("ordser_sn1","111");
		l3.put("ordser_sn2","111");
		l3.put("ordser_sn3","111");
		Map<String,Object> l4 = new HashMap();
		l4.put("ordser_sn","113");
		l4.put("ordser_sn1","111");
		l4.put("ordser_sn2","111");
		l4.put("ordser_sn3","111");


		ll.add(l1);
		ll.add(l2);
		ll.add(l3);
		ll.add(l4);
		ll.add(null);
		ll.add(null);

		//1.第一种方法
		Map<String, List<Map<String, Object>>> groupMap1 =
				ll.stream().filter(Objects::nonNull).collect(Collectors.groupingBy(x->x.get("ordser_sn").toString()));

		Set<String> testSet = groupMap1.keySet();

		Lists.partition(testSet.stream().collect(Collectors.toList()), 100).stream().forEach(billNoList -> {
			List<Map<String, Object>> list = new ArrayList<>();

			billNoList.stream().forEach(v -> {
				try {
					list.addAll(groupMap1.get(v.toString()));
					System.out.println("sds");
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("");
			});
		});

		//2.第二种方法
		Function<Map<String,Object>,String> s = new Function<Map<String, Object>, String>() {
			@Override
			public String apply(Map<String, Object> map) {
				Object o = map.get("ordser_sn");
				return o.toString();
			}
		};
		Map<String, List<Map<String, Object>>> groupMap2  = ll.stream().collect(Collectors.groupingBy(s));

		groupMap2.size();



		groupMap1.forEach((k,v) -> {
			System.out.println(MessageFormat.format("遍历map.key{0},values{1}",k,v.size()));
		});

		System.out.println("");

	}


	@Test
	public void test9(){
		List<Data> ll = new ArrayList<>();
		Data d1 = new Data("jjwhFXSC","6901776911382",1604648346L);
		Data d2 = new Data("jjwhFXSC","6901776911382",1604648287L);
		Data d3 = new Data("jjwhFXSC","6901776911382",1604648226L);
		Data d4 = new Data("jjwhFXSC","6919370887835",1604648346L);
		Data d5 = new Data("jjwhFXSC","6919370887835",1604648347L);
//		Data d6 = new Data("CK_SF_QZC_2_SX","6919370887835",1604648347L);
//		Data d7 = new Data("CK_SF_QZC_2_SX","6901776911382",1604648346L);
		ll.add(d1);
		ll.add(d2);
		ll.add(d3);
		ll.add(d4);
		ll.add(d5);
//		ll.add(d6);
//		ll.add(d7);
		Map<String,Data> ll2= ll.stream().collect(Collectors.groupingBy(Data::getBarcode,
				Collectors.collectingAndThen(Collectors.reducing(( c1, c2) -> c1.getDjrq() > c2.getDjrq() ? c1 : c2), Optional::get)));
		List<Data> dds = ll2.values().stream().collect(Collectors.toList());

		System.out.println("");

	}


	static class Data {
		String ckdm;
		String barcode;
		Long djrq;

		Data(String ckdm,String barcode,Long djrq){
			this.barcode = barcode;
			this.ckdm = ckdm;
			this.djrq = djrq;

		}

		public String getCkdm() {
			return ckdm;
		}

		public void setCkdm(String ckdm) {
			this.ckdm = ckdm;
		}

		public String getBarcode() {
			return barcode;
		}

		public void setBarcode(String barcode) {
			this.barcode = barcode;
		}

		public Long getDjrq() {
			return djrq;
		}

		public void setDjrq(Long djrq) {
			this.djrq = djrq;
		}
	}

}


