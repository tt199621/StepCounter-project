package com.today.step.main.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.today.step.MyActivity;
import com.today.step.NetWorkURL;
import com.today.step.R;
import com.today.step.beforelogin.ForgetPwActivity;
import com.today.step.beforelogin.LoginActivity;
import com.today.step.beforelogin.json.LoginJsonBean;
import com.today.step.main.HomeActivity;
import com.today.step.main.activity.jsonbean.HomeFragmentBean;
import com.today.step.main.activity.jsonbean.IdentityJsonBean;
import com.today.step.utils.getDeviceID;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 个人中心 身份信息
 * */
public class IdentityActivity extends MyActivity {

	private ProgressDialog progressDialog;

	private EditText tv_nickname,tv_sex,tv_wechat;
	private TextView tv_id;
	private TextView tv_name,tv_lv;
	private ImageView img_head;
	int grade;

	private RelativeLayout realname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_identity);


		/******标题栏初始化******/
		TextView title = (TextView)findViewById(R.id.title_text);
		title.setText("身份信息");
		//标题右侧按钮
		final TextView title1 = (TextView)findViewById(R.id.title_btn);
		title1.setText("提交");
		title1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/************网络请求保存身份信息内容*************/
				OkGoUpData();
			}
		});
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
		realname = (RelativeLayout)findViewById(R.id.identity_authentication);
		realname.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (grade == 0){
					startActivity(new Intent(IdentityActivity.this,RealNameActivity.class));
				}else {
					Toast.makeText(IdentityActivity.this,"已实名认证！",Toast.LENGTH_SHORT).show();

				}
			}
		});

		tv_nickname = (EditText)findViewById(R.id.identity_nickname);//
		tv_sex = (EditText)findViewById(R.id.identity_sex);//
		tv_wechat = (EditText)findViewById(R.id.identity_wechat);//

		tv_id = (TextView)findViewById(R.id.identity_Id);//id
		tv_name = (TextView)findViewById(R.id.identity_username);//用户名

		img_head = (ImageView)findViewById(R.id.identity_head_img);//头像

		tv_lv =  (TextView)findViewById(R.id.identity_lv);//
		OkGoInitUserInformation();
	}

	/**
	 * 个人信息初始化
	 * */
	private void OkGoInitUserInformation(){
		//正在加载弹窗
		SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(IdentityActivity.this, "","加载中，请稍候", false, false);
		} else if (progressDialog.isShowing()) {
			progressDialog.setTitle("");
			progressDialog.setMessage("加载中，请稍候");
		}
		progressDialog.show();
		/**********************/
//        Log.d("--cid",""+ getDeviceID.getDeviceID());
		OkGo.<String>post(NetWorkURL.USER_INIT_INFORMATION)
				.tag(this)
				.isMultipart(true)
				.params("userId",""+sp.getString("userid",""))//id
				.execute(new com.lzy.okgo.callback.StringCallback() {
					@Override
					public void onSuccess(Response<String> response) {
						Log.d("--forget onSuccess",""+response.body());
						//JSON.parseObject(response.toString(), NoteDataJavaBean.class);
						IdentityJsonBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), IdentityJsonBean.class);
						if (jsonBean.getCode() == 100){
							//头像
							Glide.with(IdentityActivity.this)
									.load(NetWorkURL.BASE_IMAGE +jsonBean.getExtend().getUser().getHeadPic())
//									.apply(RequestOptions.bitmapTransform(new CircleCrop()))
									.into(img_head);

							//用户名
							tv_name.setText(""+jsonBean.getExtend().getUser().getPhone());
							//个人推广ID
							tv_id.setText(""+jsonBean.getExtend().getUser().getPromotionCode());
							//微信号
							tv_wechat.setText(""+jsonBean.getExtend().getUser().getWeixinCard());
							//昵称
							tv_nickname.setText(""+jsonBean.getExtend().getUser().getNickname());
							//性别
							tv_sex.setText(""+jsonBean.getExtend().getUser().getSex());
							grade = jsonBean.getExtend().getUser().getGrade();
							if (jsonBean.getExtend().getUser().getGrade() == 0){
								tv_lv.setText("暂无等级");
							}else {
								tv_lv.setText("Lv "+jsonBean.getExtend().getUser().getGrade());
							}


							Toast.makeText(IdentityActivity.this,"查询成功",Toast.LENGTH_SHORT).show();
							//finish();
						}else {
							Toast.makeText(IdentityActivity.this,"查询失败，错误:"+jsonBean.getMsg(),Toast.LENGTH_SHORT).show();
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
						Toast.makeText(IdentityActivity.this,"请求失败,错误："+ response.body(),Toast.LENGTH_SHORT).show();
					}
				});
	}


	private void OkGoUpData(){

		//正在加载弹窗
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(IdentityActivity.this, "","加载中，请稍候", false, false);
		} else if (progressDialog.isShowing()) {
			progressDialog.setTitle("");
			progressDialog.setMessage("加载中，请稍候");
		}
		progressDialog.show();
		/**********************/
		SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
		Log.d("--cid",""+ getDeviceID.getDeviceID());
		OkGo.<String>post(NetWorkURL.USER_UPDATA)
				.tag(this)
				.isMultipart(true)

				.params("nickname",""+tv_nickname.getText().toString())//昵称
				.params("sex",""+tv_sex.getText().toString())//性别
				.params("file","")//头像new File("/storage/emulated/0/Pictures/Screenshots/Screenshot_20190604-100419.jpg")
				.params("userId",""+sp.getString("userid",""))//id
				.params("weixin", ""+tv_wechat.getText().toString())//微信号

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
							Toast.makeText(IdentityActivity.this,"解析成功",Toast.LENGTH_SHORT).show();
						}else {
							Toast.makeText(IdentityActivity.this,"请求失败，错误:"+jsonBean.getMsg(),Toast.LENGTH_SHORT).show();
						}
					}

					@Override
					public void onError(Response<String> response) {
						Log.d("--identity",""+response.body());
						//关闭正在加载弹窗
						if (progressDialog != null && progressDialog.isShowing()) {
							progressDialog.dismiss();
						}
						Toast.makeText(IdentityActivity.this,"请求失败",Toast.LENGTH_SHORT).show();

					}
				});
	}


}
