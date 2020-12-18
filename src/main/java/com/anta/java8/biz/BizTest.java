package com.anta.java8.biz;

import com.anta.java8.IWTest;
import org.junit.Test;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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


	public static void main(String[] args) {
		String sapInvokerType = SapInvokerType.ILO060.name();
		SapInvokerType type = SapInvokerType.valueOf("ILO0601");
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


}
