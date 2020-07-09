package com.anta.java8.jvmdemo;

import com.anta.java8.IWTest;
import org.junit.Test;

/**
 * @author xiaowei
 * @date 2020/7/3 23:07
 */
public class DeadLockDemo implements IWTest {

	private static String A="A";
	private static String B="B";

	@Test
	public void testDeadLock(){
		this.deadLock();
	}
	private void deadLock(){
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized(A){
					try{
						Thread.currentThread().sleep(2000);
					}catch (InterruptedException e){
						e.printStackTrace();
					}

					synchronized(B){
						System.out.println("1");
					}
				}
			}
		});

		Thread t2= new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized(B){
					synchronized(A){
						System.out.println("2");
					}
				}
			}
		});

		t1.start();
		t2.start();

		//阻塞Main线程，执行子线程t1和t2，完毕后继续执行后续的逻辑
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("run next process.");
	}
}
