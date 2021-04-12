package com.anta.java8.beans;

/**
 * @author xiaowei
 * @date 2021/4/12 17:18
 */
public class Copy1 {

	private String str1;

	private Double db1;

	private Long id;

	private float f1;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getF1() {
		return f1;
	}

	public void setF1(float f1) {
		this.f1 = f1;
	}

	@Override
	public String toString() {
		return "Copy1{" +
				"str1='" + str1 + '\'' +
				", db1=" + db1 +
				", id=" + id +
				", f1=" + f1 +
				'}';
	}
}
