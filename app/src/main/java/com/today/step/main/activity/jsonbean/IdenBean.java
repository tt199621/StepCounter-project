package com.today.step.main.activity.jsonbean;

public class IdenBean {

    /**
     * chargeStatus : 1
     * message : 成功
     * data : {"tradeNo":"19091921425092324","code":"0","riskType":"normal","address":"湖北省监利县尺八镇三红村八组","birth":"19970125","name":"姜康","cardNum":"421023199701255212","sex":"男","nation":"汉","issuingDate":"","issuingAuthority":"","expiryDate":""}
     * code : 200000
     */

    private int chargeStatus;
    private String message;
    private DataBean data;
    private String code;

    public int getChargeStatus() {
        return chargeStatus;
    }

    public void setChargeStatus(int chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * tradeNo : 19091921425092324
         * code : 0
         * riskType : normal
         * address : 湖北省监利县尺八镇三红村八组
         * birth : 19970125
         * name : 姜康
         * cardNum : 421023199701255212
         * sex : 男
         * nation : 汉
         * issuingDate :
         * issuingAuthority :
         * expiryDate :
         */

        private String tradeNo;
        private String code;
        private String riskType;
        private String address;
        private String birth;
        private String name;
        private String cardNum;
        private String sex;
        private String nation;
        private String issuingDate;
        private String issuingAuthority;
        private String expiryDate;

        public String getTradeNo() {
            return tradeNo;
        }

        public void setTradeNo(String tradeNo) {
            this.tradeNo = tradeNo;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getRiskType() {
            return riskType;
        }

        public void setRiskType(String riskType) {
            this.riskType = riskType;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCardNum() {
            return cardNum;
        }

        public void setCardNum(String cardNum) {
            this.cardNum = cardNum;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getIssuingDate() {
            return issuingDate;
        }

        public void setIssuingDate(String issuingDate) {
            this.issuingDate = issuingDate;
        }

        public String getIssuingAuthority() {
            return issuingAuthority;
        }

        public void setIssuingAuthority(String issuingAuthority) {
            this.issuingAuthority = issuingAuthority;
        }

        public String getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
        }
    }
}
