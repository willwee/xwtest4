package com.anta.java8.biz;

import com.anta.java8.IWTest;
import com.google.common.base.Joiner;
import org.apache.commons.collections4.MapUtils;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xiaowei
 * @date 2020/12/11 15:31
 */
public class BizTest implements IWTest {

	enum SapInvokerType {

		/**
		 * ILO060
		 */
		ILO060,
		/**
		 * 采购入库通知单接口
		 */
		ILO130_1,
		/**
		 * 采购退货通知单 (和采购入库通知单接口共用)
		 */
		ILO130_2,
		/**
		 * 赊销采购过账单（和采购入库通知单接口共用）
		 */
		ILO130_3;

	}


	enum SapApiHandleType {

		INIT(0),
		/**
		 * 处理中
		 */
		DOING(1),
		/**
		 * 成功处理
		 */
		DONE(2),
		/**
		 * 异常
		 */
		ERROR(3);

		private Integer code;

		SapApiHandleType(Integer code) {
			this.code = code;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}
	}

	private static final String NOT_NEED_PUSH = "offlinepoint,a-antapoint,a-antakidspoint,a-onlinepoint,k-antapoint,k-antakidspoint,k-onlinepoint,武汉,天津,福州,重庆,上海,purchase";


	private static final List<String> NOT_NEED_PUSH_LIST = Arrays.asList("offlinepoint", "a-antapoint", "a-antakidspoint", "a-onlinepoint", "k-antapoint", "k-antakidspoint", "k-onlinepoint", "武汉", "天津", "福州", "重庆", "上海", "purchase");

	@Test
	public void test5() {
		System.out.println(Arrays.stream(NOT_NEED_PUSH.split(",")).collect(Collectors.toList()).contains("k-antapoint"));
		System.out.println(NOT_NEED_PUSH_LIST.contains("offlinepoint"));
		System.out.println(NOT_NEED_PUSH_LIST.contains("风哥来了 ????16号安踏专场"));
		System.out.println(NOT_NEED_PUSH_LIST.contains(null));
		System.out.println(NOT_NEED_PUSH.indexOf("onlinepoint"));
	}

	@Test
	public void test6() {
		StringBuilder sb = new StringBuilder();
		sb.append(" select ")
				.append("  r.shop_id as r_shop_id,r.relate_order_bill_no r_relate_order_bill_no,r.exchange_rate r_exchange_rate,")
				.append(" r.complete_date as r_complete_date,r.deal_code r_deal_code,r.id AS r_id,r.billno as r_billNo,")
				.append(" r.settle_method_id as r_settle_method_id, r.currency_type_id as r_currency_type_id, ");
		sb.append(" g.id as g_id, ")
				.append(" g.goods_id as g_goods_id, ")
				.append(" g.return_qty as g_return_qty, ")
				.append(" g.single_product_id as g_single_product_id ");
		sb.append(" from ord_retail_return_bill r left join ord_retail_return_gds_de g on g.return_bill_id = r.id ");
		sb.append(" where r.billno in (%s) ");

		String sql = sb.toString();
		System.out.println(sql);
	}

	@Test
	public void test4() {
		System.out.println(SapApiHandleType.valueOf("DONE").code);
	}

	@Test
	public void test8(){
		Set tt = new HashSet<Long>(){{ add(100L);}};
		System.out.println(tt.size());
		tt.stream().forEach(v -> System.out.println(v));
	}

	@Test
	public void test9(){
		BigDecimal jsj = BigDecimal.valueOf(1000L);
		Integer i = -10;
		BigDecimal jsj2 =
				jsj.multiply(BigDecimal.valueOf(Math.abs(i)));
		System.out.println(jsj2);
	}

	@Test
	public void test7(){
		StringBuilder sql = new StringBuilder("select s.order_sn, s.fx_sap_status ");
		sql.append("from stm_retail_settle_status s ");
		sql.append("join stm_retail_settle r on s.order_sn = r.order_sn ");
		sql.append("where s.push_system = '" + 2 + "' ");
		sql.append(" and s.fx_sap_status in (0, 2) ");
		sql.append(" and (r.store_channel <> '4' or r.warehouse_type <> '1') ");
		System.out.println(sql.toString());
	}

	@Test
	public void test16(){
		System.out.println(40 >> 2);
		System.out.println(40 << 2);
	}

	@Test
	public void test17(){
		System.out.println(BigDecimal.valueOf(1.256).add(new BigDecimal("0.12333")).setScale(2,BigDecimal.ROUND_HALF_UP));

		System.out.println(new BigDecimal("1.22522333344").setScale(2,
				BigDecimal.ROUND_HALF_UP).toString());

		System.out.println(new BigDecimal(-20).compareTo(BigDecimal.ZERO));

		System.out.println(new BigDecimal("1.22522333344").setScale(0,
				BigDecimal.ROUND_DOWN).toString());

		System.out.println(new BigDecimal("1.22522333344").setScale(0,
				BigDecimal.ROUND_DOWN).compareTo(new BigDecimal("1.52522333344").setScale(0,
				BigDecimal.ROUND_DOWN)));

		System.out.println(new BigDecimal("-1.22522333344").setScale(0,
				BigDecimal.ROUND_DOWN).compareTo(new BigDecimal("1.52522333344").setScale(0,
				BigDecimal.ROUND_DOWN)) != 0);
	}

	@Test
	public void test11(){
		String[] ids = new String[]{"111","222"};

		System.out.println(String.format("没找到对应的采购入库通知单，id为%s,%s",ids));
	}

	@Test
	public void test20(){
		Integer a = 2;
		Integer b = 2;
		System.out.println(a == b);
		System.out.println(a.equals(b));
	}

	@Test
	public void test21(){
		System.out.println(LocalDate.now().atStartOfDay(ZoneOffset.systemDefault()).toInstant().getEpochSecond());
		Double bb =0.0;
		System.out.println(bb ==0);
	}


	public static void main(String[] args) {
		String sapInvokerType = SapInvokerType.ILO060.name();

		SapInvokerType type2 = SapInvokerType.ILO060;
		System.out.println("测试结果：");
		System.out.println(SapInvokerType.ILO130_1.equals(type2));
		System.out.println(SapApiHandleType.DOING.code);
		SapInvokerType type = SapInvokerType.valueOf("ILO060");
		if (StringUtils.hasText(sapInvokerType)) {
			int i = 0;
			switch (type) {
				case ILO060:
					System.out.println("ILO060");
					i = 1;
					break;
				case ILO130_1:
					System.out.println("ILO130_1");
					i = 2;
					break;
				case ILO130_2:
					System.out.println("ILO130_2");
					i = 3;
					break;
				case ILO130_3:
					System.out.println("ILO130_3");
					i = 4;
					break;
				default:
					i = 5;
					System.out.println("default");
			}
			System.out.println(String.format("最终结果：%s", i));
		}
	}


	class AllotApplyBill {
		/**
		 * 平台单号
		 */
		private String tradeOrder;

		private String billNo;

		private String storageNo;

		public String getTradeOrder() {
			return tradeOrder;
		}

		public void setTradeOrder(String tradeOrder) {
			this.tradeOrder = tradeOrder;
		}

		public String getBillNo() {
			return billNo;
		}

		public void setBillNo(String billNo) {
			this.billNo = billNo;
		}

		public String getStorageNo() {
			return storageNo;
		}

		public void setStorageNo(String storageNo) {
			this.storageNo = storageNo;
		}
	}

	class AllocateBill {
		private String sourceBillNo;

		public String getSourceBillNo() {
			return sourceBillNo;
		}

		public void setSourceBillNo(String sourceBillNo) {
			this.sourceBillNo = sourceBillNo;
		}
	}

	@Test
	public void test1() {
		AllotApplyBill[] bills = new AllotApplyBill[4];
		AllotApplyBill allotApplyBill1 = new AllotApplyBill();
		allotApplyBill1.setBillNo("HDBSQD2020121100015");
		allotApplyBill1.setTradeOrder("123456789");
		AllotApplyBill allotApplyBill2 = new AllotApplyBill();
		allotApplyBill2.setBillNo("HDBSQD2020121100015");
		allotApplyBill2.setTradeOrder("1234567891");
		AllotApplyBill allotApplyBill3 = new AllotApplyBill();
		allotApplyBill3.setBillNo("HDBSQD2020121100016");
		allotApplyBill3.setTradeOrder(null);
		AllotApplyBill allotApplyBill4 = new AllotApplyBill();
		allotApplyBill4.setBillNo("HDBSQD2020121100017");
		allotApplyBill4.setTradeOrder("123323");
		bills[0] = allotApplyBill1;
		bills[1] = allotApplyBill2;
		bills[2] = allotApplyBill3;
		bills[3] = allotApplyBill4;
		Map<String, AllotApplyBill> allotApplyBillMap = null;
		if (!ObjectUtils.isEmpty(bills)) {
			// 将结果集转换成MAP 按billNo 作为key 当有key重复时，选择第一条记录作为结果集 理论上说一个单据对应一笔记录
			allotApplyBillMap = Arrays.stream(bills).collect(Collectors.toMap(AllotApplyBill::getBillNo,
					Function.identity(), (item1, item2) -> item1));
		}
		AllocateBill item = new AllocateBill();
		item.setSourceBillNo("HDBSQD2020121100015");

		if (!ObjectUtils.isEmpty(allotApplyBillMap.get(item.getSourceBillNo()))) {
			// 分步调拨单 传递 YTZ仓库的,增加平台单号传递
			System.out.println(allotApplyBillMap.get(item.getSourceBillNo()).getTradeOrder());
		}


	}


	@Test
	public void test13(){
		StringBuilder sql = new StringBuilder("select s.order_sn, s.fx_sap_status ");
		sql.append("from stm_retail_settle_status s ");
		sql.append("join stm_retail_settle r on s.order_sn = r.order_sn ");
		sql.append("where s.push_system = 'FX_SAP' ");
		sql.append(" and s.fx_sap_status in (0, 2) ");
		sql.append(" and (r.store_channel <> '4' or r.warehouse_type <> '1') ");
		System.out.println(sql.toString());

	}

	@Test
	public void test14(){
		// String.format("%03d", i)主要实现如果一个数字为超过3位，则会在其前面补零以到达规定的位数，其中o是被填充到缺省位的数字，3代表规定数字的总位数  d代表是整型。
		String ss = String.format("%03d", new Random(System.currentTimeMillis()).nextInt(1000));
		System.out.println(ss);

		String[] tableColumns = new String[]{
				"id", "status", "create_date", "bill_type", "task_type", "bill_date", "push_date", "complete_time"
		};
		Arrays.stream(tableColumns).forEach(System.out::println);


	}

	private class RetailSettlementDetail{
		private String orderSn;

		private String info1;

		private String info2;

		public String getOrderSn() {
			return orderSn;
		}

		public void setOrderSn(String orderSn) {
			this.orderSn = orderSn;
		}

		public String getInfo1() {
			return info1;
		}

		public void setInfo1(String info1) {
			this.info1 = info1;
		}

		public String getInfo2() {
			return info2;
		}

		public void setInfo2(String info2) {
			this.info2 = info2;
		}
	}
	@Test
	public void test15(){

		List<RetailSettlementDetail> retailSettlementDetailList = new ArrayList<>();
		RetailSettlementDetail de1 =new RetailSettlementDetail();
		RetailSettlementDetail de2 =new RetailSettlementDetail();
		RetailSettlementDetail de3 =new RetailSettlementDetail();
		retailSettlementDetailList.add(de1);
		retailSettlementDetailList.add(de2);
		retailSettlementDetailList.add(de3);

		de1.setInfo1("test1");
		de1.setInfo2("test1");
		de1.setOrderSn("FT001");

		de2.setInfo1("test2");
		de2.setInfo2("test2");
		de2.setOrderSn("FT001");

		de3.setInfo1("test3");
		de3.setInfo2("test3");
		de3.setOrderSn("FT003");

		List<RetailSettlementDetail> temp = retailSettlementDetailList.stream().filter(v -> !v.getOrderSn().equals(
				"FT001")).collect(Collectors.toList());

//		Map<String, List<RetailSettlementDetail>> retailSettlements = retailSettlementDetailList.stream().collect(Collectors.groupingBy(RetailSettlementDetail::getOrderSn));

		System.out.println(temp);
	}

	@Test
	public void test18(){
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String, Object> r1 = new HashMap<>();
		r1.put("name","鞋");
		r1.put("code", "PF");
		Map<String, Object> r2 = new HashMap<>();
		r2.put("name","服装A");
		r2.put("code", "PFT");
		Map<String, Object> r3 = new HashMap<>();
		r3.put("name","鞋类");
		r3.put("code", "PF");
		result.add(r1);
		result.add(r2);
		result.add(r3);

		result.stream().forEach(v -> {
			if (MapUtils.getString(v,"name") != null) {
				String name = MapUtils.getString(v,"name").replace("服装","运动服").replace("鞋类","运动鞋").replace("鞋","运动鞋");
				v.put("name", name);
			}
		});

		result.stream().forEach(System.out::println);


	}
	private final static String regExp="[\n`~!@#$%^&*+={}';',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
	@Test
	public void test19(){

		String errorInfo = "\t\tString errorInfo = \"exception\\\":\\\"\\\\\\tat e3.middleware.result.model" +
				".ServiceResult.addErrorObject(ServiceResult.java:64)\\\\\\tat e3.middleware.result.model.ServiceResult.addErrorObject(ServiceResult.java:51)\\\\\\tat com.baison.e3.middleware.promotion.service.impl.calculate.AntaCalculatePromotionService.backWriteGiftQty(AntaCalculatePromotionService.java:736)\\\\\\tat com.baison.e3.middleware.promotion.service.impl.calculate.AntaCalculatePromotionService.calculatePromotion(AntaCalculatePromotionService.java:179)\\\\\\tat sun.reflect.GeneratedMethodAccessor307.invoke(Unknown Source)\\\\\\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\\\\\\tat java.lang.reflect.Method.invoke(Method.java:498)\\\\\\tat org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:333)\\\\\\tat org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:190)\\\\\\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\\\\\\tat org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:99)\\\\\\tat org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:282)\\\\\\tat org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96)\\\\\\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\\\\\\tat org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:213)\\\\\\tat com.sun.proxy.$Proxy215.calculatePromotion(Unknown Source)\\\\\\tat sun.reflect.GeneratedMethodAccessor307.invoke(Unknown Source)\\\\\\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\\\\\\tat java.lang.reflect.Method.invoke(Method.java:498)\\\\\\tat org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:333)\\\\\\tat org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:207)\\\\\\tat com.sun.proxy.$Proxy215.calculatePromotion(Unknown Source)\\\\\\tat com.baison.e3.middleware.promotion.service.impl.calculate.AntaCalculatePromotionProvider.calculatePromotion(AntaCalculatePromotionProvider.java:17)\\\\\\tat sun.reflect.GeneratedMethodAccessor307.invoke(Unknown Source)\\\\\\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\\\\\\tat java.lang.reflect.Method.invoke(Method.java:498)\\\\\\tat com.taobao.hsf.remoting.provider.ReflectInvocationHandler.handleRequest0(ReflectInvocationHandler.java:83)\\\\\\tat com.taobao.hsf.remoting.provider.ReflectInvocationHandler.invoke(ReflectInvocationHandler.java:163)\\\\\\tat com.taobao.hsf2dubbo.DubboServerFilterAsyncInvocationHandlerInterceptor$1.invoke(DubboServerFilterAsyncInvocationHandlerInterceptor.java:140)\\\\\\tat com.navercorp.pinpoint.plugin.micro.service.auth.dubbo.alibaba.AuthProviderFilter.invoke(AuthProviderFilter.java:33)\\\\\\tat com.taobao.hsf2dubbo.DubboFilterUtil$1.invoke(DubboFilterUtil.java:67)\\\\\\tat com.taobao.hsf2dubbo.DubboServerFilterAsyncInvocationHandlerInterceptor.invoke(DubboServerFilterAsyncInvocationHandlerInterceptor.java:63)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterBuilder$TailNode.invoke(RPCFilterBuilder.java:165)\\\\\\tat com.taobao.hsf.plugins.spas.SpasServerFilter.invoke(SpasServerFilter.java:150)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf.edas.tps.component.WhiteListServerFilter.invoke(WhiteListServerFilter.java:67)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf.edas.tps.component.TPSServerFilter.invoke(TPSServerFilter.java:67)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf.tps.component.TPSServerFilter.invoke(TPSServerFilter.java:68)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf.invocation.stats.InvocationStatsServerFilter.invoke(InvocationStatsServerFilter.java:47)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf.monitor.log.filter.MonitorLogServerFilter.invoke(MonitorLogServerFilter.java:45)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf.profiler.ProfilerServerFilter.invoke(ProfilerServerFilter.java:32)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf.plugins.eagleeye.EagleEyeServerFilter.invoke(EagleEyeServerFilter.java:66)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf.rpc.server.MethodAbsenceFilter.invoke(MethodAbsenceFilter.java:48)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf.rpc.server.EchoFilter.invoke(EchoFilter.java:45)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf.rpc.generic.GenericInvocationServerFilter.invoke(GenericInvocationServerFilter.java:107)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf2dubbo.DubboGenericServerFilter.invoke(DubboGenericServerFilter.java:37)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf.rpc.server.ServiceAbsenceFilter.invoke(ServiceAbsenceFilter.java:47)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf.common.filter.CommonServerFilter.invoke(CommonServerFilter.java:24)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf2dubbo.context.DubboRPCContextServerFilter.invoke(DubboRPCContextServerFilter.java:48)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf.context.RPCContextServerFilter.invoke(RPCContextServerFilter.java:39)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterNode.invoke(RPCFilterNode.java:71)\\\\\\tat com.taobao.hsf.invocation.filter.RPCFilterBuilder$HeadNode.invoke(RPCFilterBuilder.java:134)\\\\\\tat com.taobao.hsf.invocation.filter.FilterInvocationHandler.invoke(FilterInvocationHandler.java:28)\\\\\\tat com.taobao.hsf.remoting.provider.ServerContextInvocationHandler.invoke(ServerContextInvocationHandler.java:35)\\\\\\tat com.taobao.hsf.remoting.provider.ProviderProcessor.handleRequest(ProviderProcessor.java:53)\\\\\\tat com.taobao.hsf.io.remoting.hsf.message.HSFServerHandler$1.run(HSFServerHandler.java:177)\\\\\\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\\\\\\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\\\\\\tat java.lang.Thread.run(Thread.java:748)\\\\\",\\\"paras\\\"\";";

		errorInfo = errorInfo.replaceAll(regExp," ");


		System.out.println("error::"+errorInfo);
	}


	private String uatSettlemetResult() {
		return "{\"code\": \"\", \"data\": {\"order_list\": [{\"add_time\": \"\", \"bill_type\": \"0\", \"complete_date\": \"2020-12-01 10:23:05\", \"concession_amount\": \"0.0\", \"coupon_amount\": \"0.0\", \"coupon_cash_amount\": \"0.0\", \"discount\": \"1.0\", \"goods_code\": \"332010001-8\", \"goods_market_price\": \"269.0\", \"goods_number\": \"1\", \"income_amount\": \"79.0\", \"item_sn\": \"0\", \"order_deal_code\": \"20113061841706\", \"order_sn\": \"ZX60113096417876\", \"pay_time\": \"2020-11-30 21:10:07\", \"platform_name\": \"唯品会\", \"return_amount\": \"0.0\", \"return_order_type\": \"0\", \"shipping_fee\": \"0.0\", \"shop_amount\": \"79.0\", \"shop_code\": \"VIP_ANTAKIDS\", \"shop_name\": \"唯品会-ANTAKIDS\", \"shop_price\": \"79.0\", \"warehouse_code\": \"CK_SF_QZC_2WP\", \"warehouse_name\": \"顺丰泉州2仓-唯品\"}, {\"add_time\": \"\", \"bill_type\": \"0\", \"complete_date\": \"2020-11-30 22:43:03\", \"concession_amount\": \"139.0\", \"coupon_amount\": \"0.0\", \"coupon_cash_amount\": \"0.0\", \"discount\": \"0.36\", \"goods_code\": \"152021327-1\", \"goods_market_price\": \"219.0\", \"goods_number\": \"1\", \"income_amount\": \"79.0\", \"item_sn\": \"0\", \"order_deal_code\": \"20113061864006\", \"order_sn\": \"ZX60113096417810\", \"pay_time\": \"2020-11-30 20:39:23\", \"platform_name\": \"唯品会\", \"return_amount\": \"0.0\", \"return_order_type\": \"0\", \"shipping_fee\": \"0.0\", \"shop_amount\": \"218.0\", \"shop_code\": \"VIP_Anta\", \"shop_name\": \"唯品会-Anta\", \"shop_price\": \"218.0\", \"warehouse_code\": \"CK_SF_JJ_WP\", \"warehouse_name\": \"顺丰晋江仓-唯品\"} ], \"total_pages\": \"\", \"total_results\": \"2\"}, \"flag\": \"success\", \"message\": \"销售结算数据获取接口成功\"}";
	}


	@Test
	public void test22() {
		LocalDate today = LocalDate.now();
		//本月的第一天
		LocalDate firstday = LocalDate.of(today.getYear(), today.getMonth(), 1);
		DateTimeFormatter df5 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		System.out.println(firstday.format(df5));


		LocalDate billDate = firstday.plusDays(7);

		System.out.println(billDate.format(df5));

		LocalDate today1 = LocalDate.now();
		LocalDate preDate = today1.minusDays(1);
		System.out.println(preDate.format(df5));
	}

	@Test
	public void test23() {
		List<String> uu = Stream.of("admin","admin1","admin2").collect(Collectors.toList());

		List<String> list =
				uu.stream().filter(Objects::nonNull).filter(v->!"admin".equals(v)).collect(Collectors.toList());

		System.out.println(list.size());

	}

}
