package com.anta.java8.string;

import com.anta.java8.IWTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.context.annotation.Primary;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiaowei
 * @date 2020/10/29 8:24
 */
public class StringFormatTest implements IWTest {

	@Test
	public void test1(){
		String userName="XXX";
		String userProvince="上海";
		int userAge=21;
		String userSex="男";

		// $ 相当于变量 符号
		String string = " %1$s  用户来自 %2$s   年龄 %3$d  性别  %4$s   ";
		String userInfo=String.format(string,userName,userProvince,userAge,userSex);
		System.out.println(userInfo);
	}

	@Test
	public void test2(){
		String str=null;
		str=String.format("Hi,%s", "小超");
		System.out.println(str);
		str=String.format("Hi,%s %s %s", "小超","是个","大帅哥");
		System.out.println(str);
		System.out.printf("字母c的大写是：%c %n", 'C');
		System.out.printf("布尔结果是：%b %n", "小超".equals("帅哥"));
		System.out.printf("100的一半是：%d %n", 100/2);
		System.out.printf("100的16进制数是：%x %n", 100);
		System.out.printf("100的8进制数是：%o %n", 100);
		System.out.printf("50元的书打8.5折扣是：%f 元%n", 50*0.85);
		System.out.printf("上面价格的16进制数是：%a %n", 50*0.85);
		System.out.printf("上面价格的指数表示：%e %n", 50*0.85);
		System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50*0.85);
		System.out.printf("上面的折扣是%d%% %n", 85);
		System.out.printf("字母A的散列码是：%h %n", 'A');
	}

	@Test
	public void test3(){
		System.out.printf("50元的书打8.5折扣是：%d 元%n", 80);
		System.out.printf("年-月-日 HH:MM:SS 格式：%tF %<tT%n",new Date());
	}

	@Test
	public void test4(){
		String str=null;
		//$使用  相当于变量的感觉   String.format("格式参数$的使用：%1$d,%1$s", 99,"abc")  结果  格式参数$的使用：99,99
		// String.format("格式参数$的使用：%1$d,%2$s", 99,"abc")  结果  格式参数$的使用：99,abc
		str=String.format("格式参数$的使用：%1$d,%1$s", 99,"abc");
		System.out.println(str);
		//+使用
		System.out.printf("显示正负数的符号：%+d与%d%n", 99,-99);
		//补O使用
		System.out.printf("最牛的编号是：%03d%n", 7);
		//空格使用
		System.out.printf("Tab键的效果是：% 8d%n", 7);
		//.使用
		System.out.printf("整数分组的效果是：%,d%n", 9989997);
		//空格和小数点后面个数
		System.out.printf("一本书的价格是：% 50.5f元%n", 49.8);
	}

	@Test
	public void test5(){
		String s = "XNSZ";
		System.out.println(s.substring(s.length()-4));
	}

	@Test
	public void test6(){
		Set<String> orderSnSet = new HashSet<>();
		orderSnSet.add("01");
		orderSnSet.add("02");
		System.out.println(StringUtils.join(orderSnSet.toArray(), ","));
	}

	@Test
	public void test7(){
		StringBuilder sql = new StringBuilder("select s.order_sn, s.fx_sap_status ");
		sql.append("from stm_retail_settle_status s ");
		sql.append("join stm_retail_settle r on s.order_sn = r.order_sn ");
		sql.append("where s.push_system = 'FX_SAP' ");
		sql.append(" and s.fx_sap_status in (0, 2) ");
		sql.append(" and (r.store_channel <> '4' or r.warehouse_type <> '1') ");

		System.out.println(sql.toString());
	}

	@Test
	 public void test8(){
		//1. 可以在中括号内加上任何想要删除的字符，实际上是一个正则表达式
		String regExp="[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";

		//2. 这里是将特殊字符换为空字符串,""代表直接去掉
		String replace = " ";

		//3. 要处理的字符串
		String str = "HSFServiceAddressNotFoundException-\n" +
				"error message : [HSF-Consumer] can't find target service addresses, target serviceName:com.baison.e3.middleware.gateway.api.wms.settlement.IFxsSettlementToPosServi";
		str = str.replaceAll(regExp,replace);
		System.out.println("去除特殊字符后的值："+ str);
	 }

	@Test
	public void test9(){
		List<Data> ll = new ArrayList<>();
		Data d1 = new Data("jjwhFXSC","6901776911382",1604648346L);
		Data d2 = new Data("jjwhFXSC","6901776911382",1604648287L);
		Data d3 = new Data("jjwhFXSC","6901776911382",1604648226L);
		Data d4 = new Data("jjwhFXSC","6919370887835",1604648346L);
		Data d5 = new Data("jjwhFXSC","6919370887835",1604648347L);
//		Data d6 = new Data("CK_SF_QZC_2_SX","6919370887835",1604648347L);
//		Data d7 = new Data("CK_SF_QZC_2_SX","6901776911382",1604648346L);
//		ll.add(d1);
//		ll.add(d2);
//		ll.add(d3);
//		ll.add(d4);
//		ll.add(d5);
//		ll.add(d6);
//		ll.add(d7);
		Map<String,Data> ll2= ll.stream().collect(Collectors.groupingBy(Data::getBarcode,
				Collectors.collectingAndThen(Collectors.reducing(( c1, c2) -> c1.getDjrq() > c2.getDjrq() ? c1 : c2), Optional::get)));
		List<Data> dds = ll2.values().stream().collect(Collectors.toList());

		System.out.println("");

	}



	 static class Data {
		String ckdm;
		String barcode;
		Long djrq;

		 Data(String ckdm,String barcode,Long djrq){
		 	this.barcode = barcode;
		 	this.ckdm = ckdm;
		 	this.djrq = djrq;

		 }

		 public String getCkdm() {
			 return ckdm;
		 }

		 public void setCkdm(String ckdm) {
			 this.ckdm = ckdm;
		 }

		 public String getBarcode() {
			 return barcode;
		 }

		 public void setBarcode(String barcode) {
			 this.barcode = barcode;
		 }

		 public Long getDjrq() {
			 return djrq;
		 }

		 public void setDjrq(Long djrq) {
			 this.djrq = djrq;
		 }
	 }

	 @Test
	 public void test10(){
		Set<String> orderSnSet = new HashSet<>();
		 orderSnSet.add("001");
		 orderSnSet.add("001");
		 orderSnSet.add("002");

		 List<String> orderSnList = orderSnSet.stream().collect(Collectors.toList());

		 orderSnList.add("004");

		 System.out.println("test over");
	 }
}
