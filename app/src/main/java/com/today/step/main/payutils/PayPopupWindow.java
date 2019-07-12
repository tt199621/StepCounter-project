package com.today.step.main.payutils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.today.step.NetWorkURL;
import com.today.step.R;
import com.today.step.main.activity.jsonbean.WxPayBean;


/**
 *  PopupWindow支付弹窗
 *  PayPopupWindow payPopupWindow = new PayPopupWindow(Home_mc_activity.this,listener);
 *  payPopupWindow.showAtLocation(Home_mc_activity.this.findViewById(R.id.home_mc_act), Gravity.CENTER | Gravity.CENTER, 0, 0);
 *	调用上面的方法即可弹出界面
 * **/
public class PayPopupWindow extends PopupWindow{
	private View window;


	private LinearLayout paylayout;
	private Button ok;
	private TextView name,moeny;
	private CheckBox pwechat,palipay;
	public Context context;

	private String nonceStr;
	private String paySign;
	private String prepay_id;
	private String timeStamp;
	private int code;
	private TextView pay_way_name,pay_way_moeny;
	private String userID;

	public PayPopupWindow(final Context context, int code, String userID){
		super(context);
		this.context = context;
		this.code=code;
		this.userID=userID;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		window = inflater.inflate(R.layout.pay_popup_window, null);

		pay_way_name=window.findViewById(R.id.pay_way_name);
		pay_way_moeny=window.findViewById(R.id.pay_way_moeny);
		pay_way_moeny.setText(code+"元");
		switch (code){
			case 1:
				pay_way_name.setText("初级实名认证");
				break;
			case 3:
				pay_way_name.setText("中级实名认证");
				break;
			case 5:
				pay_way_name.setText("高级实名认证");
				break;
		}
		//1.发送预支付请求，返回调起支付所需数据
		sendRequest(NetWorkURL.SELECT_PRAPARE_ORDER);

		paylayout = (LinearLayout)window.findViewById(R.id.pay_layout);
		ok = (Button)window.findViewById(R.id.pay_ok);
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!pwechat.isChecked() && !palipay.isChecked()){
					Toast.makeText(context,"请选择支付方式",Toast.LENGTH_SHORT).show();
				}else {
					if (pwechat.isChecked()){
						//调微信支付
						//2.调起支付
						toWXPay();
						dismiss();//销毁弹窗


					}else {
						//支付宝支付
						Toast.makeText(context,"支付宝支付",Toast.LENGTH_SHORT).show();
						dismiss();

					}

				}
			}
		});

		name = (TextView)window.findViewById(R.id.pay_way_name);
		moeny = (TextView)window.findViewById(R.id.pay_way_moeny);

		pwechat = (CheckBox)window.findViewById(R.id.pay_way_wechat);
		pwechat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				pwechat.setChecked(true);
//				palipay.setChecked(false);
				if (isChecked == true){
					palipay.setChecked(false);
				}else
					palipay.setChecked(true);
				Toast.makeText(context,""+isChecked,Toast.LENGTH_SHORT).show();
			}
		});
		palipay = (CheckBox)window.findViewById(R.id.pay_way_alipay);
		palipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked == false){
					pwechat.setChecked(true);
				}else
					pwechat.setChecked(false);
				Toast.makeText(context,""+isChecked,Toast.LENGTH_SHORT).show();
			}
		});

		//设置SelectPicPopupWindow的View
		this.setContentView(window);

		//设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

		//设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

		//设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);

		//实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);

		//设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);

		//设置SelectPicPopupWindow弹出窗体动画效果(从底部弹出效果)
		this.setAnimationStyle(R.style.pop_anim_style);

		//mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		window.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				int height = window.findViewById(R.id.pay_layout).getTop();
				int y=(int) event.getY();
				if(event.getAction()==MotionEvent.ACTION_UP){
					if(y<height){
						dismiss();//销毁
					}
				}
				return true;
			}
		});
	}


	//发送预支付请求
	public void sendRequest(String addres) {
		OkGo.<String>post(addres)
				.tag(this)
				.isMultipart(true)
				.params("userId",userID)//ID
				.params("totalFee",code)//金额
				.execute(new StringCallback() {

					@Override
					public void onSuccess(Response<String> response) {
						WxPayBean wxPayBean=com.alibaba.fastjson.JSON.parseObject(response.body(), WxPayBean.class);
						nonceStr=wxPayBean.getNoncestr();
						paySign=wxPayBean.getSign();
						prepay_id=wxPayBean.getPrepayid();
						timeStamp=wxPayBean.getTimestamp();

					}
				});
	}

	private void toWXPay() {
		final IWXAPI iwxapi;
		iwxapi = WXAPIFactory.createWXAPI(context, null); //初始化微信api
		iwxapi.registerApp("wx591efa3c85e5608b"); //注册appid  appid可以在开发平台获取

		if (!iwxapi.isWXAppInstalled()) {
			Toast.makeText(context,"您还没有安装微信",Toast.LENGTH_SHORT).show();
			return;
		}

		Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
			@Override
			public void run() {
				PayReq request = new PayReq(); //调起微信APP的对象
				//下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
				request.appId = "wx591efa3c85e5608b";
				request.partnerId = "1499812242";
				request.prepayId = prepay_id;
				request.packageValue = "Sign=WXPay";
				request.nonceStr = nonceStr;
				request.timeStamp = timeStamp;
				request.sign = paySign;
				iwxapi.sendReq(request);//发送调起微信的请求
				Log.d("================","prepayId "+prepay_id+"nonceStr "+nonceStr+"timeStamp "+timeStamp+"sign "+paySign);
			}
		};
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}


}
