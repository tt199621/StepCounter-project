package com.today.step.main.activity.jsonbean;

import java.util.List;

public class TaskJsonBean {


	/**
	 * code : 100
	 * msg : 成功
	 * extend : {"list":[{"id":"5d01e62cfde9a4adb2fa9c47","taskName":"初级任务","guoshu":12,"award":20,"yaoqiu":3500,"vitality":10,"taskLevel":"初级","idPutaway":1,"createTime":"2019-06-13","code":3},{"id":"5d01e9ecf904a4adc36bd771","taskName":"中级任务","guoshu":30,"award":45,"yaoqiu":3500,"vitality":10,"taskLevel":"中级","idPutaway":1,"createTime":"2019-06-13","code":2},{"id":"5d01efa893afa4adcc50ee5d","taskName":"高级任务","guoshu":60,"award":100,"yaoqiu":3500,"vitality":120,"taskLevel":"高级","idPutaway":1,"createTime":"2019-06-13","code":1},{"id":"5d01efd993afa4adcd50ee5d","taskName":"特级任务","guoshu":100,"award":200,"yaoqiu":3500,"vitality":150,"taskLevel":"特级","idPutaway":1,"createTime":"2019-06-13","code":1},{"id":"5d01eff593afa4adce50ee5d","taskName":"终极任务","guoshu":200,"award":500,"yaoqiu":3500,"vitality":200,"taskLevel":"终极","idPutaway":1,"createTime":"2019-06-13","code":1},{"id":"5d034212763c84935472444f","taskName":"中级任务","guoshu":1,"award":105,"yaoqiu":100,"vitality":50,"taskLevel":"中级","idPutaway":1,"createTime":"2019-06-14","code":1}]}
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
			 * id : 5d01e62cfde9a4adb2fa9c47
			 * taskName : 初级任务
			 * guoshu : 12
			 * award : 20
			 * yaoqiu : 3500
			 * vitality : 10
			 * taskLevel : 初级
			 * idPutaway : 1
			 * createTime : 2019-06-13
			 * code : 3
			 */

			private String id;
			private String taskName;
			private int guoshu;
			private int award;
			private int yaoqiu;
			private int vitality;
			private String taskLevel;
			private int idPutaway;
			private String createTime;
			private int code;

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getTaskName() {
				return taskName;
			}

			public void setTaskName(String taskName) {
				this.taskName = taskName;
			}

			public int getGuoshu() {
				return guoshu;
			}

			public void setGuoshu(int guoshu) {
				this.guoshu = guoshu;
			}

			public int getAward() {
				return award;
			}

			public void setAward(int award) {
				this.award = award;
			}

			public int getYaoqiu() {
				return yaoqiu;
			}

			public void setYaoqiu(int yaoqiu) {
				this.yaoqiu = yaoqiu;
			}

			public int getVitality() {
				return vitality;
			}

			public void setVitality(int vitality) {
				this.vitality = vitality;
			}

			public String getTaskLevel() {
				return taskLevel;
			}

			public void setTaskLevel(String taskLevel) {
				this.taskLevel = taskLevel;
			}

			public int getIdPutaway() {
				return idPutaway;
			}

			public void setIdPutaway(int idPutaway) {
				this.idPutaway = idPutaway;
			}

			public String getCreateTime() {
				return createTime;
			}

			public void setCreateTime(String createTime) {
				this.createTime = createTime;
			}

			public int getCode() {
				return code;
			}

			public void setCode(int code) {
				this.code = code;
			}
		}
	}
}
