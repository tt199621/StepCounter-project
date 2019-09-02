package com.today.step.main.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.today.step.MyActivity;
import com.today.step.NetWorkURL;
import com.today.step.R;
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
	public static int code=5;
	private SharedPreferences sp;
	private String userID;
	public static RealNameActivity realNameActivity;
	public static int ok=3;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_real_name);

		realNameActivity=this;

		/******标题栏初始化******/
		TextView title = (TextView)findViewById(R.id.title_text);
		title.setText("实名认证");
		//标题右侧按钮
		final TextView title1 = (TextView)findViewById(R.id.title_btn);
		title1.setVisibility(View.GONE);
//		title1.setText("");
		//标题返回按钮
		Button button = (Button) findViewById(R.id.title_back);
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
		sp=this.getSharedPreferences("data", Context.MODE_PRIVATE);
		userID=sp.getString("userid","");

//		et_name = (EditText)findViewById(R.id.real_name_name);//真实姓名
//		et_id = (EditText)findViewById(R.id.real_name_id);//身份证
//		et_bank_name = (EditText)findViewById(R.id.real_name_card_name);//银行
//		et_bank_id = (EditText)findViewById(R.id.real_name_card_id);//银行卡号
//		et_alipay = (EditText)findViewById(R.id.real_name_alipay);//支付宝账号
		bt_ok = (Button)findViewById(R.id.real_name_okbtn);//支付并认证按钮


		bt_ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//上传数据
				//OkGoUpData();
				PayPopupWindow payPopupWindow = new PayPopupWindow(RealNameActivity.this,code,userID);
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
//						//赋值
						code=1;
						ok=1;
						break;
//					//中级认证
					case R.id.lv_2:
						code=3;
						ok=2;
						break;
//					//高级认证
					case R.id.lv_3:
						code=5;
						ok=3;
						break;
				}
			}
		});

	}



		//	认证请求
	private void OkGoUpData(){
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
				.params("grade",code+"")//认证等级  1初级 2中级 3高级
				.execute(new com.lzy.okgo.callback.StringCallback() {
					@Override
					public void onSuccess(Response<String> response) {

					}
				});
	}


}
