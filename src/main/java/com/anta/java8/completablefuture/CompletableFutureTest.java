package com.anta.java8.completablefuture;

import com.anta.java8.IWTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author xiaowei
 * @date 2020/10/23 10:09
 */
public class CompletableFutureTest implements IWTest {

	private final Map<Long, String> db = LongStream.rangeClosed(1, 4).boxed()
			.collect(Collectors.toMap(id -> id, id -> "user"+id));

	CompletableFutureTest() {}

	private static <T> T slowDown(String op, T result) {
		LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(500));
		System.out.println(op + " -> " + result + " thread: "
				+ Thread.currentThread().getName()+ ", "
				+ POOL.getPoolSize() + " threads");
		return result;
	}
	private CompletableFuture<List<Long>> returnUserIdsFromDb() {
		System.out.println("trigger building the list of Ids - thread: "
				+ Thread.currentThread().getName());
		return CompletableFuture.supplyAsync(
				() -> slowDown("building the list of Ids", Arrays.asList(1L, 2L, 3L, 4L)),
				POOL);
	}
	private CompletableFuture<String> fetchById(Long id) {
		System.out.println("trigger fetching id: " + id + " thread: "
				+ Thread.currentThread().getName());
		return CompletableFuture.supplyAsync(
				() -> slowDown("fetching id: " + id , db.get(id)), POOL);
	}

	static ForkJoinPool POOL = new ForkJoinPool(2);

	/**
	 * 区别在于，首先提交所有异步作业，然后安排一个对它们调用join的依赖操作，仅在所有作业完成后才执行，因此这些join调用将永远不会阻塞。 只有main方法末尾的最后一个join调用可以阻塞主线程。
	 * 显示无需创建补偿线程，因此线程数与配置的目标并行度匹配。
	 * 请注意，如果实际工作是在后台线程中完成的，而不是在fetchById方法本身中完成的，那么您现在不再需要并行流，因为没有阻塞的join()调用。 对于这种情况，仅使用stream()通常会导致更高的性能
	 * @param args
	 */

	public static void main(String[] args) {
		CompletableFutureTest example = new CompletableFutureTest();

		CompletableFuture<List<String>> result = example.returnUserIdsFromDb()
				.thenComposeAsync(listOfIds -> {
							List<CompletableFuture<String>> jobs = listOfIds.parallelStream()
									.map(id -> example.fetchById(id))
									.collect(Collectors.toList());
							return CompletableFuture.allOf(jobs.toArray(new CompletableFuture<?>[0]))
									.thenApply(_void -> jobs.stream()
											.map(CompletableFuture::join).collect(Collectors.toList()));
						},
						POOL
				);

		System.out.println(result.join());
		System.out.println(ForkJoinPool.commonPool().getPoolSize());
	}


}
