package com.today.step.beforelogin.json;

public class LoginJsonBean {


	/**
	 * code : 100
	 * msg : 成功
	 * extend : {"user":{"id":"5cfdfb0032a8e7db24d58e8a","username":null,"healthilyBean":null,"stepNumber":null,"headPic":null,"nickname":null,"sex":null,"creatTime":"19-6-10 下午2:38","changeNumber":null,"referrer":"123456","promotionCode":"35136456","qrCode":null,"token":"5cfe06806eace7db88551f99"}}
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
		 * user : {"id":"5cfdfb0032a8e7db24d58e8a","username":null,"healthilyBean":null,"stepNumber":null,"headPic":null,"nickname":null,"sex":null,"creatTime":"19-6-10 下午2:38","changeNumber":null,"referrer":"123456","promotionCode":"35136456","qrCode":null,"token":"5cfe06806eace7db88551f99"}
		 */

		private UserBean user;
		private String msg;
		private int isPass;
		private int dengji;

		public int getIsPass() {
			return isPass;
		}

		public void setIsPass(int isPass) {
			this.isPass = isPass;
		}

		public int getDengji() {
			return dengji;
		}

		public void setDengji(int dengji) {
			this.dengji = dengji;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public UserBean getUser() {
			return user;
		}

		public void setUser(UserBean user) {
			this.user = user;
		}

		public static class UserBean {
			/**
			 * id : 5cfdfb0032a8e7db24d58e8a
			 * username : null
			 * healthilyBean : null
			 * stepNumber : null
			 * headPic : null
			 * nickname : null
			 * sex : null
			 * creatTime : 19-6-10 下午2:38
			 * changeNumber : null
			 * referrer : 123456
			 * promotionCode : 35136456
			 * qrCode : null
			 * token : 5cfe06806eace7db88551f99
			 */

			private String id;
			private Object username;
			private Object healthilyBean;
			private Object stepNumber;
			private Object headPic;
			private Object nickname;
			private Object sex;
			private String creatTime;
			private Object changeNumber;
			private String referrer;
			private String promotionCode;
			private Object qrCode;
			private String token;

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public Object getUsername() {
				return username;
			}

			public void setUsername(Object username) {
				this.username = username;
			}

			public Object getHealthilyBean() {
				return healthilyBean;
			}

			public void setHealthilyBean(Object healthilyBean) {
				this.healthilyBean = healthilyBean;
			}

			public Object getStepNumber() {
				return stepNumber;
			}

			public void setStepNumber(Object stepNumber) {
				this.stepNumber = stepNumber;
			}

			public Object getHeadPic() {
				return headPic;
			}

			public void setHeadPic(Object headPic) {
				this.headPic = headPic;
			}

			public Object getNickname() {
				return nickname;
			}

			public void setNickname(Object nickname) {
				this.nickname = nickname;
			}

			public Object getSex() {
				return sex;
			}

			public void setSex(Object sex) {
				this.sex = sex;
			}

			public String getCreatTime() {
				return creatTime;
			}

			public void setCreatTime(String creatTime) {
				this.creatTime = creatTime;
			}

			public Object getChangeNumber() {
				return changeNumber;
			}

			public void setChangeNumber(Object changeNumber) {
				this.changeNumber = changeNumber;
			}

			public String getReferrer() {
				return referrer;
			}

			public void setReferrer(String referrer) {
				this.referrer = referrer;
			}

			public String getPromotionCode() {
				return promotionCode;
			}

			public void setPromotionCode(String promotionCode) {
				this.promotionCode = promotionCode;
			}

			public Object getQrCode() {
				return qrCode;
			}

			public void setQrCode(Object qrCode) {
				this.qrCode = qrCode;
			}

			public String getToken() {
				return token;
			}

			public void setToken(String token) {
				this.token = token;
			}
		}
	}
}
