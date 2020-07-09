package com.anta.java8.volatiletest;

import com.anta.java8.IWTest;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * volatile 原子性问题，可见性问题，有序性问题
 * cpu 主存 缓存
 * jvm 内存 缓存
 *
 * @author xiaowei
 * @date 2020/6/10 10:29
 */
public class SynchronizeTest implements IWTest {
	public int inc = 0;

	public volatile int inc2 = 0;

	public synchronized void increase() {
		inc++;
	}

	/**
	 * 采用synchronize
	 */
	@Test
	public void testSynchronize() {
		final SynchronizeTest test = new SynchronizeTest();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase();
				}

				;
			}.start();
		}

		while (Thread.activeCount() > 1)  //保证前面的线程都执行完
			Thread.yield();
		System.out.println(test.inc);
	}


	public void increase2() {
		inc2++;
	}

	/**
	 * 采用Volatile
	 *
	 */
	@Test
	public void testVolatile() {
		final SynchronizeTest test = new SynchronizeTest();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase2();
				}

				;
			}.start();
		}

		while (Thread.activeCount() > 1)  //保证前面的线程都执行完
			Thread.yield();
		System.out.println(test.inc2);
	}


	public int inc3 = 0;
	Lock lock = new ReentrantLock();

	/**
	 * 采用Lock
	 */
	@Test
	public void testLock() {
		final SynchronizeTest test = new SynchronizeTest();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase3();
				}

				;
			}.start();
		}

		while (Thread.activeCount() > 1)  //保证前面的线程都执行完
			Thread.yield();
		System.out.println(test.inc3);
	}

	public void increase3() {
		lock.lock();
		try {
			inc3++;
		} finally {
			lock.unlock();
		}
	}


	public AtomicInteger inc4 = new AtomicInteger();
	public  void increase4() {
		inc4.getAndIncrement();
	}

	/**
	 * 采用AtomicInteger
	 */
	@Test
	public void testAtomicInteger(){
		final SynchronizeTest test = new SynchronizeTest();
		for(int i=0;i<10;i++){
			new Thread(){
				public void run() {
					for(int j=0;j<1000;j++)
						test.increase4();
				};
			}.start();
		}

		while(Thread.activeCount()>1)  //保证前面的线程都执行完
			Thread.yield();
		System.out.println(test.inc4);
	}

}
