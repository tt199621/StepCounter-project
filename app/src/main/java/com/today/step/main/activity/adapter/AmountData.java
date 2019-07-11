package com.today.step.main.activity.adapter;

public class AmountData {
    String infor_text;
    String infor_time;
    String infor_number;

    public AmountData(String infor_text, String infor_time, String infor_number) {
        this.infor_text = infor_text;
        this.infor_time = infor_time;
        this.infor_number = infor_number;
    }

    public AmountData(String infor_text, String infor_time) {
        this.infor_text = infor_text;
        this.infor_time = infor_time;
    }

    public String getInform_text() {
        return infor_text;
    }

    public String getInform_time() {
        return infor_time;
    }

    public void setInform_text(String inform_text) {
        this.infor_text = inform_text;
    }

    public void setInform_time(String inform_time) {
        this.infor_time = inform_time;
    }

    public String getInfor_number() {
        return infor_number;
    }

    public void setInfor_number(String infor_number) {
        this.infor_number = infor_number;
    }
}
