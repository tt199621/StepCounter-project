package com.today.step.chat.jsonbean;

public class OrderJSonBean {

	/**
	 * code : 100
	 * msg : 成功
	 * extend : {"tradeOrder":{"id":"5d0ae23bd012b9370176735e","buyer":"5d009b3392588493640e9bc9","seller":"5d00c6d06e9b84939ca3ae88","transactionNumber":10,"unitPrice":1,"quantity":10,"orderNumber":null,"sellerPhone":null,"sellerBank":null,"alipay":"15773005016","buyerRealName":null,"sellerRealName":null,"isAffirm":1,"creatTime":"2019-06-20 09:32:43"}}
	 */

	private int code;
	private String msg;
	private ExtendBean extend;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ExtendBean getExtend() {
		return extend;
	}

	public void setExtend(ExtendBean extend) {
		this.extend = extend;
	}

	public static class ExtendBean {
		/**
		 * tradeOrder : {"id":"5d0ae23bd012b9370176735e","buyer":"5d009b3392588493640e9bc9","seller":"5d00c6d06e9b84939ca3ae88","transactionNumber":10,"unitPrice":1,"quantity":10,"orderNumber":null,"sellerPhone":null,"sellerBank":null,"alipay":"15773005016","buyerRealName":null,"sellerRealName":null,"isAffirm":1,"creatTime":"2019-06-20 09:32:43"}
		 */

		private TradeOrderBean tradeOrder;

		public TradeOrderBean getTradeOrder() {
			return tradeOrder;
		}

		public void setTradeOrder(TradeOrderBean tradeOrder) {
			this.tradeOrder = tradeOrder;
		}

		public static class TradeOrderBean {
			/**
			 * id : 5d0ae23bd012b9370176735e
			 * buyer : 5d009b3392588493640e9bc9
			 * seller : 5d00c6d06e9b84939ca3ae88
			 * transactionNumber : 10
			 * unitPrice : 1
			 * quantity : 10
			 * orderNumber : null
			 * sellerPhone : null
			 * sellerBank : null
			 * alipay : 15773005016
			 * buyerRealName : null
			 * sellerRealName : null
			 * isAffirm : 1
			 * creatTime : 2019-06-20 09:32:43
			 */

			private String id;
			private String buyer;
			private String seller;
			private int transactionNumber;
			private int unitPrice;
			private int quantity;
			private Object orderNumber;
			private Object sellerPhone;
			private Object sellerBank;
			private String alipay;
			private Object buyerRealName;
			private Object sellerRealName;
			private int isAffirm;
			private String creatTime;

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getBuyer() {
				return buyer;
			}

			public void setBuyer(String buyer) {
				this.buyer = buyer;
			}

			public String getSeller() {
				return seller;
			}

			public void setSeller(String seller) {
				this.seller = seller;
			}

			public int getTransactionNumber() {
				return transactionNumber;
			}

			public void setTransactionNumber(int transactionNumber) {
				this.transactionNumber = transactionNumber;
			}

			public int getUnitPrice() {
				return unitPrice;
			}

			public void setUnitPrice(int unitPrice) {
				this.unitPrice = unitPrice;
			}

			public int getQuantity() {
				return quantity;
			}

			public void setQuantity(int quantity) {
				this.quantity = quantity;
			}

			public Object getOrderNumber() {
				return orderNumber;
			}

			public void setOrderNumber(Object orderNumber) {
				this.orderNumber = orderNumber;
			}

			public Object getSellerPhone() {
				return sellerPhone;
			}

			public void setSellerPhone(Object sellerPhone) {
				this.sellerPhone = sellerPhone;
			}

			public Object getSellerBank() {
				return sellerBank;
			}

			public void setSellerBank(Object sellerBank) {
				this.sellerBank = sellerBank;
			}

			public String getAlipay() {
				return alipay;
			}

			public void setAlipay(String alipay) {
				this.alipay = alipay;
			}

			public Object getBuyerRealName() {
				return buyerRealName;
			}

			public void setBuyerRealName(Object buyerRealName) {
				this.buyerRealName = buyerRealName;
			}

			public Object getSellerRealName() {
				return sellerRealName;
			}

			public void setSellerRealName(Object sellerRealName) {
				this.sellerRealName = sellerRealName;
			}

			public int getIsAffirm() {
				return isAffirm;
			}

			public void setIsAffirm(int isAffirm) {
				this.isAffirm = isAffirm;
			}

			public String getCreatTime() {
				return creatTime;
			}

			public void setCreatTime(String creatTime) {
				this.creatTime = creatTime;
			}
		}
	}
}
