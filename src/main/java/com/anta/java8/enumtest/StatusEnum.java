package com.anta.java8.enumtest;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 状态等 枚举最佳实践
 * @author xiaowei
 * @date 2021/4/12 15:17
 */
public enum StatusEnum {
	UNCONFIRMED(0,"未确认"), CONFIRMED(1,"已确认"),COMPLETE(8,"已完成"), TREM(9,"终止");

	private Integer status;

	private String name;

	StatusEnum(Integer status,String name){
		this.name = name;
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	// Implementing a fromString method on an enum type
	private static final Map<Integer, StatusEnum> statusToEnum =
			Stream.of(StatusEnum.values()).collect(Collectors.toMap(StatusEnum::getStatus, e -> e));

	// Returns Operation for string, if any
	public static Optional<StatusEnum> fromStatus(Integer dbStatus) {
		return Optional.ofNullable(statusToEnum.get(dbStatus));
	}


}