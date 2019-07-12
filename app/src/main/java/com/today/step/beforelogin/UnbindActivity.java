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
import com.today.step.utils.getDeviceID;
import com.today.step.R;


/**
 * 解除绑定activity
 * */
public class UnbindActivity extends MyActivity {

    private EditText tel;
    private EditText password;
    private EditText code;
    private ProgressDialog progressDialog;
    private Button get_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unbind);

        /******标题栏初始化******/
        TextView title = (TextView)findViewById(R.id.title_text);
        title.setText("解除设备绑定");
        //标题右侧按钮
        TextView title1 = (TextView)findViewById(R.id.title_btn);
        title1.setText("解绑");
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tel.getText().toString().equals("")){
                    if (!password.getText().toString().equals("")){
                        if (!code.getText().toString().equals("")){
                            //请求解除绑定
                            OkGoUnbind();
                        }else {
                            Toast.makeText(UnbindActivity.this,"验证码不能为空",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(UnbindActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(UnbindActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
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

        get_code = (Button)findViewById(R.id.unbind_btn_tcode);
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
                    Toast.makeText(UnbindActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tel = (EditText)findViewById(R.id.register_edit_tel);//
        password = (EditText)findViewById(R.id.register_edit_lpassword);
        code = (EditText)findViewById(R.id.register_edit_tel_code);

    }

    /**
     * 解除设备绑定请求
     * */
    private void OkGoUnbind(){
        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(UnbindActivity.this, "","加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();
        /**********************/
//        Log.d("--cid",""+ getDeviceID.getDeviceID());
        OkGo.<String>post(NetWorkURL.USER_UNBIND)
                .tag(this)
                .isMultipart(true)
                .params("phone",""+tel.getText().toString())//手机号
                .params("password",""+password.getText().toString())//密码
                .params("verificationCode",""+code.getText().toString())//短信呢验证码
                .params("cid", ""+ getDeviceID.getDeviceID())//手机唯一标识

                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("--unbind onSuccess",""+response.body());
                        //JSON.parseObject(response.toString(), NoteDataJavaBean.class);

                        LoginJsonBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), LoginJsonBean.class);
                        if (jsonBean.getCode() == 100){
//                            startActivity(new Intent(ForgetPwActivity.this,HomeActivity.class));
//                            finish();
                            Toast.makeText(UnbindActivity.this,"解除绑定成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(UnbindActivity.this,"解除失败，错误:"+jsonBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        Log.d("--unbind onError",""+response.body());
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(UnbindActivity.this,"请求失败,错误："+ response.body(),Toast.LENGTH_SHORT).show();
                    }
                });
    }


    /**
     * 获取手机短信验证码
     * */
    private void OkGogetTelCode(){

        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(UnbindActivity.this, "","加载中，请稍候", false, false);
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
                            Toast.makeText(UnbindActivity.this,"获取验证码失败，错误："+jsonBean.getMsg(),Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(UnbindActivity.this,"请求错误！",Toast.LENGTH_SHORT).show();

                    }
                });
    }


}
