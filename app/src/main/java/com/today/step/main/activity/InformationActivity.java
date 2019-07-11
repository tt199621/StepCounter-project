package com.today.step.main.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.today.step.MyActivity;
import com.today.step.R;
import com.today.step.main.activity.adapter.InformationAdapter;
import com.today.step.main.activity.adapter.InformationData;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页中 消息页面
 * */
public class InformationActivity extends MyActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        /******标题栏初始化******/
        TextView title = (TextView)findViewById(R.id.title_text);
        title.setText("消息");
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

        tabLayout = (TabLayout)findViewById(R.id.information_tablayout);
        //添加标签
        tabLayout.addTab(tabLayout.newTab().setText("系统消息"));
        tabLayout.addTab(tabLayout.newTab().setText("俱乐部消息"));
        tabLayout.addTab(tabLayout.newTab().setText("组队消息"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

//                tab.getPosition();//选中选项
                Toast.makeText(InformationActivity.this,"onclick"+tab.getPosition(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        initRecyclerView();

    }
    //初始化recyclerview
    private void initRecyclerView(){
        List<InformationData> dataList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            InformationData a = new InformationData("Awfkn Store向您发了面壁",i+"5分钟前");
            dataList.add(a);
        }
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.information_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(InformationActivity.this);//默认垂直布局
        recyclerView.setLayoutManager(layoutManager);
        InformationAdapter adapter = new InformationAdapter(InformationActivity.this,dataList);
        recyclerView.setAdapter(adapter);
    }
}
