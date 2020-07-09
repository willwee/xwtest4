package com.anta.java8.jvmdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaowei
 * @date 2020/7/1 16:29
 */
public class JvmBigThread extends Thread {

	public void run() {
		List<StaticObject> list = new ArrayList<StaticObject>();
		int i = 1;

		//不断的向堆中添加对象
		while (true) {
			list.add(new StaticObject());
			i++;
			System.out.println(Thread.currentThread().getName() +"big-thread" + i);
		}
	}
}
