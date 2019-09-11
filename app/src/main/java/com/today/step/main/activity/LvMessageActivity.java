package com.today.step.main.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.today.step.NetWorkURL;
import com.today.step.R;
import com.today.step.beforelogin.json.LoginJsonBean;

public class LvMessageActivity extends AppCompatActivity {

    private Button title_back;
    private TextView title_text;
    ImageView smll_vip,star_vip,city_vip,more_vip;
    int Partner,vip;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv_message);
        initView();
    }

    public void initView(){
        dialog=new ProgressDialog(LvMessageActivity.this);
        dialog.show();
        title_back=findViewById(R.id.title_back);
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_text=findViewById(R.id.title_text);
        title_text.setText("身份等级");
        smll_vip=findViewById(R.id.smll_vip);
        star_vip=findViewById(R.id.star_vip);
        city_vip=findViewById(R.id.city_vip);
        more_vip=findViewById(R.id.more_vip);
        //查询等级信息
        getLV();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    //查询等级信息
    public void getLV(){
        SharedPreferences sp=getSharedPreferences("data",MODE_PRIVATE);
        OkGo.<String>post(NetWorkURL.LV_MESSAGE)
                .params("userId",sp.getString("userid",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dialog.dismiss();
                        LoginJsonBean bean=com.alibaba.fastjson.JSON.parseObject(response.body(),LoginJsonBean.class);
                        if (bean.getCode()==100){
                            vip=bean.getExtend().getDengji();
                            Partner=bean.getExtend().getIsPass();
                            Log.d("lvmessage",vip+"  "+Partner);
                            if (Partner==0){
                                city_vip.setImageResource(R.drawable.city_vip);
                            }
                            if (Partner==1){
                                city_vip.setImageResource(R.drawable.city_vip2);
                            }
                            if (vip==0){
                                smll_vip.setImageResource(R.drawable.small_vip);
                            }
                            if (vip==1){
                                smll_vip.setImageResource(R.drawable.small_vip2);
                            }
                            if (vip==2){
                                star_vip.setImageResource(R.drawable.star_vip1);
                            }
                            if (vip==3){
                                star_vip.setImageResource(R.drawable.star_vip2);
                            }
                            if (vip==4){
                                star_vip.setImageResource(R.drawable.star_vip3);
                            }
                        }
                    }
                });
    }

}
