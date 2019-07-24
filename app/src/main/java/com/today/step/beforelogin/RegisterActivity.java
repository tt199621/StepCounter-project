package com.today.step.beforelogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.today.step.MyActivity;
import com.today.step.NetWorkURL;
import com.today.step.beforelogin.json.LoginJsonBean;
import com.today.step.beforelogin.json.MsgJavaBean;
import com.today.step.R;


/**
 * 注册activity
 * */
public class RegisterActivity extends MyActivity {

    Button register;
    EditText phone,password,referrer,code;
    private ProgressDialog progressDialog;
    private Button get_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        get_code = (Button)findViewById(R.id.register_btn_tcode);
        get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!phone.getText().toString().equals("")){
                    OkGogetTelCode();
                    //按钮倒计时60秒，60秒内不可点击。第二个参数是设置计时的速度，1000就是每秒一次。
                    new CountDownTimer(60000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            get_code.setEnabled(false);
                            get_code.setText(String.format("%ds",millisUntilFinished/1000));
                        }

                        @Override
                        public void onFinish() {
                            get_code.setEnabled(true);
                            get_code.setText("重新获取验证码");
                        }
                    }.start();
                }else {
                    Toast.makeText(RegisterActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                }


            }
        });
        //注册
        register = (Button)findViewById(R.id.register_btn_ok);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phone.getText().toString() != null || !phone.getText().toString().equals("")){
                    if (password.getText().toString() != null || !password.getText().toString().equals("")){
                        if (referrer.getText().toString() != null || !referrer.getText().toString().equals("")){
                            if (code.getText().toString() != null || !code.getText().toString().equals("")){

                                if (password.getText().toString().length()<6){
                                    Toast.makeText(RegisterActivity.this, "密码至少6位", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    OkGoRegister();
                            }else {
                                Toast.makeText(RegisterActivity.this,"手机验证码不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(RegisterActivity.this,"推荐人id不能为空",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                }

            }
        });

        phone = (EditText)findViewById(R.id.register_edit_tel);//手机号
        password = (EditText)findViewById(R.id.register_edit_pw);//密码
        referrer = (EditText)findViewById(R.id.register_edit_referrer);//推荐人
        code = (EditText)findViewById(R.id.register_edit_tel_code);//验证码



    }


    /**
     * 获取手机短信验证码
     * */
    private void OkGogetTelCode(){

        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(RegisterActivity.this, "","加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();
        /**********************/
        OkGo.<String>post(NetWorkURL.USER_CODE)
                .tag(this)
                .isMultipart(true)
                .params("phone",""+phone.getText().toString().trim())//手机号1
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("--register",""+response.body());

                        LoginJsonBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), LoginJsonBean.class);
                        if (jsonBean.getCode() == 100){
//                          Toast.makeText(RegisterActivity.this,"短信已发送",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegisterActivity.this,"获取验证码失败，错误："+jsonBean.getMsg(),Toast.LENGTH_SHORT).show();

                        }
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.d("--register",""+response.body());
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(RegisterActivity.this,"请求错误！",Toast.LENGTH_SHORT).show();

                    }
                });
    }

    /**
     * 注册请求
     * */
    private void OkGoRegister(){

        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(RegisterActivity.this, "","加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();
        /**********************/
        OkGo.<String>post(NetWorkURL.USER_REGISTER)
                .tag(this)
                .isMultipart(true)
                .params("phone",""+phone.getText().toString().trim())//手机号1
                .params("password",""+password.getText().toString().trim())//密码1
//                .params("cid", "")//手机唯一标识
                .params("referrer",""+referrer.getText().toString().trim())//推荐人id123456
                .params("verificationCode",""+code.getText().toString().trim())//短信验证码1234

//                .params("phone",""+phone.getText().toString())//手机号1
//                .params("password",""+password.getText().toString())//密码1
////                .params("cid", "")//手机唯一标识
//                .params("referrer",""+referrer.getText().toString())//推荐人id123456
//                .params("verificationCode",""+code.getText().toString())//短信验证码1234
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("--register",""+response.body());

                        MsgJavaBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), MsgJavaBean.class);
                        if (jsonBean.getCode() == 100){
                            startActivity(new Intent(RegisterActivity.this, RegisterSucceedActivity.class));
                            finish();
                            Toast.makeText(RegisterActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegisterActivity.this,"注册失败，错误："+jsonBean.getExtend().getMsg(),Toast.LENGTH_SHORT).show();

                        }
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                       }

                    @Override
                    public void onError(Response<String> response) {
                        Log.d("--register",""+response.body());
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(RegisterActivity.this,"请求错误！",Toast.LENGTH_SHORT).show();

                    }
                });
    }

}
