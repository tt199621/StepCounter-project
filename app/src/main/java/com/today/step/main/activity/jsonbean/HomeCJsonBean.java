package com.today.step.main.activity.jsonbean;

public class HomeCJsonBean {

	//
	/**
	 * code : 100
	 * msg : 成功
	 * extend : {"result":{"id":9,"content":"接待我爱上的简欧风科技噢ID放假哦按时交我我的是佛家具哦","creatTime":"2019-06-27 17:23:09"}}
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
		 * result : {"id":9,"content":"接待我爱上的简欧风科技噢ID放假哦按时交我我的是佛家具哦","creatTime":"2019-06-27 17:23:09"}
		 */

		private ResultBean result;

		public ResultBean getResult() {
			return result;
		}

		public void setResult(ResultBean result) {
			this.result = result;
		}

		public static class ResultBean {
			/**
			 * id : 9
			 * content : 接待我爱上的简欧风科技噢ID放假哦按时交我我的是佛家具哦
			 * creatTime : 2019-06-27 17:23:09
			 */

			private int id;
			private String content;
			private String creatTime;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public String getCreatTime() {
				return creatTime;
			}

			public void setCreatTime(String creatTime) {
				this.creatTime = creatTime;
			}
		}
	}
}
