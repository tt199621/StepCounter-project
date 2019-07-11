package com.today.step.main.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.today.step.MyActivity;
import com.today.step.R;
import com.today.step.beforelogin.LoginActivity;

import java.util.List;


/**
 * 个人中心  设置
 * */
public class SettingActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        /******标题栏初始化******/
        TextView title = (TextView)findViewById(R.id.title_text);
        title.setText("设置");
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

        Button exit = (Button)findViewById(R.id.setting_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killAllProcess();
//                Intent intent = new Intent(SettingActivity.this,LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
//                //注释掉下面一句即为 即销毁掉中间所有的activity，这里要求不保留FirstActivity上的数据。
//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
//                startActivity(intent);
//                finish();
            }
        });

    }


    /**
     * 杀死所有进程(包括前台服务、后台服务)
     * */
    private void killAllProcess(){
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> mList = mActivityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : mList) {
            if (runningAppProcessInfo.pid != android.os.Process.myPid()) {
                android.os.Process.killProcess(runningAppProcessInfo.pid);
            }
        }
        startActivity(new Intent(SettingActivity.this,LoginActivity.class));
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
