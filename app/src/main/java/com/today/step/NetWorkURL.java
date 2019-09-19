package com.today.step;

public class NetWorkURL {

//	public static final String BASE_URL = "https:www.yuebu.shop/qubu/";
//	public static final String BASE_URL = "http://192.168.43.120/qubu/";//http://192.168.5.45:8080/qubu/swagger-ui.html

	public static final String BASE_IMAGE = "https:www.yuebu.shop/images";

	public static final String BASE_URL = "https:www.wxxcx.club/qubu/";

	//用户注册
	public static final String USER_REGISTER = BASE_URL + "user/userregister";

	//
	//用户注册
	public static final String USER_CODE = BASE_URL + "user/getCode";

	//用户登录
	public static final String USER_LOGIN = BASE_URL + "user/userlogin";

	//判断是否登录
	public static final String IFLOGIN=BASE_URL+"user/panduanshifoulogin";

	//用户退出登录
	public static final String USER_EXIT = BASE_URL + "user/userexit";

	//用户解除设备绑定
	public static final String USER_UNBIND = BASE_URL + "user/amendmentno";

	//初始化首页数据
	public static final String USER_INIT_HOME = BASE_URL + "user/indexParameter";

	//用户更新信息
	public static final String USER_UPDATA = BASE_URL + "user/updateXinxi";

	//
	//用户更新信息
	public static final String USER_HISTORY = BASE_URL + "rollInOut/select";
	//查询我的团队
//	public static final String USER_SELECT_TEAM = BASE_URL + "user/selectMyTeam";

	//
	//忘记密码
	public static final String USER_FORGET_PW = BASE_URL + "user/forgetPass";

	//初始化我的团队数据
	public static final String USER_MY_TEAM_INIT = BASE_URL + "user/selectMyTeam";


	//更新服务器步数(上传当前步数)
	public static final String USER_MY_STEPS = BASE_URL + "step/recordNumber";

	//初始化任务
	public static final String USER_TASK  = BASE_URL + "task/selectTaskForQianduan";

	//实名认证
	public static final String USER_REAL_NAME = BASE_URL + "certification/insert";

	//兑换任务
	public static final String USER_BUY_TASK  = BASE_URL + "task/completionTask";

	//个人信息查询
	public static final String USER_INIT_INFORMATION  = BASE_URL + "user/selectXinxiByUserId";
	//认证
	public static final String USER_RENZHENG_INFORMATION  = BASE_URL + "user/updatePass";
	//发起订单
	public static final String USER_CREATE_ORDER  = BASE_URL + "tradeOrder/insert";
	//提交订单
	public static final String USER_ENTER_ORDER  = BASE_URL + "tradeOrder/confirmOrder";
	//取消订单
	public static final String USER_CANCEL_ORDER  = BASE_URL + "tradeOrder/cancellationOfTradeOrder";
	//确认付款
	public static final String USER_OK_ORDER  = BASE_URL + "tradeOrder/accountPaidTradeOrder";

	//根据订单id查询订单详情
	public static final String USER_SELECT_ORDER  = BASE_URL + "tradeOrder/selectXinxiById";

	//人参果总数以及转入转出记录
	public static final String USER_SELECT_INFORMATION  = BASE_URL + "rollInOut/select";

	//运动统计
	public static final String USER_SELECT_STEP  =BASE_URL + "step/sevenConsecutiveDays";//+ "http://192.168.0.138:8080/qubu/"
	//每周
	public static final String USER_WEEK_STEP  =BASE_URL + "step/fiveWeeks";
	//每月
	public static final String USER_MONTH_STEP  =BASE_URL + "step/fiveMonth";
	//分享请求查询
	public static final String USER_SHARE_STEP = BASE_URL+ "step/shareData";//http://192.168.0.138:8080/qubu/

	//
	public static final String USER_INFORMATION = BASE_URL + "message/select";//http://192.168.0.138:8080/qubu/
	//查询所有订单
	public static final String SELECT_ALL_ORDER=BASE_URL+"tradeOrder/checkAllOrders";

	//查询待支付的订单
	public static final String SELECT_WAIT_PAY_ORDER=BASE_URL+"tradeOrder/inquireAboutPendingPayment";

	//请求预订单
	public static final String SELECT_PRAPARE_ORDER=BASE_URL+"v1/weixin/apppay.json";

	//卖家确认收款
	public static final String SELLER_CONFIGURE_ORDER=BASE_URL+"tradeOrder/update";

	//我要申诉
	public static final String Appeal=BASE_URL+"tradeOrder/grievanceTradingOrder";

	//取消申诉
	public static final String CANCEL_APPEALING=BASE_URL+"tradeOrder/quxiaoshensu";

	//实名认证信息1
	public static final String UP_GRAGDE=BASE_URL+"certification/insertFirstStep";

	//实名认证信息2
	public static final String UP_MESSAGE=BASE_URL+"certification/insertSecondStep";

	//获得用户支付状态
	public static final String PAY_SITUATION=BASE_URL+"certification/selectdijibu";

	//添加认证次数
	public static final String ADD_RPC_TIME=BASE_URL+"certification/updatecishu";

	//身份等级
	public static final String LV_MESSAGE=BASE_URL+"intelligent/select";

}
