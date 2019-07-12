package com.today.step;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * 隐藏标题栏，不能横屏 样式 activity
 * */
public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置只能竖屏,横屏无效/**/
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //隐藏系统标题栏
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }

    //监听物理返回按键
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//        Log.v("onBack","按下返回键");
//    }
}
