package com.anta.java8.biz;

public class TestPojo{
	private AsnInfo asnInfo;

	public void setAsnInfo(AsnInfo asnInfo){
		this.asnInfo = asnInfo;
	}

	public AsnInfo getAsnInfo(){
		return asnInfo;
	}

	public static class AsnInfo{
		private String tmsPhone;
		private String orderType;
		private String customerId;
		private String orderCode;
		private String customerCode;
		private Items items;
		private String warehouseCode;

		public void setTmsPhone(String tmsPhone){
			this.tmsPhone = tmsPhone;
		}

		public String getTmsPhone(){
			return tmsPhone;
		}

		public void setOrderType(String orderType){
			this.orderType = orderType;
		}

		public String getOrderType(){
			return orderType;
		}

		public void setCustomerId(String customerId){
			this.customerId = customerId;
		}

		public String getCustomerId(){
			return customerId;
		}

		public void setOrderCode(String orderCode){
			this.orderCode = orderCode;
		}

		public String getOrderCode(){
			return orderCode;
		}

		public void setCustomerCode(String customerCode){
			this.customerCode = customerCode;
		}

		public String getCustomerCode(){
			return customerCode;
		}

		public void setItems(Items items){
			this.items = items;
		}

		public Items getItems(){
			return items;
		}

		public void setWarehouseCode(String warehouseCode){
			this.warehouseCode = warehouseCode;
		}

		public String getWarehouseCode(){
			return warehouseCode;
		}
	}

	public static class Item{
		private String itemQuantity;
		private String lineNo;
		private String itemSkuCode;

		public void setItemQuantity(String itemQuantity){
			this.itemQuantity = itemQuantity;
		}

		public String getItemQuantity(){
			return itemQuantity;
		}

		public void setLineNo(String lineNo){
			this.lineNo = lineNo;
		}

		public String getLineNo(){
			return lineNo;
		}

		public void setItemSkuCode(String itemSkuCode){
			this.itemSkuCode = itemSkuCode;
		}

		public String getItemSkuCode(){
			return itemSkuCode;
		}
	}

	public static class Items{
		private Item item;

		public void setItem(Item item){
			this.item = item;
		}

		public Item getItem(){
			return item;
		}
	}
}
