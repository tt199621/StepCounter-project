package com.today.step.main.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.today.step.MyActivity;
import com.today.step.NetWorkURL;
import com.today.step.main.activity.adapter.AmountAdapter;
import com.today.step.main.activity.adapter.AmountData;
import com.today.step.main.activity.jsonbean.AmountJsonBean;
import com.today.step.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 个人中心 总人参果数
 * */
public class AmountActivity extends MyActivity {

    private TabLayout tabLayout;
    private ProgressDialog progressDialog;
    private TextView tv_amount,tv_avalue;
    private List<AmountData> datasList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount);


        /******标题栏初始化******/
        TextView title = (TextView)findViewById(R.id.title_text);
        title.setText("");
        //标题右侧按钮
        TextView title1 = (TextView)findViewById(R.id.title_btn);
        title1.setVisibility(View.GONE);
        //标题返回按钮
        Button button = (Button)findViewById(R.id.title_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //Toast.makeText(IdentityInfoActivity.this,"onclick button",Toast.LENGTH_SHORT).show();
            }
        });

        tv_amount = (TextView)findViewById(R.id.amount_number);
        tv_avalue = (TextView)findViewById(R.id.amount_value);

        tabLayout = (TabLayout)findViewById(R.id.amount_tablayout);
        //添加标签
        tabLayout.addTab(tabLayout.newTab().setText(" 全  部 "));
        tabLayout.addTab(tabLayout.newTab().setText("转出记录"));
        tabLayout.addTab(tabLayout.newTab().setText("转入记录"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                initRecyclerView(tab.getPosition());
//                tab.getPosition();//选中选项
                Toast.makeText(AmountActivity.this,"onclick"+tab.getPosition(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        OkGoAmount();


    }


    //初始化recyclerview
    private void initRecyclerView(int state){

        List<AmountData> list = new ArrayList<>();
        if (list.size()>0){
            for (int i = list.size()-1; i > -1 ; i--) {
                list.remove(i);
            }
        }
        if (state == 0){
            //获取全部记录
            for (int i = 0; i < datasList.size() ; i++) {
                list.add(datasList.get(i));
            }
        }else if (state == 1){
            //获取转出记录
            for (int i = 0; i < datasList.size() ; i++) {
                if (datasList.get(i).getInform_text().equals("转出")){
                    list.add(datasList.get(i));
                }
            }
        }else if (state == 2){
            //获取转入记录
            for (int i = 0; i < datasList.size() ; i++) {
                if (datasList.get(i).getInform_text().equals("转入")){
                    list.add(datasList.get(i));
                }
            }
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.amount_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(AmountActivity.this);//默认垂直布局
        recyclerView.setLayoutManager(layoutManager);
        AmountAdapter adapter = new AmountAdapter(AmountActivity.this,list);
        recyclerView.setAdapter(adapter);
    }



    /**
     * 获取人参果树与转入转出记录
     * */
    private void OkGoAmount(){

        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(AmountActivity.this, "","加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();
        /**********************/
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);

        OkGo.<String>post(NetWorkURL.USER_SELECT_INFORMATION)
                .tag(this)
                .isMultipart(true)
                .params("userId",""+sp.getString("userid",""))//手机号
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("--amount",""+response.body());

                        AmountJsonBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), AmountJsonBean.class);
                        if (jsonBean.getCode() == 100){
                            tv_amount.setText(""+jsonBean.getExtend().getResult().getTotalFruitTrees()+".0000");//果数
                            tv_avalue.setText("= ¥ "+jsonBean.getExtend().getResult().getTotalValue()); ;//价值
                            List<AmountJsonBean.ExtendBean.ResultBean.ListBean> listBeans = new ArrayList<>();
                            listBeans = jsonBean.getExtend().getResult().getList();
                            for (int i = 0; i < listBeans.size(); i++) {
                                if (listBeans.get(i).getState() == 1){
                                    //1为转入
                                    AmountData amount = new AmountData("转入",""+listBeans.get(i).getShijian(),"+"+listBeans.get(i).getQuantity());
                                    datasList.add(amount);
                                }else {
                                    AmountData amount = new AmountData("转出",""+listBeans.get(i).getShijian(),"-"+listBeans.get(i).getQuantity());
                                    datasList.add(amount);
                                }
                            }
                            initRecyclerView(0);
//                          Toast.makeText(RegisterActivity.this,"短信已发送",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(AmountActivity.this,"获取验证码失败，错误："+jsonBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.d("--amount",""+response.body());
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(AmountActivity.this,"请求错误！",Toast.LENGTH_SHORT).show();

                    }
                });
    }

}
