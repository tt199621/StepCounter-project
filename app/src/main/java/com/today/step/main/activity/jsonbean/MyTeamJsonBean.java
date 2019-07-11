package com.today.step.main.activity.jsonbean;

import java.util.List;

public class MyTeamJsonBean {

	/**
	 * code : 100
	 * msg : 成功
	 * extend : {"totalTeamUser":{"referrerUser":{"id":"5d009b3392588493640e922c9","nickName":"一号","img":null},"peopleNum":5,"livenessNum":0,"livenessHero":0,"livenessAlliance":0,"list":[{"id":"5d00aa4a12c784938b800cc5","imgPath":null,"nickName":"要溢欧","isAutonym":0,"liveness":0,"teamSize":0,"teamLiveness":0},{"id":"5d00aa5a12c784938d800cc5","imgPath":null,"nickName":"谬贵","isAutonym":0,"liveness":0,"teamSize":0,"teamLiveness":0},{"id":"5d00aa5d12c784938f800cc5","imgPath":null,"nickName":"植胆","isAutonym":0,"liveness":0,"teamSize":0,"teamLiveness":0},{"id":"5d00aa6012c7849391800cc5","imgPath":null,"nickName":"惋级","isAutonym":0,"liveness":0,"teamSize":0,"teamLiveness":0},{"id":"5d00aa6312c7849393800cc5","imgPath":null,"nickName":"累笼","isAutonym":0,"liveness":0,"teamSize":0,"teamLiveness":0}]}}
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
		 * totalTeamUser : {"referrerUser":{"id":"5d009b3392588493640e922c9","nickName":"一号","img":null},"peopleNum":5,"livenessNum":0,"livenessHero":0,"livenessAlliance":0,"list":[{"id":"5d00aa4a12c784938b800cc5","imgPath":null,"nickName":"要溢欧","isAutonym":0,"liveness":0,"teamSize":0,"teamLiveness":0},{"id":"5d00aa5a12c784938d800cc5","imgPath":null,"nickName":"谬贵","isAutonym":0,"liveness":0,"teamSize":0,"teamLiveness":0},{"id":"5d00aa5d12c784938f800cc5","imgPath":null,"nickName":"植胆","isAutonym":0,"liveness":0,"teamSize":0,"teamLiveness":0},{"id":"5d00aa6012c7849391800cc5","imgPath":null,"nickName":"惋级","isAutonym":0,"liveness":0,"teamSize":0,"teamLiveness":0},{"id":"5d00aa6312c7849393800cc5","imgPath":null,"nickName":"累笼","isAutonym":0,"liveness":0,"teamSize":0,"teamLiveness":0}]}
		 */

		private TotalTeamUserBean totalTeamUser;

		public TotalTeamUserBean getTotalTeamUser() {
			return totalTeamUser;
		}

		public void setTotalTeamUser(TotalTeamUserBean totalTeamUser) {
			this.totalTeamUser = totalTeamUser;
		}

		public static class TotalTeamUserBean {
			/**
			 * referrerUser : {"id":"5d009b3392588493640e922c9","nickName":"一号","img":null}
			 * peopleNum : 5
			 * livenessNum : 0
			 * livenessHero : 0
			 * livenessAlliance : 0
			 * list : [{"id":"5d00aa4a12c784938b800cc5","imgPath":null,"nickName":"要溢欧","isAutonym":0,"liveness":0,"teamSize":0,"teamLiveness":0},{"id":"5d00aa5a12c784938d800cc5","imgPath":null,"nickName":"谬贵","isAutonym":0,"liveness":0,"teamSize":0,"teamLiveness":0},{"id":"5d00aa5d12c784938f800cc5","imgPath":null,"nickName":"植胆","isAutonym":0,"liveness":0,"teamSize":0,"teamLiveness":0},{"id":"5d00aa6012c7849391800cc5","imgPath":null,"nickName":"惋级","isAutonym":0,"liveness":0,"teamSize":0,"teamLiveness":0},{"id":"5d00aa6312c7849393800cc5","imgPath":null,"nickName":"累笼","isAutonym":0,"liveness":0,"teamSize":0,"teamLiveness":0}]
			 */

			private ReferrerUserBean referrerUser;
			private int peopleNum;
			private int livenessNum;
			private int livenessHero;
			private int livenessAlliance;
			private List<ListBean> list;

			public ReferrerUserBean getReferrerUser() {
				return referrerUser;
			}

			public void setReferrerUser(ReferrerUserBean referrerUser) {
				this.referrerUser = referrerUser;
			}

			public int getPeopleNum() {
				return peopleNum;
			}

			public void setPeopleNum(int peopleNum) {
				this.peopleNum = peopleNum;
			}

			public int getLivenessNum() {
				return livenessNum;
			}

			public void setLivenessNum(int livenessNum) {
				this.livenessNum = livenessNum;
			}

			public int getLivenessHero() {
				return livenessHero;
			}

			public void setLivenessHero(int livenessHero) {
				this.livenessHero = livenessHero;
			}

			public int getLivenessAlliance() {
				return livenessAlliance;
			}

			public void setLivenessAlliance(int livenessAlliance) {
				this.livenessAlliance = livenessAlliance;
			}

			public List<ListBean> getList() {
				return list;
			}

			public void setList(List<ListBean> list) {
				this.list = list;
			}

			public static class ReferrerUserBean {
				/**
				 * id : 5d009b3392588493640e922c9
				 * nickName : 一号
				 * img : null
				 */

				private String id;
				private String nickName;
				private Object img;

				public String getId() {
					return id;
				}

				public void setId(String id) {
					this.id = id;
				}

				public String getNickName() {
					return nickName;
				}

				public void setNickName(String nickName) {
					this.nickName = nickName;
				}

				public Object getImg() {
					return img;
				}

				public void setImg(Object img) {
					this.img = img;
				}
			}

			public static class ListBean {
				/**
				 * id : 5d00aa4a12c784938b800cc5
				 * imgPath : null
				 * nickName : 要溢欧
				 * isAutonym : 0
				 * liveness : 0
				 * teamSize : 0
				 * teamLiveness : 0
				 */

				private String id;
				private Object imgPath;
				private String nickName;
				private int isAutonym;
				private int liveness;
				private int teamSize;
				private int teamLiveness;

				public String getId() {
					return id;
				}

				public void setId(String id) {
					this.id = id;
				}

				public Object getImgPath() {
					return imgPath;
				}

				public void setImgPath(Object imgPath) {
					this.imgPath = imgPath;
				}

				public String getNickName() {
					return nickName;
				}

				public void setNickName(String nickName) {
					this.nickName = nickName;
				}

				public int getIsAutonym() {
					return isAutonym;
				}

				public void setIsAutonym(int isAutonym) {
					this.isAutonym = isAutonym;
				}

				public int getLiveness() {
					return liveness;
				}

				public void setLiveness(int liveness) {
					this.liveness = liveness;
				}

				public int getTeamSize() {
					return teamSize;
				}

				public void setTeamSize(int teamSize) {
					this.teamSize = teamSize;
				}

				public int getTeamLiveness() {
					return teamLiveness;
				}

				public void setTeamLiveness(int teamLiveness) {
					this.teamLiveness = teamLiveness;
				}
			}
		}
	}
}
