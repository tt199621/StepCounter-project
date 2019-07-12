package com.today.step.main.activity.jsonbean;

public class WxPayBean {

    /**
     * package : Sign=WXPay
     * appid : wx591efa3c85e5608b
     * extdata : {"user_id":"18774520398"}
     * sign : E71C89DC96CEB20F9529131C9C7B6F16
     * partnerid : 1499812242
     * prepayid : wx1210400517104651406a8c731932236700
     * noncestr : t5sOIrKTxYkx4StD
     * timestamp : 1562899205
     */

    private String sign;
    private String prepayid;
    private String noncestr;
    private String timestamp;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
