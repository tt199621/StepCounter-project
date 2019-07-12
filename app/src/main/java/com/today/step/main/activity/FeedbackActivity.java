package com.today.step.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.today.step.MyActivity;
import com.today.step.R;

import java.util.Timer;
import java.util.TimerTask;


/**
 * 个人中心 问题反馈
 * */
public class FeedbackActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        /******标题栏初始化******/
        TextView title = (TextView)findViewById(R.id.title_text);
        title.setText("问题反馈");
        //标题右侧按钮
        final TextView title1 = (TextView)findViewById(R.id.title_btn);
        title1.setText("提交");
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title1.setClickable(false);
                Toast.makeText(FeedbackActivity.this,"反馈成功! 感谢您的反馈！！",Toast.LENGTH_SHORT).show();
                TimerTask task = new TimerTask(){
                    public void run(){
                        //延时1.0m后执行
                        finish();
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 1000);
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

    }
}
