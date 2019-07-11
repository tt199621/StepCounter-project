package com.today.step.main.activity.jsonbean;

public class ItemBean {
    /**
     * code : 100
     * msg : 成功
     * extend : {"tradeOrder":{"id":"5rdgdsft4gfdsgfdsgdf","buyer":"5d1191ed5831ea8636f3123c","seller":"5d11cb72566dea861cfa8286","transactionNumber":45,"unitPrice":0.89,"quantity":43,"orderNumber":null,"sellerPhone":null,"sellerBank":null,"alipay":"45435","buyerRealName":"您","sellerRealName":"您","isAffirm":3,"creatTime":"2019-06-26 16:37:33"}}
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
         * tradeOrder : {"id":"5rdgdsft4gfdsgfdsgdf","buyer":"5d1191ed5831ea8636f3123c","seller":"5d11cb72566dea861cfa8286","transactionNumber":45,"unitPrice":0.89,"quantity":43,"orderNumber":null,"sellerPhone":null,"sellerBank":null,"alipay":"45435","buyerRealName":"您","sellerRealName":"您","isAffirm":3,"creatTime":"2019-06-26 16:37:33"}
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
             * id : 5rdgdsft4gfdsgfdsgdf
             * buyer : 5d1191ed5831ea8636f3123c
             * seller : 5d11cb72566dea861cfa8286
             * transactionNumber : 45
             * unitPrice : 0.89
             * quantity : 43
             * orderNumber : null
             * sellerPhone : null
             * sellerBank : null
             * alipay : 45435
             * buyerRealName : 您
             * sellerRealName : 您
             * isAffirm : 3
             * creatTime : 2019-06-26 16:37:33
             */

            private String id;
            private String buyer;
            private String seller;
            private int transactionNumber;
            private double unitPrice;
            private int quantity;
            private Object orderNumber;
            private Object sellerPhone;
            private Object sellerBank;
            private String alipay;
            private String buyerRealName;
            private String sellerRealName;
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

            public double getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(double unitPrice) {
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

            public String getBuyerRealName() {
                return buyerRealName;
            }

            public void setBuyerRealName(String buyerRealName) {
                this.buyerRealName = buyerRealName;
            }

            public String getSellerRealName() {
                return sellerRealName;
            }

            public void setSellerRealName(String sellerRealName) {
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
