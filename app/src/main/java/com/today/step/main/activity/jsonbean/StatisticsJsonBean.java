package com.today.step.main.activity.jsonbean;

import java.util.List;

public class StatisticsJsonBean {


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
		private List<ListBean> list;

		public List<ListBean> getList() {
			return list;
		}

		public void setList(List<ListBean> list) {
			this.list = list;
		}

		public static class ListBean {
			/**
			 * step : 3000
			 * time : 2019-06-27
			 */

			private int step;
			private String time;
			private double kilometres;
			private double energy;
			public int getStep() {
				return step;
			}

			public void setStep(int step) {
				this.step = step;
			}

			public String getTime() {
				return time;
			}

			public void setTime(String time) {
				this.time = time;
			}

			public double getKilometres() {
				return kilometres;
			}

			public void setKilometres(double kilometres) {
				this.kilometres = kilometres;
			}

			public double getEnergy() {
				return energy;
			}

			public void setEnergy(double energy) {
				this.energy = energy;
			}
		}
	}
}
