package com.today.step.beforelogin;

import android.content.Intent;
import android.os.Bundle;

import com.today.step.MyActivity;
import com.today.step.R;
import com.today.step.main.HomeActivity;

import java.util.Timer;
import java.util.TimerTask;


/**
 * 欢迎页activity
 * */
public class WelcomeActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        TimerTask task = new TimerTask(){
            public void run(){
                //execute the task
                //延时1.5m后执行
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 1000);
    }
}
