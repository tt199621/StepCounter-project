package com.today.step.main.activity.jsonbean;

public class IdentityJsonBean {


	/**
	 * code : 100
	 * msg : 成功
	 * extend : {"user":{"id":"5d11be00566dea86fbf98286","username":null,"password":"c4ca4238a0b923820dcc509a6f75849b","phone":"15914108018","headPic":null,"nickname":"唤静","sex":"男","changeNumber":3,"referrer":"123456","promotionCode":"47645838","cid":"6798395738247","weixinCard":null,"phoneId":null,"creatTime":"2019-06-25","grade":0}}
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
		 * user : {"id":"5d11be00566dea86fbf98286","username":null,"password":"c4ca4238a0b923820dcc509a6f75849b","phone":"15914108018","headPic":null,"nickname":"唤静","sex":"男","changeNumber":3,"referrer":"123456","promotionCode":"47645838","cid":"6798395738247","weixinCard":null,"phoneId":null,"creatTime":"2019-06-25","grade":0}
		 */

		private UserBean user;

		public UserBean getUser() {
			return user;
		}

		public void setUser(UserBean user) {
			this.user = user;
		}

		public static class UserBean {
			/**
			 * id : 5d11be00566dea86fbf98286
			 * username : null
			 * password : c4ca4238a0b923820dcc509a6f75849b
			 * phone : 15914108018
			 * headPic : null
			 * nickname : 唤静
			 * sex : 男
			 * changeNumber : 3
			 * referrer : 123456
			 * promotionCode : 47645838
			 * cid : 6798395738247
			 * weixinCard : null
			 * phoneId : null
			 * creatTime : 2019-06-25
			 * grade : 0
			 */

			private String id;
			private Object username;
			private String password;
			private String phone;
			private Object headPic;
			private String nickname;
			private String sex;
			private int changeNumber;
			private String referrer;
			private String promotionCode;
			private String cid;
			private Object weixinCard;
			private Object phoneId;
			private String creatTime;
			private int grade;

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

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}

			public String getPhone() {
				return phone;
			}

			public void setPhone(String phone) {
				this.phone = phone;
			}

			public Object getHeadPic() {
				return headPic;
			}

			public void setHeadPic(Object headPic) {
				this.headPic = headPic;
			}

			public String getNickname() {
				return nickname;
			}

			public void setNickname(String nickname) {
				this.nickname = nickname;
			}

			public String getSex() {
				return sex;
			}

			public void setSex(String sex) {
				this.sex = sex;
			}

			public int getChangeNumber() {
				return changeNumber;
			}

			public void setChangeNumber(int changeNumber) {
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

			public String getCid() {
				return cid;
			}

			public void setCid(String cid) {
				this.cid = cid;
			}

			public Object getWeixinCard() {
				return weixinCard;
			}

			public void setWeixinCard(Object weixinCard) {
				this.weixinCard = weixinCard;
			}

			public Object getPhoneId() {
				return phoneId;
			}

			public void setPhoneId(Object phoneId) {
				this.phoneId = phoneId;
			}

			public String getCreatTime() {
				return creatTime;
			}

			public void setCreatTime(String creatTime) {
				this.creatTime = creatTime;
			}

			public int getGrade() {
				return grade;
			}

			public void setGrade(int grade) {
				this.grade = grade;
			}
		}
	}
}
