package com.anta.java8.annotationtest;

import com.anta.java8.IWTest;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.junit.Test;

/**
 *
 * @NonNull可以标注在方法、字段、参数之上，表示对应的值不可以为空
 * @Nullable注解可以标注在方法、字段、参数之上，表示对应的值可以为空
 * 以上两个注解在程序运行的过程中不会起任何作用，只会在IDE、编译器、FindBugs检查、生成文档的时候有做提示；我使用的IDE是STS，不会做自动的检查，只有安装了FindBugs插件并运行后会做对应的提示
 * @author xiaowei
 * @date 2020/6/17 8:45
 */
public class AnnotationDemo implements IWTest {

	@Test
	public void test1() {
		Object obj = "kk";
		int i = 58;
		System.out.println(i/10);
	}

	/**
	 * @Nullable 表示可以为空
	 * @param obj
	 * @return
	 */
	private String nullAble(@Nullable Object obj) {
		return obj.toString();

	}
	/**
	 * @NotNull 表示不能为空
	 * @param obj
	 * @return
	 */
	private String notNull(@NotNull Object obj) {
		return obj.toString();

	}

}
