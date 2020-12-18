package com.anta.java8.nio;

import com.anta.java8.IWTest;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author xiaowei
 * @date 2020/11/19 8:48
 */
public class NioTest implements IWTest {


	@Test
	public void test1() throws Exception {
		//1. 创建两个流
		FileInputStream fis = new FileInputStream("D:\\02-Work\\01_Project\\xwtest4\\src\\main\\java\\com\\anta\\java8\\nio\\basic.text");
		FileOutputStream fos = new FileOutputStream("D:\\02-Work\\01_Project\\xwtest4\\src\\main\\java\\com\\anta" +
				"\\java8\\nio\\basic2.text");
		// 2. 得到两个通道
		FileChannel sourceFc = fis.getChannel();
		FileChannel destFc = fos.getChannel();
		//3. 复制
		destFc.transferFrom(sourceFc,0,sourceFc.size());
		//4.关闭
		fis.close();
		fos.close();
	}
}
