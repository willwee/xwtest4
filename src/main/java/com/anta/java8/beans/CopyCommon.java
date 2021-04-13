package com.anta.java8.beans;

/**
 * @author xiaowei
 * @date 2021/4/13 13:41
 */
public class CopyCommon {

	private String a;

	private String b;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "CopyCommon{" +
				"a='" + a + '\'' +
				", b='" + b + '\'' +
				'}';
	}
}
