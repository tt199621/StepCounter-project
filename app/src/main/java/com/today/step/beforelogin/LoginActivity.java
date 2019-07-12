package com.today.step.beforelogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.today.step.main.HomeActivity;
import com.today.step.utils.getDeviceID;
import com.today.step.R;

import java.util.Calendar;


/**
 * 登录activity
 * */
public class LoginActivity extends MyActivity implements View.OnClickListener{

    Button login,register;
    TextView remove,forget;
    EditText username,userpw;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//        PackageManager manager = getApplicationContext().getPackageManager();
//        PackageInfo info = null;
//        try {
//            info = manager.getPackageInfo(getApplicationContext().getPackageName(), 0);
//            Log.d("--version name1",info.versionCode+"");
//            Log.d("--version code1",""+info.versionName);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
////        info.versionCode
//
//        info.versionName.equals("1.0.1");
//        String version = info.versionName;
//        Log.d("--version name2",version);
//        Log.d("--version code2",""+info.versionCode);
//        //versionCode = info.versionCode;



        Log.d("--cid",""+ getDeviceID.getDeviceID());
        //2019-06-10 11:32:04.524 24693-24693/com.today.step D/--cid: 6798395738247
                                                                    //6696505676247
        login = (Button)findViewById(R.id.login_btn);
        login.setOnClickListener(this);
        register = (Button)findViewById(R.id.register_btn);
        register.setOnClickListener(this);
        remove = (TextView) findViewById(R.id.login_tv_remove);
        remove.setOnClickListener(this);
        forget = (TextView)findViewById(R.id.login_tv_forget);
        forget.setOnClickListener(this);

        username = (EditText)findViewById(R.id.login_edit_username);
        userpw = (EditText)findViewById(R.id.login_edit_userpw);
    }


    @Override
    protected void onResume() {
        super.onResume();
//        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningAppProcessInfo> mList = mActivityManager.getRunningAppProcesses();
//        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : mList) {
//            if (runningAppProcessInfo.pid != android.os.Process.myPid()) {
//                android.os.Process.killProcess(runningAppProcessInfo.pid);
//            }
//        }
        Log.d("---time",""+ Calendar.getInstance().getTimeInMillis());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
                //登录
            case R.id.login_btn:
                if (!username.getText().toString().equals("")){
                    if (!userpw.getText().toString().equals("")){
                        OkGoLogin();
                    }else {
                        Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }

                break;

                //注册
            case R.id.register_btn:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;

                //解除绑定
            case R.id.login_tv_remove:
                startActivity(new Intent(LoginActivity.this, UnbindActivity.class));
                //finish();
                break;

                //忘记密码
            case R.id.login_tv_forget:
                startActivity(new Intent(LoginActivity.this, ForgetPwActivity.class));
                //finish();
                break;
        }
    }


    /**
     * 登录请求
     * */
    private void OkGoLogin(){
        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(LoginActivity.this, "","加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();
        /**********************/
        Log.d("--cid",""+ getDeviceID.getDeviceID());
        OkGo.<String>post(NetWorkURL.USER_LOGIN)
                .tag(this)
                .isMultipart(true)
                .params("phone",""+username.getText().toString())//手机号
                .params("password",""+userpw.getText().toString())//密码
                .params("cid", ""+ getDeviceID.getDeviceID())//手机唯一标识

                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("--login onSuccess",""+response.body());
                            //JSON.parseObject(response.toString(), NoteDataJavaBean.class);

                        LoginJsonBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), LoginJsonBean.class);
                        if (jsonBean.getCode() == 100){

                            SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("userid", jsonBean.getExtend().getUser().getId());//登录成功

                            Log.e("返回值",jsonBean.getExtend().getUser().getId());

                            editor.commit();

                            Log.d("--login id",""+jsonBean.getExtend().getUser().getId());

                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginActivity.this,"登录失败，错误:"+jsonBean.getMsg(),Toast.LENGTH_SHORT).show();

                        }
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.d("--login onError",""+response.body());

                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(LoginActivity.this,"请求失败,错误："+ response.body(),Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
