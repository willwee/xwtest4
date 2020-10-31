package com.anta.java8.multithread;

import com.anta.java8.IWTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author xiaowei
 * @date 2020/10/23 9:19
 */
@Slf4j
public class ThreadTest1 implements IWTest {

	@Test
	public void test1(){

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		// 使用 Callable ，可以获取返回值
		Callable<String> callable = () -> {
			log.info("进入 Callable 的 call 方法");
			// 模拟子线程任务，在此睡眠 2s，
			// 小细节：由于 call 方法会抛出 Exception，这里不用像使用 Runnable 的run 方法那样 try/catch 了
			Thread.sleep(5000);
			return "Hello from Callable";
		};

		log.info("提交 Callable 到线程池");
		Future<String> future = executorService.submit(callable);

		log.info("主线程继续执行");

		log.info("主线程等待获取 Future 结果");
		// Future.get() blocks until the result is available
		try {
			String result = future.get();
			log.info("主线程获取到 Future 结果: {}", result);
		} catch (InterruptedException e) {
//			e.printStackTrace();
		} catch (ExecutionException e) {
//			e.printStackTrace();
		}

		executorService.shutdown();

	}

}
