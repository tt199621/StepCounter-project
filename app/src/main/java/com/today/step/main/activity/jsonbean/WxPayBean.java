package com.today.step.main.activity.jsonbean;

public class WxPayBean {

    /**
     * appId : wx591efa3c85e5608b
     * nonceStr : kby3NU3pIjr23dgRlWtG8IJp5vPMHGZf
     * package : prepay_id=wx11145838024988577c811e1d1138427500
     * paySign : 4E1DDD4C4A78DA45B0521FD677D38FA2
     * prepay_id : wx11145838024988577c811e1d1138427500
     * signType : MD5
     * timeStamp : 1562828317
     */

    private String nonceStr;
    private String paySign;
    private String prepay_id;
    private long timeStamp;

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
