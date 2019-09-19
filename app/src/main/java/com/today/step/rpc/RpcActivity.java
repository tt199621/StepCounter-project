package com.today.step.rpc;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.rpc.enumerate.LivenessTypeEnum;
import com.rpc.manager.RPCSDKManager;
import com.today.step.NetWorkURL;
import com.today.step.R;
import com.today.step.main.activity.IdentityActivity;
import com.today.step.main.activity.jsonbean.IDbean;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.List;

public class RpcActivity extends AppCompatActivity {

    private Button rpc_sdk_test,title_back;
    private TextView title_text;
    private SharedPreferences sp;
    private int day,count;
    public static RpcActivity rpcActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpc);
        rpc_sdk_test = findViewById(R.id.rpc_sdk_test);
        rpc_sdk_test.setVisibility(View.GONE);
        initViews();
        initListener();
    }


    private void initViews() {
        rpcActivity=this;
        sp=getSharedPreferences("data",MODE_PRIVATE);
        ifPay();
        title_back=findViewById(R.id.title_back);
        title_text=findViewById(R.id.title_text);
        title_text.setText("实名认证");
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(RpcActivity.this);
                builder.setMessage("退出认证");
                builder.setCancelable(true);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(RpcActivity.this, IdentityActivity.class));
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
        RPCSDKManager.getInstance().setModifiedIdcardMsg(true,true,true);
        List<LivenessTypeEnum> list = new ArrayList<>();
        list.add(LivenessTypeEnum.Eye);
        list.add(LivenessTypeEnum.Mouth);
        list.add(LivenessTypeEnum.HeadLeftOrRight);
        RPCSDKManager.getInstance().setLivenessTypeEnum(list);
    }

    private void initListener() {
        RPCSDKManager.getInstance().setRPCLister(new RPCListener(RpcActivity.this));
        RPCSDKManager.getInstance().getTiker(new RPCSDKManager.TikerCallBack() {
            @Override
            public void onSuccess(String s) {
                rpc_sdk_test.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFail(String code, String failMsg) {
                rpc_sdk_test.setVisibility(View.GONE);
                if(code.equals("1001")){
                    if (failMsg.contains("Authentication time error")) {
                        h.sendEmptyMessage(1);
                    }
                }
            }
        });
    }
    Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(RpcActivity.this,"SDK初始化失败，请检查当前系统日期和时间是否正确",Toast.LENGTH_LONG).show();
        }
    };
    public void doClick(View view) {
        if (count<9){
            if (day<3){
                Toast.makeText(RpcActivity.this, "今天还剩"+(2-day)+"次认证机会", Toast.LENGTH_SHORT).show();
                payTime();
                requestPermission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_PHONE_STATE);
            }else {
                Toast.makeText(RpcActivity.this, "今天的认证次数已用完", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(RpcActivity.this, "一共9次认证机会，你已用完", Toast.LENGTH_SHORT).show();
        }

    }

    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .onGranted(new Action() {
                    @TargetApi(Build.VERSION_CODES.M)
                    @Override
                    public void onAction(List<String> permissions) {
                        RPCSDKManager.getInstance().startAuthentication(RpcActivity.this);
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {

                    }
                })
                .start();
    }

    public void payTime(){
        OkGo.<String>post(NetWorkURL.ADD_RPC_TIME)
                .params("userId",sp.getString("userid",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                    }
                });
    }


    //获取支付次数
    public void ifPay(){
        OkGo.<String>post(NetWorkURL.PAY_SITUATION)
                .params("userId",sp.getString("userid",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        IDbean iDbean=com.alibaba.fastjson.JSON.parseObject(response.body(),IDbean.class);
                        day=iDbean.getExtend().getToday();
                        count=iDbean.getExtend().getAll();
                        Log.d("认证次数","day"+day);
                        Log.d("认证次数","count"+count);
                    }
                });
    }

}
