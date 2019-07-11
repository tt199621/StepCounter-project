package com.today.step.main.activity.jsonbean;

public class ShareJsonBean {


	//http://localhost:8080/qubu/step/shareData

	/**
	 * code : 100
	 * msg : 成功
	 * extend : {"ShareBo":{"id":"5d118c321bbb343abc3384de","stepNumber":3000,"kilometre":3,"catabiotic":123,"fruiter":0}}
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
		 * ShareBo : {"id":"5d118c321bbb343abc3384de","stepNumber":3000,"kilometre":3,"catabiotic":123,"fruiter":0}
		 */

		private ShareBoBean ShareBo;

		public ShareBoBean getShareBo() {
			return ShareBo;
		}

		public void setShareBo(ShareBoBean ShareBo) {
			this.ShareBo = ShareBo;
		}

		public static class ShareBoBean {
			/**
			 * id : 5d118c321bbb343abc3384de
			 * stepNumber : 3000
			 * kilometre : 3
			 * catabiotic : 123
			 * fruiter : 0
			 */

			private String id;
			private int stepNumber;
			private double kilometre;
			private int catabiotic;
			private int fruiter;

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
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

			public int getCatabiotic() {
				return catabiotic;
			}

			public void setCatabiotic(int catabiotic) {
				this.catabiotic = catabiotic;
			}

			public int getFruiter() {
				return fruiter;
			}

			public void setFruiter(int fruiter) {
				this.fruiter = fruiter;
			}
		}
	}
}
