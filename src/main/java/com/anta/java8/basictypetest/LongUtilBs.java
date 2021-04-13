package com.anta.java8.basictypetest;

public class LongUtilBs {

	public static Long parse(Object o) {
		if (o == null)
			return 0l;
		try {
			return Long.valueOf(String.valueOf(o));
		} catch (Exception e) {
			return 0l;
		}
	}

}
