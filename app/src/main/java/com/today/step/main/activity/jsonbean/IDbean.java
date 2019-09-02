package com.today.step.main.activity.jsonbean;

public class IDbean {

    /**
     * code : 100
     * msg : 成功
     * extend : {"all":0,"today":0,"status":0}
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
         * all : 0
         * today : 0
         * status : 0
         */

        private int all;
        private int today;
        private int status;

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }

        public int getToday() {
            return today;
        }

        public void setToday(int today) {
            this.today = today;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
