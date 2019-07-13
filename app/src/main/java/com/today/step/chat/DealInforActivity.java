package com.today.step.chat;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.today.step.MyActivity;
import com.today.step.NetWorkURL;
import com.today.step.chat.jsonbean.ChatJsonBean;
import com.today.step.chat.jsonbean.OrderJSonBean;
import com.today.step.R;

import org.greenrobot.eventbus.EventBus;

/**
 * 查询交易订单
 * */
public class DealInforActivity extends MyActivity {


	private TextView tv_amount,tv_moeny,tv_pic,tv_nickname,tv_realname,tv_alipay;
	private ProgressDialog progressDialog;
	String orderid ;
	private Button enter_order;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deal_infor);

		orderid = getIntent().getStringExtra("orderId");
		/******标题栏初始化******/
		TextView title = (TextView)findViewById(R.id.title_text);
		title.setText("交易信息");
		//标题右侧按钮
		final TextView title1 = (TextView)findViewById(R.id.title_btn);
		title1.setVisibility(View.GONE);
		Button button = (Button)findViewById(R.id.title_back);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				//Toast.makeText(IdentityInfoActivity.this,"onclick button",Toast.LENGTH_SHORT).show();
			}
		});
		OkGoInit();
		initView();
		Log.d("---------",orderid);
	}

	private void initView(){

		tv_amount = (TextView)findViewById(R.id.deal_infor_amount);
		tv_moeny = (TextView)findViewById(R.id.deal_infor_moeny);
		tv_alipay = (TextView)findViewById(R.id.deal_infor_alipay);
		tv_nickname = (TextView)findViewById(R.id.deal_infor_nickname);
		tv_realname = (TextView)findViewById(R.id.deal_infor_realname);
		tv_pic = (TextView)findViewById(R.id.deal_infor_pic);
		enter_order=findViewById(R.id.enter_order);
		enter_order.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				OkGoCreateOrder();
			}
		});

	}
	private void OkGoCreateOrder() {
		//正在加载弹窗
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(DealInforActivity.this, "", "加载中，请稍候", false, false);
		} else if (progressDialog.isShowing()) {
			progressDialog.setTitle("");
			progressDialog.setMessage("加载中，请稍候");
		}
		progressDialog.show();
		/**********************/

//        Log.d("--cid",""+ getDeviceID.getDeviceID());
		OkGo.<String>post(NetWorkURL.USER_ENTER_ORDER)
				.tag(this)
				.isMultipart(true)
				.params("id", orderid)
				.execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(DealInforActivity.this, "提交订单成功", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);
                        Log.d("进度",progress+"");
                    }
                });

	}


	/**
	 * 查询订单详情
	 * */
	private void OkGoInit(){
		//正在加载弹窗
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(DealInforActivity.this, "","加载中，请稍候", false, false);
		} else if (progressDialog.isShowing()) {
			progressDialog.setTitle("");
			progressDialog.setMessage("加载中，请稍候");
		}
		progressDialog.show();
		/**********************/

        Log.d("--id",""+ orderid);
		OkGo.<String>post(NetWorkURL.USER_SELECT_ORDER)
				.tag(this)
				.isMultipart(true)
				.params("id",orderid)
				.execute(new com.lzy.okgo.callback.StringCallback() {
					@Override
					public void onSuccess(Response<String> response) {
						Log.d("--forget onSuccess",""+response.body());
						//JSON.parseObject(response.toString(), NoteDataJavaBean.class);
						OrderJSonBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), OrderJSonBean.class);
						if (jsonBean.getCode() == 100){
//                            startActivity(new Intent(ForgetPwActivity.this,HomeActivity.class));
							Toast.makeText(DealInforActivity.this,"成功",Toast.LENGTH_SHORT).show();

							tv_alipay.setText(jsonBean.getExtend().getTradeOrder().getAlipay());;//支付宝
							tv_nickname.setText(""+jsonBean.getExtend().getTradeOrder().getSellerRealName());;//买家昵称
							tv_realname.setText(""+jsonBean.getExtend().getTradeOrder().getBuyerRealName());;//买家真实姓名
							tv_amount.setText(""+jsonBean.getExtend().getTradeOrder().getQuantity());;//人参果数
							tv_moeny.setText(""+jsonBean.getExtend().getTradeOrder().getTransactionNumber()); ;//交易金额
							tv_pic.setText(""+jsonBean.getExtend().getTradeOrder().getOrderNumber());;//单价
//							jsonBean.getExtend().getTradeOrder().getUnitPrice();//手续费


							//finish();
						}else {
							Toast.makeText(DealInforActivity.this,"失败，错误:"+jsonBean.getMsg(),Toast.LENGTH_SHORT).show();
						}
						//关闭正在加载弹窗
						if (progressDialog != null && progressDialog.isShowing()) {
							progressDialog.dismiss();
						}
					}
					@Override
					public void onError(Response<String> response) {
						Log.d("--forget onError",""+response.body());
						//关闭正在加载弹窗
						if (progressDialog != null && progressDialog.isShowing()) {
							progressDialog.dismiss();
						}
						Toast.makeText(DealInforActivity.this,"请求失败,错误："+ response.body(),Toast.LENGTH_SHORT).show();
					}
				});
	}

}
