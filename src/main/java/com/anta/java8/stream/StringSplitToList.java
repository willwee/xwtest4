package com.anta.java8.stream;

import com.anta.java8.IWTest;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaowei
 * @date 2020/5/22 14:30
 */
public class StringSplitToList implements IWTest {

    @Test
    public void test(){
        String ids= "1,2,3,4,5,6";
        List<Long> listIds = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        System.out.println(Arrays.toString(listIds .toArray()));//[1,2,3,3,4,5,6]

    }
}
