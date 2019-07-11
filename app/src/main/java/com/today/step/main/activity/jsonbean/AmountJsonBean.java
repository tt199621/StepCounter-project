package com.today.step.main.activity.jsonbean;

import java.util.List;

public class AmountJsonBean {


	/**
	 * code : 100
	 * msg : 成功
	 * extend : {"result":{"totalFruitTrees":469,"totalValue":703.5,"list":[{"id":"2dsfadsafw","userId":"5d009b3392588493640e9bc9","quantity":88,"state":2,"shijian":"2019-04-20 13:41:58"},{"id":"324dfsf","userId":"5d009b3392588493640e9bc9","quantity":100,"state":1,"shijian":"2019-04-20 13:41:58"}]}}
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
		 * result : {"totalFruitTrees":469,"totalValue":703.5,"list":[{"id":"2dsfadsafw","userId":"5d009b3392588493640e9bc9","quantity":88,"state":2,"shijian":"2019-04-20 13:41:58"},{"id":"324dfsf","userId":"5d009b3392588493640e9bc9","quantity":100,"state":1,"shijian":"2019-04-20 13:41:58"}]}
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
			 * totalFruitTrees : 469
			 * totalValue : 703.5
			 * list : [{"id":"2dsfadsafw","userId":"5d009b3392588493640e9bc9","quantity":88,"state":2,"shijian":"2019-04-20 13:41:58"},{"id":"324dfsf","userId":"5d009b3392588493640e9bc9","quantity":100,"state":1,"shijian":"2019-04-20 13:41:58"}]
			 */

			private int totalFruitTrees;
			private double totalValue;
			private List<ListBean> list;

			public int getTotalFruitTrees() {
				return totalFruitTrees;
			}

			public void setTotalFruitTrees(int totalFruitTrees) {
				this.totalFruitTrees = totalFruitTrees;
			}

			public double getTotalValue() {
				return totalValue;
			}

			public void setTotalValue(double totalValue) {
				this.totalValue = totalValue;
			}

			public List<ListBean> getList() {
				return list;
			}

			public void setList(List<ListBean> list) {
				this.list = list;
			}

			public static class ListBean {
				/**
				 * id : 2dsfadsafw
				 * userId : 5d009b3392588493640e9bc9
				 * quantity : 88
				 * state : 2
				 * shijian : 2019-04-20 13:41:58
				 */

				private String id;
				private String userId;
				private int quantity;
				private int state;
				private String shijian;

				public String getId() {
					return id;
				}

				public void setId(String id) {
					this.id = id;
				}

				public String getUserId() {
					return userId;
				}

				public void setUserId(String userId) {
					this.userId = userId;
				}

				public int getQuantity() {
					return quantity;
				}

				public void setQuantity(int quantity) {
					this.quantity = quantity;
				}

				public int getState() {
					return state;
				}

				public void setState(int state) {
					this.state = state;
				}

				public String getShijian() {
					return shijian;
				}

				public void setShijian(String shijian) {
					this.shijian = shijian;
				}
			}
		}
	}
}
