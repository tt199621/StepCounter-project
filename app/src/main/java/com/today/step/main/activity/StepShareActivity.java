package com.today.step.main.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.today.step.MyActivity;
import com.today.step.NetWorkURL;
import com.today.step.main.activity.jsonbean.ShareJsonBean;
import com.today.step.utils.WxShareUtils;
import com.today.step.R;

public class StepShareActivity extends MyActivity implements View.OnClickListener {


	private SharedPreferences sp;
	private ProgressDialog progressDialog;
	private TextView step_num,step_km,step_hot,step_s;
	private LinearLayout shareToWx,shareToFriend,shareToQQ;
	private Bitmap bitmap,bitmap1;

	private static final String APP_ID = "wx591efa3c85e5608b";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_step_share);

		sp = getSharedPreferences("data", MODE_PRIVATE);

		/******标题栏初始化******/
		TextView title = (TextView)findViewById(R.id.title_text);
		title.setText("今日运动分享");
		//标题右侧按钮
		TextView title1 = (TextView)findViewById(R.id.title_btn);
		title1.setVisibility(View.GONE);
		//标题返回按钮
		Button button = (Button)findViewById(R.id.title_back);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				//Toast.makeText(IdentityInfoActivity.this,"onclick button",Toast.LENGTH_SHORT).show();
			}
		});

		step_num = (TextView)findViewById(R.id.share_step_num);
		step_km = (TextView)findViewById(R.id.share_step_km);
		step_hot = (TextView)findViewById(R.id.share_step_hot);
		step_s = (TextView)findViewById(R.id.share_step_amount);

		shareToWx=findViewById(R.id.share_to_wx);
		shareToFriend=findViewById(R.id.share_to_friendO);
		shareToQQ=findViewById(R.id.share_to_QQ);
		bitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.shareicon),120,120,true);

		shareToWx.setOnClickListener(this);
		shareToFriend.setOnClickListener(this);
		shareToQQ.setOnClickListener(this);
		OkGoInit();


	}

	/**
	 *
	 * */
	private void OkGoInit(){
		//正在加载弹窗
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(StepShareActivity.this, "","加载中，请稍候", false, false);
		} else if (progressDialog.isShowing()) {
			progressDialog.setTitle("");
			progressDialog.setMessage("加载中，请稍候");
		}
		progressDialog.show();
		/**********************/
//        Log.d("--cid",""+ getDeviceID.getDeviceID());
		OkGo.<String>post(NetWorkURL.USER_SHARE_STEP)
				.tag(this)
				.isMultipart(true)
				.params("userId",""+sp.getString("userid",""))//手机号
				.execute(new com.lzy.okgo.callback.StringCallback() {
					@Override
					public void onSuccess(Response<String> response) {
						Log.d("--forget onSuccess",""+response.body());
						//JSON.parseObject(response.toString(), NoteDataJavaBean.class);
						ShareJsonBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), ShareJsonBean.class);
						if (jsonBean.getCode() == 100){
							step_num.setText(""+jsonBean.getExtend().getShareBo().getStepNumber());
							step_km.setText(""+jsonBean.getExtend().getShareBo().getKilometre());
							step_hot.setText(""+jsonBean.getExtend().getShareBo().getCatabiotic()+"k");
							step_s.setText("今日人参果收益 "+jsonBean.getExtend().getShareBo().getFruiter());
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
						Toast.makeText(StepShareActivity.this,"请求失败,错误："+ response.body(),Toast.LENGTH_SHORT).show();
					}
				});
	}
	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.title_back:
				finish();
				break;
			case R.id.share_to_wx:
				//分享给好友
				WxShareUtils.shareWeb(StepShareActivity.this,APP_ID,"https://www.wxxcx.club/qubu/admin/fenxiang/index.html","好友邀请你与他一起奔跑","邀请码："+sp.getString("hg_p_code",""),bitmap,1);
				break;
			case  R.id.share_to_friendO:
				//分享到朋友圈
				WxShareUtils.shareWeb(StepShareActivity.this,APP_ID,"https://www.wxxcx.club/qubu/admin/fenxiang/index.html","好友邀请你与他一起奔跑","邀请码："+sp.getString("hg_p_code",""),bitmap,2);
				break;
			case R.id.share_to_QQ:
				break;
		}
	}
}
