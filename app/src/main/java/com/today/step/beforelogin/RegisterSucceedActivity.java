package com.today.step.beforelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.today.step.MyActivity;
import com.today.step.R;


/**
 * 注册成功activity
 * */
public class RegisterSucceedActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_succeed);

        //
        TextView tv = (TextView)findViewById(R.id.register_to_login);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterSucceedActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
}
