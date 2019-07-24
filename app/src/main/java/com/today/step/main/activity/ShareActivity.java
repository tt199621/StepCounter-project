package com.today.step.main.activity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.today.step.MyActivity;
import com.today.step.utils.WxShareUtils;
import com.today.step.R;

/**
 * 团队招募
 * */
public class ShareActivity extends MyActivity implements View.OnClickListener {
private LinearLayout weixin_img,weixin_friend_img,qq_img;
	private static final String APP_ID = "wx591efa3c85e5608b";
	private SharedPreferences sp;
	private Bitmap bitmap,bitmap1;
	private TextView shareId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);
		sp = getSharedPreferences("data", MODE_PRIVATE);
		/******标题栏初始化******/
		TextView title = (TextView)findViewById(R.id.title_text);
		title.setText("团队招募");
		shareId=findViewById(R.id.share_user_id);
		shareId.setText(""+sp.getString("hg_p_code",""));
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
		bitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.shareicon),120,120,true);
		weixin_img=findViewById(R.id.weixin_img);
		weixin_friend_img=findViewById(R.id.weixin_friend_img);
		qq_img=findViewById(R.id.qq_img);
		weixin_img.setOnClickListener(this);
		weixin_friend_img.setOnClickListener(this);
		qq_img.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.weixin_img:
				//分享给好友
				WxShareUtils.shareWeb(ShareActivity.this,APP_ID,"https://www.yuebu.shop/qubu/admin/fenxiang/index.html","好友邀请你与他一起奔跑","邀请码："+sp.getString("hg_p_code",""),bitmap,1);
				break;
			case R.id.weixin_friend_img:
				//分享到朋友圈
				WxShareUtils.shareWeb(ShareActivity.this,APP_ID,"https://www.yuebu.shop/qubu/admin/fenxiang/index.html","好友邀请你与他一起奔跑","邀请码："+sp.getString("hg_p_code",""),bitmap,2);
				break;
			case R.id.qq_img:
				Toast.makeText(this, "暂未开发", Toast.LENGTH_SHORT).show();
				break;
		}
	}
}
