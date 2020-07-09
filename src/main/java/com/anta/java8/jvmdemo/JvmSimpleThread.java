package com.anta.java8.jvmdemo;

/**
 * @author xiaowei
 * @date 2020/7/1 16:31
 */
public class JvmSimpleThread extends Thread {
	public void run() {
		int i = 1;

		while (true) {
			i++;
			System.out.println(Thread.currentThread().getName() +"simple-thread" + i);
		}
	}

}
