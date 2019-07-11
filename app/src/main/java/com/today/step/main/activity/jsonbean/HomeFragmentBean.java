package com.today.step.main.activity.jsonbean;

public class HomeFragmentBean {


	/**
	 * code : 100
	 * msg : 成功
	 * extend : {"member":{"steps":0,"todayfruiter":0,"gradeMember":0,"contributionValue":0,"livenessValue":440,"todaylivenes":0,"fruiterValue":469,"promotionCode":"21263476"}}
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
		 * member : {"steps":0,"todayfruiter":0,"gradeMember":0,"contributionValue":0,"livenessValue":440,"todaylivenes":0,"fruiterValue":469,"promotionCode":"21263476"}
		 */

		private MemberBean member;

		public MemberBean getMember() {
			return member;
		}

		public void setMember(MemberBean member) {
			this.member = member;
		}

		public static class MemberBean {
			/**
			 * steps : 0
			 * todayfruiter : 0.0
			 * gradeMember : 0
			 * contributionValue : 0.0
			 * livenessValue : 440.0
			 * todaylivenes : 0.0
			 * fruiterValue : 469.0
			 * promotionCode : 21263476
			 */

			private int steps;
			private double todayfruiter;
			private int gradeMember;
			private double contributionValue;
			private double livenessValue;
			private double todaylivenes;
			private double fruiterValue;
			private String promotionCode;

			public int getSteps() {
				return steps;
			}

			public void setSteps(int steps) {
				this.steps = steps;
			}

			public double getTodayfruiter() {
				return todayfruiter;
			}

			public void setTodayfruiter(double todayfruiter) {
				this.todayfruiter = todayfruiter;
			}

			public int getGradeMember() {
				return gradeMember;
			}

			public void setGradeMember(int gradeMember) {
				this.gradeMember = gradeMember;
			}

			public double getContributionValue() {
				return contributionValue;
			}

			public void setContributionValue(double contributionValue) {
				this.contributionValue = contributionValue;
			}

			public double getLivenessValue() {
				return livenessValue;
			}

			public void setLivenessValue(double livenessValue) {
				this.livenessValue = livenessValue;
			}

			public double getTodaylivenes() {
				return todaylivenes;
			}

			public void setTodaylivenes(double todaylivenes) {
				this.todaylivenes = todaylivenes;
			}

			public double getFruiterValue() {
				return fruiterValue;
			}

			public void setFruiterValue(double fruiterValue) {
				this.fruiterValue = fruiterValue;
			}

			public String getPromotionCode() {
				return promotionCode;
			}

			public void setPromotionCode(String promotionCode) {
				this.promotionCode = promotionCode;
			}
		}
	}
}
