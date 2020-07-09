package com.anta.java8.stream;

import com.anta.java8.IWTest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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


//	指定具体收集的map
//	toMap还有另一个重载方法，可以指定一个Map的具体实现，来收集数据：

	public Map<String, Account> getNameAccountMap(List<Account> accounts, Long type) {
		return accounts.stream().collect(Collectors.toMap(Account::getUserName, Function.identity(),
				(key1, key2) -> key2, LinkedHashMap::new));
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

}


