package com.today.step.main.activity.jsonbean;

import java.util.List;

public class The_Month {
    private int code;
    private String msg;
    private Msg.ExtendBean extend;

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

    public Msg.ExtendBean getExtend() {
        return extend;
    }

    public void setExtend(Msg.ExtendBean extend) {
        this.extend = extend;
    }

    public static class ExtendBean {
        private List<Msg.ExtendBean.WeekSumber> list;

        public List<Msg.ExtendBean.WeekSumber> getList() {
            return list;
        }

        public void setList(List<Msg.ExtendBean.WeekSumber> list) {
            this.list = list;
        }

        public static class MonthSumber {
            private int stepNumber;  //步数

            private double kilometre;  //公里数

            private double catabiotic;  //消耗能量

            private String date;  //时间

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getStepNumber() {
                return stepNumber;
            }

            public void setStepNumber(int stepNumber) {
                this.stepNumber = stepNumber;
            }

            public double getKilometre() {
                return kilometre;
            }

            public void setKilometre(double kilometre) {
                this.kilometre = kilometre;
            }

            public double getCatabiotic() {
                return catabiotic;
            }

            public void setCatabiotic(double catabiotic) {
                this.catabiotic = catabiotic;
            }

            @Override
            public String toString() {
                return "MonthSumber [stepNumber=" + stepNumber + ", kilometre=" + kilometre + ", catabiotic=" + catabiotic
                        + "]";
            }
        }
    }

}
