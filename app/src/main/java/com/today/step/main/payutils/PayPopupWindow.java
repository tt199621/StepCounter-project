package com.today.step.main.payutils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
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

import com.today.step.R;


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

	public PayPopupWindow(final Context context){
		super(context);
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		window = inflater.inflate(R.layout.pay_popup_window, null);

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
						Toast.makeText(context,"微信支付",Toast.LENGTH_SHORT).show();


					}else {
						//支付宝支付
						Toast.makeText(context,"支付宝支付",Toast.LENGTH_SHORT).show();

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
				}
				Toast.makeText(context,""+isChecked,Toast.LENGTH_SHORT).show();
			}
		});
		palipay = (CheckBox)window.findViewById(R.id.pay_way_alipay);
		palipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked == true){
					pwechat.setChecked(false);
				}
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



}
