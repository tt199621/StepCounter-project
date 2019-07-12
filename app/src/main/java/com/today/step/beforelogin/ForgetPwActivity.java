package com.today.step.beforelogin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.today.step.MyActivity;
import com.today.step.NetWorkURL;
import com.today.step.beforelogin.json.LoginJsonBean;
import com.today.step.R;


/**
 * 忘记密码activity
 * */
public class ForgetPwActivity extends MyActivity {

    private EditText tel;
    private EditText code;
    private EditText password;
    private EditText password_t;
    private ProgressDialog progressDialog;
    private Button get_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pw);

        /******标题栏初始化******/
        TextView title = (TextView)findViewById(R.id.title_text);
        title.setText("忘记密码");
        //标题右侧按钮
        TextView title1 = (TextView)findViewById(R.id.title_btn);
        title1.setText("提交");
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tel.getText().toString().equals("")){
                    if (!code.getText().toString().equals("")){
                        if (!password.getText().toString().equals("")&&!password_t.getText().toString().equals("")){
                            if (password.getText().toString().equals(password_t.getText().toString())){
                                //忘记密码 请求
                                OkGoForgetPw();
                            }else {
                                Toast.makeText(ForgetPwActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(ForgetPwActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ForgetPwActivity.this,"验证码不能为空",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ForgetPwActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
//        title1.setVisibility(View.GONE);
        //标题返回按钮
        Button button = (Button)findViewById(R.id.title_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //Toast.makeText(IdentityInfoActivity.this,"onclick button",Toast.LENGTH_SHORT).show();
            }
        });

        get_code = (Button)findViewById(R.id.forget_pw_btn_tcode);
        get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tel.getText().toString().equals("")){
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
                    Toast.makeText(ForgetPwActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                }


            }
        });


        tel = (EditText)findViewById(R.id.register_forget_edit_tel);
        code = (EditText)findViewById(R.id.register_forget_edit_tel_code);
        password = (EditText)findViewById(R.id.register_forget_edit_pw);
        password_t = (EditText)findViewById(R.id.register_forget_edit_pwto);

    }


    /**
     * 获取手机短信验证码
     * */
    private void OkGogetTelCode(){

        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(ForgetPwActivity.this, "","加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();
        /**********************/
        OkGo.<String>post(NetWorkURL.USER_CODE)
                .tag(this)
                .isMultipart(true)
                .params("phone",""+tel.getText().toString().trim())//手机号1
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("--register",""+response.body());

                        LoginJsonBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), LoginJsonBean.class);
                        if (jsonBean.getCode() == 100){
//                          Toast.makeText(RegisterActivity.this,"短信已发送",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ForgetPwActivity.this,"获取验证码失败，错误："+jsonBean.getMsg(),Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(ForgetPwActivity.this,"请求错误！",Toast.LENGTH_SHORT).show();

                    }
                });
    }


    /**
     * 忘记密码请求
     * */
    private void OkGoForgetPw(){
        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(ForgetPwActivity.this, "","加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();
        /**********************/
//        Log.d("--cid",""+ getDeviceID.getDeviceID());
        OkGo.<String>post(NetWorkURL.USER_FORGET_PW)
                .tag(this)
                .isMultipart(true)
                .params("phone",""+tel.getText().toString())//手机号
                .params("password",""+password.getText().toString())//密码
                .params("verificationCode", ""+ code.getText().toString())//手机唯一标识
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("--forget onSuccess",""+response.body());
                        //JSON.parseObject(response.toString(), NoteDataJavaBean.class);
                        LoginJsonBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), LoginJsonBean.class);
                        if (jsonBean.getCode() == 100){
//                            startActivity(new Intent(ForgetPwActivity.this,HomeActivity.class));
                            Toast.makeText(ForgetPwActivity.this,"修改密码成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(ForgetPwActivity.this,"修改密码失败，错误:"+jsonBean.getMsg(),Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(ForgetPwActivity.this,"请求失败,错误："+ response.body(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
