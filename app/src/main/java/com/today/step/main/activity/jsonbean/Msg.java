package com.today.step.main.activity.jsonbean;

import java.util.List;

public class Msg {
	/**
	 * code : 100
	 * msg : 成功
	 * extend : {"list":[{"step":3000,"time":"2019-06-27"},{"step":3001,"time":"2019-06-26"},{"step":3002,"time":"2019-06-25"},{"step":3003,"time":"2019-06-24"},{"step":3004,"time":"2019-06-23"},{"step":3005,"time":"2019-06-22"},{"step":3006,"time":"2019-06-21"},{"step":3007,"time":"2019-06-20"},{"step":3008,"time":"2019-06-19"},{"step":3009,"time":"2019-06-18"},{"step":3010,"time":"2019-06-17"},{"step":3011,"time":"2019-06-16"}]}
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
		private List<WeekSumber> list;

		public List<WeekSumber> getList() {
			return list;
		}

		public void setList(List<WeekSumber> list) {
			this.list = list;
		}

		public static class WeekSumber {
			/**
			 * step : 3000
			 * time : 2019-06-27
			 */

			private int stepNumber;  //步数

			private double kilometre;  //公里数

			private double catabiotic;  //消耗能量

			private String date;    //时间

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
				return "WeekSumber [stepNumber=" + stepNumber + ", kilometre=" + kilometre + ", catabiotic=" + catabiotic + "]";
			}
		}
		}

}
