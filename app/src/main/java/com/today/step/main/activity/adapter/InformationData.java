package com.today.step.main.activity.adapter;

public class InformationData {
    String inform_text;
    String inform_time;

    public InformationData(String inform_text, String inform_time) {
        this.inform_text = inform_text;
        this.inform_time = inform_time;
    }

    public String getInform_text() {
        return inform_text;
    }

    public String getInform_time() {
        return inform_time;
    }

    public void setInform_text(String inform_text) {
        this.inform_text = inform_text;
    }

    public void setInform_time(String inform_time) {
        this.inform_time = inform_time;
    }
}
