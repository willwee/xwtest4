package com.anta.java8.completablefuture.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 功能描述
 *
 * @author xiaowei
 * @date 2020-05-06
 **/
public class ThreadSafe {

    class Person {
        int    id;
        String name;
        String sex;
        float  height;
        Long tt;

        public Person(int id, String name, String sex, float height) {
            this.id = id;
            this.name = name;
            this.sex = sex;
            this.height = height;
        }
    }

    /**
     * 构造数据
     *
     * @return
     */
    public List<Person> constructPersons() {

        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 5; i++) {
            Person p = new Person(i, "name" + i, "sex" + i, i);
            persons.add(p);
        }
        return persons;
    }

    /**
     * 构造线程安全测试数据
     *
     * @return
     */
    public List<Person> constructThreadSafeTestPersons() {

        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 10000; i++) {
            Person p = new Person(i, "name" + i, "sex" + i, i);
            persons.add(p);
        }
        return persons;
    }

    /**
     * for
     *
     * @param persons
     */
    @Test
    public void doFor() {

        List<Person> persons = this.constructPersons();
        long start = System.currentTimeMillis();

        for (Person p : persons) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println(p.name);
        }

        long end = System.currentTimeMillis();
        System.out.println("doFor cost:" + (end - start));
    }

    /**
     * 顺序流
     *
     * @param persons
     */
    @Test
    public void doStream() {
        List<Person> persons = this.constructPersons();
        long start = System.currentTimeMillis();

        persons.stream().forEach(x -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println(x.name);
        });

        long end = System.currentTimeMillis();
        System.out.println("doStream cost:" + (end - start));
    }

    /**
     * 并行流
     *
     * @param persons
     */
    @Test
    public void doParallelStream() {
        List<Person> persons = this.constructPersons();
        long start = System.currentTimeMillis();

        persons.parallelStream().forEach(x -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println(x.name);
        });

        long end = System.currentTimeMillis();

        System.out.println("doParallelStream cost:" + (end - start));
    }


    /**
     * 测试线程 非安全数据
     */
    @Test
    public void doThreadUnSafeParallelStream() {
        List<Person> persons = this.constructThreadSafeTestPersons();
        long start = System.currentTimeMillis();

        List<Person> unSafeList = new ArrayList<>();

        persons.parallelStream().forEach(x -> {
            unSafeList.add(x);
        });

        long end = System.currentTimeMillis();

        System.out.println("doParallelStream cost:" + (end - start)+"::length::"+unSafeList.size());
    }

    /**
     * 测试线程 安全数据
     */
    @Test
    public void doThreadSafeParallelStream() {
        List<Person> persons = this.constructThreadSafeTestPersons();
        long start = System.currentTimeMillis();

        // 构造线程安全结构，数据越大偏差越大
        List<Person> safeList =  Collections.synchronizedList(new ArrayList<>());

        persons.parallelStream().forEach(x -> {
            safeList.add(x);
        });

        long end = System.currentTimeMillis();

        System.out.println("doParallelStream cost:" + (end - start)+"::length::"+safeList.size());
    }

    /**
     * 测试线程 安全数据
     */
    @Test
    public void testLong() {
        Person p = new Person(1,"2","3",4);

        System.out.println(p.tt!=null && p.tt>0);
    }

}
