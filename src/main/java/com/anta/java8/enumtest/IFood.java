package com.anta.java8.enumtest;

/**
 * @author xiaowei
 * @date 2020/10/22 13:14
 */
public interface IFood {
	enum CoffeeEnum implements IFood{
		BLACK_COFFEE,DECAF_COFFEE,LATTE,CAPPUCCINO
	}
	enum DessertEnum implements IFood{
		FRUIT, CAKE, GELATO
	}

}
