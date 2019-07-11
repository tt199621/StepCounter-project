package com.today.step.main.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.today.step.MyActivity;
import com.today.step.NetWorkURL;
import com.today.step.R;
import com.today.step.main.activity.jsonbean.HomeFragmentBean;
import com.today.step.main.payutils.PayPopupWindow;
import com.today.step.utils.getDeviceID;

/**
 * 实名认证
 * */
public class RealNameActivity extends MyActivity {

	private ProgressDialog progressDialog;
	private EditText et_name,et_id,et_bank_name,et_bank_id,et_alipay;
	private RadioGroup choose_lv;
	private Button bt_ok;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_real_name);

		/******标题栏初始化******/
		TextView title = (TextView)findViewById(R.id.title_text);
		title.setText("实名认证");
		//标题右侧按钮
		final TextView title1 = (TextView)findViewById(R.id.title_btn);
		title1.setVisibility(View.GONE);
//		title1.setText("");
		//标题返回按钮
		Button button = (Button)findViewById(R.id.title_back);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				//Toast.makeText(IdentityInfoActivity.this,"onclick button",Toast.LENGTH_SHORT).show();
			}
		});

		InitView();

	}


	private void InitView(){

		et_name = (EditText)findViewById(R.id.real_name_name);//真实姓名
		et_id = (EditText)findViewById(R.id.real_name_id);//身份证
		et_bank_name = (EditText)findViewById(R.id.real_name_card_name);//银行
		et_bank_id = (EditText)findViewById(R.id.real_name_card_id);//银行卡号
		et_alipay = (EditText)findViewById(R.id.real_name_alipay);//支付宝账号

		//
		bt_ok = (Button)findViewById(R.id.real_name_okbtn);
		bt_ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				PayPopupWindow payPopupWindow = new PayPopupWindow(RealNameActivity.this);
				payPopupWindow.showAtLocation(RealNameActivity.this.findViewById(R.id.real_layout), Gravity.CENTER | Gravity.CENTER, 0, 0);

			}
		});

		choose_lv = (RadioGroup)findViewById(R.id.radiogroup_lv) ;
		choose_lv.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				switch (i) { //1初级 2中级 3高级
					//初级认证
					case R.id.lv_1:
						//赋值
						Toast.makeText(RealNameActivity.this,"onclick button lv1",Toast.LENGTH_SHORT).show();
						break;
					//中级认证
					case R.id.lv_2:
						Toast.makeText(RealNameActivity.this,"onclick button lv2",Toast.LENGTH_SHORT).show();
						break;
					//高级认证
					case R.id.lv_3:
						Toast.makeText(RealNameActivity.this,"onclick button lv3",Toast.LENGTH_SHORT).show();
						break;
				}
			}
		});

	}

	private void OkGoUpData(){

		//正在加载弹窗
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(RealNameActivity.this, "","加载中，请稍候", false, false);
		} else if (progressDialog.isShowing()) {
			progressDialog.setTitle("");
			progressDialog.setMessage("加载中，请稍候");
		}
		progressDialog.show();
		/**********************/
		SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
		Log.d("--cid",""+ getDeviceID.getDeviceID());
		OkGo.<String>post(NetWorkURL.USER_REAL_NAME)
				.tag(this)
				.isMultipart(true)
				.params("userId",""+sp.getString("userid",""))//userId
				.params("realName",""+et_name.getText().toString())//真实姓名
				.params("identityCard",""+et_id.getText().toString())//身份证件号
				.params("bankName",""+et_bank_name.getText().toString())//银行名称
				.params("bankCard",""+et_bank_id.getText().toString())//银行卡号
				.params("alipay",""+et_alipay.getText().toString())//支付宝账号
				.params("grade",""+1)//认证等级  1初级 2中级 3高级

				.execute(new com.lzy.okgo.callback.StringCallback() {
					@Override
					public void onSuccess(Response<String> response) {
						//关闭加载弹窗
						if (progressDialog != null && progressDialog.isShowing()) {
							progressDialog.dismiss();
						}
						Log.d("--identity",""+response.body());
						HomeFragmentBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), HomeFragmentBean.class);
						if (jsonBean.getCode() == 100){

							//finish();
							Toast.makeText(RealNameActivity.this,"解析成功",Toast.LENGTH_SHORT).show();
						}else {
							Toast.makeText(RealNameActivity.this,"请求失败，错误:"+jsonBean.getMsg(),Toast.LENGTH_SHORT).show();
						}
					}

					@Override
					public void onError(Response<String> response) {
						Log.d("--identity",""+response.body());
						//关闭正在加载弹窗
						if (progressDialog != null && progressDialog.isShowing()) {
							progressDialog.dismiss();
						}
						Toast.makeText(RealNameActivity.this,"请求失败",Toast.LENGTH_SHORT).show();

					}
				});
	}


}
