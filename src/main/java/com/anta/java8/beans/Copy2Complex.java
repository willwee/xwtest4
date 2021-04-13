package com.anta.java8.beans;

/**
 * @author xiaowei
 * @date 2021/4/13 9:31
 */
public class Copy2Complex {

	private String str1;

	private Double db1;

	private String db3;

	private CopyCommon common;

	public CopyCommon getCommon() {
		return common;
	}

	public void setCommon(CopyCommon common) {
		this.common = common;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public Double getDb1() {
		return db1;
	}

	public void setDb1(Double db1) {
		this.db1 = db1;
	}

	public String getDb3() {
		return db3;
	}

	public void setDb3(String db3) {
		this.db3 = db3;
	}

	@Override
	public String toString() {
		return "Copy2Complex{" +
				"str1='" + str1 + '\'' +
				", db1=" + db1 +
				", db3='" + db3 + '\'' +
				", common=" + common.toString() +
				'}';
	}
}
