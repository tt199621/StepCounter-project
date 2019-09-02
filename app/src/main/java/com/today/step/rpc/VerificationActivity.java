package com.today.step.rpc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.rpc.manager.RPCSDKManager;
import com.today.step.NetWorkURL;
import com.today.step.R;
import com.today.step.main.activity.IdentityActivity;


public class VerificationActivity extends Activity {
    private Button retry,title_back;
    private ImageView hintIv;
    private TextView verificationTip,title_text;
    private boolean isSuccess;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        Intent it  = getIntent();
        float score = Float.parseFloat(it.getStringExtra("score"));
        hintIv = findViewById(R.id.hintIv);
        if(score>=80){
            isSuccess = true;
            hintIv.setImageResource(R.drawable.verificationsuccess);
        }else{
            isSuccess = false;
            hintIv.setImageResource(R.drawable.verificationfail);
        }

        retry = findViewById(R.id.retry);
        Log.d("idcard","身份信息"+RPCListener.idcardFront);
        //上传信息
        updata();
        if(isSuccess){
            retry.setText("认证成功");
        }else{
            retry.setText("重新认证");
        }
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSuccess){
                    startActivity(new Intent(VerificationActivity.this, IdentityActivity.class));
                    finish();
                    RPCSDKManager.getInstance().finishActivity();
                }else{
                    startActivity(new Intent(VerificationActivity.this,RpcActivity.class));
                    finish();
                    RPCSDKManager.getInstance().finishActivity();
                }
            }
        });
        verificationTip = findViewById(R.id.verificationTip);

        if(isSuccess){
            verificationTip.setText("认证成功");
        }else{
            verificationTip.setText("认证失败");
        }

        title_back=findViewById(R.id.title_back);
        title_text=findViewById(R.id.title_text);
        title_text.setText("实名认证");
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RPCSDKManager.getInstance().finishActivity();
    }

    public void updata(){
        sp=getSharedPreferences("data", MODE_PRIVATE);
        OkGo.<String>post(NetWorkURL.UP_MESSAGE)
                .tag(this)
                .isMultipart(true)
                .params("xinxi",RPCListener.idcardFront)
                .params("userId",sp.getString("userid",""))
                .params("isPass",isSuccess)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("实名认证","userid "+sp.getString("userid","")+" xinxi "+RPCListener.idcardFront);
                    }
                });
    }

}
