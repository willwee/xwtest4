package com.anta.java8.enumtest;

import com.anta.java8.IWTest;
import org.junit.Test;

import java.util.Optional;

/**
 *
 * https://zhuanlan.zhihu.com/p/109766942
 * enum类和static常量区别：
 * 在使用方式效果上来看是没有区别的，其实enum类的每一个枚举值也是 static final的，但是我们为什么要选择使用enum枚举类呢
 * 1.static方式的静态变量类型不安全，我们可以在调用的时候传入其他值，导致错误
 * 例如： seventhDay.tellItLikeItIs(999);
 * 2.static方式的静态变量不支持属性扩展，每一个key对应一个值，而enum的每一个key可以拥有自己的属性
 *
 * 7种常见用法
 * 用法一：常量
 * 用法二：switch
 * 用法三：向枚举中添加新方法
 * 用法四：覆盖枚举的方法
 * 用法五：实现接口
 * 用法六：使用接口组织枚举
 * 用法七：关于枚举集合的使用s
 *
 * @author xiaowei
 * @date 2020/6/30 13:20
 */
public class EnumTest implements IWTest {
	// 用法一：常量
	enum Color
	{
		RED, GREEN, BLUE;
	}

    // 用法二：switch
	enum Day {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY,THURSDAY, FRIDAY, SATURDAY
	}

	// 用法三：向枚举中添加新方法  用法四：覆盖枚举的方法
	enum DayMethod {
		SUNDAY(1, "周一"), MONDAY1(1, "周一"), TUESDAY(1, "周一"), WEDNESDAY(1, "周一"), THURSDAY(1, "周一"), FRIDAY(1, "周一"),
		SATURDAY(1, "周一"),
		;

		private int index;
		private String name;

		DayMethod(int index, String name) {
			this.index = index;
			this.name = name;
		}

		// 向枚举中添加新方法
		public static String getName(DayMethod day) {
			switch (day) {
				case SUNDAY:
					return day.index + day.name;
				default:
					return MONDAY1.index + MONDAY1.name;
			}
		}

		// 覆盖枚举的方法
		@Override
		public String toString(){
			return this.index + this.name;
		}
	}

	// 用法五：实现接口
	public enum ColorImpl implements IBehaviour {
		RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private ColorImpl(String name, int index) {
			this.name = name;
			this.index = index;
		}

		//接口方法
		@Override
		public String getInfo() {
			return this.name;
		}

		//接口方法
		@Override
		public void print() {
			System.out.println(this.index + ":" + this.name);
		}
	}

   // 用法六：使用接口组织枚举
    @Test
	public void testEnum(){

	    for (IFood.DessertEnum dessertEnum : IFood.DessertEnum.values()) {
		    System.out.print(dessertEnum + "  ");
	    }
	    //搞个实现接口，来组织枚举，简单讲，就是分类吧。如果大量使用枚举的话，这么干，在写代码的时候，就很方便调用啦。
	    //还有就是个“多态”的功能吧，
	    IFood food = IFood.DessertEnum.CAKE;
	    System.out.println(food);

    }

	enum DayExt {
		MONDAY(1, "星期一", "星期一各种不在状态"),
		TUESDAY(2, "星期二", "星期二依旧犯困"),
		WEDNESDAY(3, "星期三", "星期三感觉半周终于过去了"),
		THURSDAY(4, "星期四", "星期四期待这星期五"),
		FRIDAY(5, "星期五", "星期五感觉还不错"),
		SATURDAY(6, "星期六", "星期六感觉非常好"),
		SUNDAY(7, "星期日", "星期日感觉周末还没过够。。。");

		DayExt(int index, String name, String value) {
			this.index = index;
			this.name = name;
			this.value = value;
		}

		private int index;
		private String name;
		private String value;

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}


	@Test
	public void testColor(){
		Color c1 = Color.RED;
		System.out.println(c1);
	}

	@Test
	public void testDay(){
		Day day = Day.FRIDAY;
		switch (day) {
			case MONDAY:
				System.out.println("周一各种不在状态");
				break;

			case FRIDAY:
				System.out.println("周五感觉还不错");
				break;

			case SATURDAY: case SUNDAY:
				System.out.println("周末给人的感觉是最棒的");
				break;

			default:
				System.out.println("周内感觉就那样吧。。。");
				break;
		}
	}

	@Test
	public void testDayExt(){
		DayExt day = DayExt.FRIDAY;
		switch (day) {
			case MONDAY:
				System.out.println(day.getName()+day.getValue());
				break;

			case FRIDAY:
				System.out.println(day.getName()+day.getValue());
				break;

			case SATURDAY: case SUNDAY:
				System.out.println(day.getName()+day.getValue());
				break;

			default:
				System.out.println(day.getName()+day.getValue());
				break;
		}
	}


}
