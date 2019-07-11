package com.today.step.main.activity.jsonbean;

import java.util.List;

public class DealBean {

    /**
     * code : 100
     * msg : 成功
     * extend : {"list":[{"id":"5d132ecd9209ea86bd6345edf","creatTime":"2019-06-26 16:37:33","orderNumber":null,"quantity":60,"transactionNumber":200,"transactionStatus":"已付款","outOrIn":1},{"id":"5d132ecd9209ea86bd6345fdhdsf","creatTime":"2019-06-26 16:37:33","orderNumber":null,"quantity":100,"transactionNumber":300,"transactionStatus":"待付款","outOrIn":1},{"id":"5d132ecd9209ea86bd6345e75","creatTime":"2019-06-26 16:37:33","orderNumber":null,"quantity":50,"transactionNumber":100,"transactionStatus":"已付款","outOrIn":2},{"id":"5d132ecd9209ea86bd6345fdhddsfe","creatTime":"2019-06-26 16:37:33","orderNumber":null,"quantity":300,"transactionNumber":500,"transactionStatus":"待付款","outOrIn":2}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 5d132ecd9209ea86bd6345edf
             * creatTime : 2019-06-26 16:37:33
             * orderNumber : null
             * quantity : 60
             * transactionNumber : 200
             * transactionStatus : 已付款
             * outOrIn : 1
             */

            private String id;
            private String creatTime;
            private Object orderNumber;
            private int quantity;
            private double transactionNumber;
            private String transactionStatus;
            private int outOrIn;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreatTime() {
                return creatTime;
            }

            public void setCreatTime(String creatTime) {
                this.creatTime = creatTime;
            }

            public Object getOrderNumber() {
                return orderNumber;
            }

            public void setOrderNumber(Object orderNumber) {
                this.orderNumber = orderNumber;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public double getTransactionNumber() {
                return transactionNumber;
            }

            public void setTransactionNumber(double transactionNumber) {
                this.transactionNumber = transactionNumber;
            }

            public String getTransactionStatus() {
                return transactionStatus;
            }

            public void setTransactionStatus(String transactionStatus) {
                this.transactionStatus = transactionStatus;
            }

            public int getOutOrIn() {
                return outOrIn;
            }

            public void setOutOrIn(int outOrIn) {
                this.outOrIn = outOrIn;
            }
        }
    }
}
