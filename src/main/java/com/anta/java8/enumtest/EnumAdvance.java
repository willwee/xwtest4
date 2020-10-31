package com.anta.java8.enumtest;

import org.junit.Test;

/**
 * @author xiaowei
 * @date 2020/10/22 9:02
 */
public class EnumAdvance {

	Day day;

	public EnumAdvance(Day day) {
		this.day = day;
	}

	public void tellItLikeItIs() {
		switch (day) {
			case MONDAY:
				System.out.println(day.getName()+day.getValue());
				System.out.println(day.getName()+"的下一天是"+day.getNext().getName());
				break;

			case FRIDAY:
				System.out.println(day.getName()+day.getValue());
				System.out.println(day.getName()+"的下一天是"+day.getNext().getName());
				break;

			case SATURDAY: case SUNDAY:
				System.out.println(day.getName()+day.getValue());
				System.out.println(day.getName()+"的下一天是"+day.getNext().getName());
				break;

			default:
				System.out.println(day.getName()+day.getValue());
				System.out.println(day.getName()+"的下一天是"+day.getNext().getName());
				break;
		}
	}

	public static void main(String[] args) {
		EnumAdvance firstDay = new EnumAdvance(Day.MONDAY);
		firstDay.tellItLikeItIs();
		EnumAdvance thirdDay = new EnumAdvance(Day.WEDNESDAY);
		thirdDay.tellItLikeItIs();
		EnumAdvance fifthDay = new EnumAdvance(Day.FRIDAY);
		fifthDay.tellItLikeItIs();
		EnumAdvance sixthDay = new EnumAdvance(Day.SATURDAY);
		sixthDay.tellItLikeItIs();
		EnumAdvance seventhDay = new EnumAdvance(Day.SUNDAY);
		seventhDay.tellItLikeItIs();
	}

}
