package com.today.step.main.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.today.step.MyActivity;
import com.today.step.NetWorkURL;
import com.today.step.chat.ChatActivity;
import com.today.step.main.activity.adapter.MyTeamAdapter;
import com.today.step.main.activity.adapter.TeamCallBack;
import com.today.step.main.activity.jsonbean.MyTeamJsonBean;
import com.today.step.R;

import java.util.List;


/**
 * 个人中心  我的团队activity
 * */
public class MyTeamActivity extends MyActivity {

    private TabLayout tabLayout;

    private String referrer_id,referrer_name_s;
    private TextView team_num,live_num,hero_num,hot_num;

    List<MyTeamJsonBean.ExtendBean.TotalTeamUserBean.ListBean> teamlist;



    private Handler handler = new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                //初始化页面数据
                case 0:
                    initRecyclerView(teamlist);
                    break;
                //
                case 2:

                    break;
                default:
                    break;
            }
        }
    };
	private TextView referrer_name;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team);

        /******标题栏初始化******/
        TextView title = (TextView)findViewById(R.id.title_text);
        title.setText("我的团队");
        //标题右侧按钮
        TextView title1 = (TextView)findViewById(R.id.title_btn);
        title1.setText("团队招募");
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyTeamActivity.this, ShareActivity.class));
            }
        });
        //标题返回按钮
        Button button = (Button)findViewById(R.id.title_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //Toast.makeText(IdentityInfoActivity.this,"onclick button",Toast.LENGTH_SHORT).show();
            }
        });

        tabLayout = (TabLayout)findViewById(R.id.my_team_tablayout);
        //添加标签
        tabLayout.addTab(tabLayout.newTab().setText("全部队员"));
        tabLayout.addTab(tabLayout.newTab().setText("转出记录"));
        tabLayout.addTab(tabLayout.newTab().setText("转入记录"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

//                tab.getPosition();//选中选项
                Toast.makeText(MyTeamActivity.this,"onclick"+tab.getPosition(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //initRecyclerView();
        initView();
    }

    //初始化recyclerview
    private void initRecyclerView(final List<MyTeamJsonBean.ExtendBean.TotalTeamUserBean.ListBean> teamlist){
//        List<InformationData> dataList = new ArrayList<>();
//        for (int i = 0; i < 12; i++) {
//            InformationData a = new InformationData("活跃度 654","团队人数"+i);
//            dataList.add(a);
//        }
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.my_team_recyclerview);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(MyTeamActivity.this);//默认垂直布局
        recyclerView.setLayoutManager(layoutManager);
        MyTeamAdapter adapter = new MyTeamAdapter(MyTeamActivity.this,teamlist);
        recyclerView.setAdapter(adapter);
        adapter.setCallBack(new TeamCallBack() {
            @Override
            public void setTeamData(int postion) {
                SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("team_name", teamlist.get(postion).getNickName());//昵称
                editor.putString("team_id", teamlist.get(postion).getId());//队员id
                Log.d("---team_id",""+teamlist.get(postion).getId());

                editor.commit();
                startActivity(new Intent(MyTeamActivity.this, ChatActivity.class));
            }
        });

}



    private void initView(){
        TextView ol_doubt = (TextView)findViewById(R.id.my_team_hot_ol_doubt);
        ol_doubt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TipDialog();
            }
        });

        // TODO
        ImageView tochat = (ImageView)findViewById(R.id.my_team_chatlogo);
        tochat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
				SharedPreferences.Editor editor = sp.edit();
				editor.putString("team_name", referrer_name_s);//昵称
				editor.putString("team_id", referrer_id);//队员id
				editor.commit();
                startActivity(new Intent(MyTeamActivity.this, ChatActivity.class));
            }
        });

        team_num = (TextView)findViewById(R.id.my_team_team_num);
        live_num = (TextView)findViewById(R.id.my_team_live_num);
        hero_num = (TextView)findViewById(R.id.my_team_hero_num);
        hot_num = (TextView)findViewById(R.id.my_team_hot_ol);//

		referrer_name = (TextView)findViewById(R.id.my_team_referrer_name);


    }

    @Override
    protected void onResume() {
        super.onResume();
        OkGoInitMyteam();
    }

    /**
     * 弹出联盟热度 解释对话框
     */
    private void TipDialog() {
//        lw_click.hideProgressDialog();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("信息提示");
        builder.setMessage("英雄\n第一大直推团队和第二大直推团队，为英雄\n\n联盟\n团队成员排除英雄，剩下的为联盟");
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0) {
//                    UpDateActivity.this.finish();
                }
                return false;
            }
        });

//        builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
        builder.setNegativeButton("我知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                finish();
            }
        });
        builder.show();
    }


    /**
     * 我的团队初始化
     * */
    private void OkGoInitMyteam(){
        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(MyTeamActivity.this, "","加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();
        /**********************/

        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
//        Log.d("--cid",""+ getDeviceID.getDeviceID());
        OkGo.<String>post(NetWorkURL.USER_MY_TEAM_INIT)
                .tag(this)
                .isMultipart(true)
                .params("userId",sp.getString("userid",""))//userid

                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("--myteam onSuccess",""+response.body());
                        //JSON.parseObject(response.toString(), NoteDataJavaBean.class);

                        MyTeamJsonBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), MyTeamJsonBean.class);
                        if (jsonBean.getCode() == 100){
                            //团队人数
                            team_num.setText(""+jsonBean.getExtend().getTotalTeamUser().getPeopleNum());
                            //团队活跃度
                            live_num.setText(""+jsonBean.getExtend().getTotalTeamUser().getLivenessNum());
                            //英雄活跃度
                            hero_num.setText(""+jsonBean.getExtend().getTotalTeamUser().getLivenessHero());
                            //联盟活跃度
                            hot_num.setText(""+jsonBean.getExtend().getTotalTeamUser().getLivenessAlliance());
                            //推荐人昵称
                            referrer_name.setText(""+jsonBean.getExtend().getTotalTeamUser().getReferrerUser().getNickName());
							referrer_name_s = jsonBean.getExtend().getTotalTeamUser().getReferrerUser().getNickName();

							referrer_id = jsonBean.getExtend().getTotalTeamUser().getReferrerUser().getId();
                            //初始化 适配器集合数据
                            teamlist = jsonBean.getExtend().getTotalTeamUser().getList();
                            handler.sendEmptyMessage(0);


//                            startActivity(new Intent(ForgetPwActivity.this,HomeActivity.class));
//                            finish();
                            //Toast.makeText(MyTeamActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MyTeamActivity.this,"登录失败，错误:"+jsonBean.getMsg(),Toast.LENGTH_SHORT).show();

                        }
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    }
                    @Override
                    public void onError(Response<String> response) {
                        Log.d("--myteam onError",""+response.body());
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(MyTeamActivity.this,"请求失败,错误："+ response.body(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
