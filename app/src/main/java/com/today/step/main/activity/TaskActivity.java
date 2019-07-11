package com.today.step.main.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.today.step.R;
import com.today.step.beforelogin.ForgetPwActivity;
import com.today.step.beforelogin.json.LoginJsonBean;
import com.today.step.main.activity.adapter.InformationAdapter;
import com.today.step.main.activity.adapter.InformationData;
import com.today.step.main.activity.adapter.TaskAdapter;
import com.today.step.main.activity.adapter.TaskCallBack;
import com.today.step.main.activity.adapter.TaskData;
import com.today.step.main.activity.jsonbean.TaskJsonBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页中 任务页面
 * */
public class TaskActivity extends MyActivity {

    private TabLayout tabLayout;
    private ProgressDialog progressDialog;

    private List<TaskData> taskList = new ArrayList<>();

    private List<Integer> stateList = new ArrayList<>();
	private RecyclerView recyclerView;
	private TaskAdapter adapter;

	List<TaskData> testlist = new ArrayList<>();

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
			}
		}
	};


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        /******标题栏初始化******/
        TextView title = (TextView)findViewById(R.id.title_text);
        title.setText("我的果树");
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
		recyclerView = (RecyclerView)findViewById(R.id.task_recyclerview);

        tabLayout = (TabLayout)findViewById(R.id.task_tablayout);
        //添加标签
        tabLayout.addTab(tabLayout.newTab().setText("任务果树"));
        tabLayout.addTab(tabLayout.newTab().setText("我的果树"));
        tabLayout.addTab(tabLayout.newTab().setText("历史果树"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            	if (tab.getPosition() == 0){
            		TaskTree(0);
				}else if (tab.getPosition() == 1){
					TaskTree(1);
				} else if (tab.getPosition() == 2){
					TaskTree(2);
				}
            	//handler.sendEmptyMessage(tab.getPosition());
                Toast.makeText(TaskActivity.this,"onclick"+tab.getPosition(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        OkGoInitTask();

    }

    //初始化任务果数列表
    private void TaskTree(int postion){
		if (testlist.size()>0){
			for (int i = testlist.size()-1; i > -1 ; i--) {
				testlist.remove(i);
			}
		}
		if (postion == 0){
			for (int i = 0; i < taskList.size(); i++) {
				if (stateList.get(i) == 1){
					testlist.add(taskList.get(i));
				}
			}
		}else if (postion == 1){
			for (int i = 0; i < taskList.size(); i++) {
				if (stateList.get(i) == 2){
					testlist.add(taskList.get(i));
				}
			}
		}else if (postion == 2){
			for (int i = 0; i < taskList.size(); i++) {
				if (stateList.get(i) == 3){
					testlist.add(taskList.get(i));
				}
			}
		}
		Log.d("--1testlist size",""+testlist.size() );
		Log.d("--1statelist size",""+stateList.size() );

		LinearLayoutManager layoutManager = new LinearLayoutManager(TaskActivity.this);//默认垂直布局
		recyclerView.setLayoutManager(layoutManager);
		adapter = new TaskAdapter(TaskActivity.this,testlist);
		recyclerView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		adapter.setCallBack(new TaskCallBack() {
			@Override
			public void setTaskPostion(int postion) {
				//数据请求兑换任务
				OkGoDealTask(testlist.get(postion).getTask_id());
			}
		});
	}

	private void listCleanAll(){
		if (taskList.size()  > 0){
			for (int i = taskList.size()-1; i > -1 ; i--) {
				taskList.remove(i);
			}
		}
		if (stateList.size()  > 0){
			for (int i = stateList.size()-1; i > -1 ; i--) {
				stateList.remove(i);
			}
		}
	}

	/**
     * 初始化任务
     * */
    private void OkGoInitTask(){
        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(TaskActivity.this, "","加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();
        /**********************/

		listCleanAll();

        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
//        Log.d("--cid",""+ getDeviceID.getDeviceID());
        OkGo.<String>post(NetWorkURL.USER_TASK)
                .tag(this)
                .isMultipart(true)
                .params("userId",sp.getString("userid",""))//userid
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("--task onSuccess",""+response.body());
                        //JSON.parseObject(response.toString(), NoteDataJavaBean.class);
                        TaskJsonBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), TaskJsonBean.class);
                        if (jsonBean.getCode() == 100){
							List<TaskJsonBean.ExtendBean.ListBean> list = jsonBean.getExtend().getList();
                            if ((jsonBean.getExtend().getList().size()) > 0){
                                for (int i = 0; i < jsonBean.getExtend().getList().size(); i++) {
									Log.d("--taskList size",""+jsonBean.getExtend().getList().size());
                                  	Log.d("--taskList getTaskName",""+jsonBean.getExtend().getList().get(i).getTaskName());//任命名称
                                  	Log.d("--taskList getAward",""+jsonBean.getExtend().getList().get(i).getAward());//完成任务奖励的人生果数
									Log.d("--taskList getCode",""+jsonBean.getExtend().getList().get(i).getCode());//任务状态
									Log.d("--taskList getVitality",""+jsonBean.getExtend().getList().get(i).getVitality());//完成任务奖励的活跃度
									Log.d("--taskList getYaoqiu",""+jsonBean.getExtend().getList().get(i).getYaoqiu());//任务所需的步数(达到步数即可完成任务)
									Log.d("--taskList getYaoqiu",""+jsonBean.getExtend().getList().get(i).getGuoshu());//兑换需要的果数
									Log.d("--taskList getCTime",""+jsonBean.getExtend().getList().get(i).getId());//id
									TaskData data = new TaskData(""+jsonBean.getExtend().getList().get(i).getId(),""+jsonBean.getExtend().getList().get(i).getTaskName(),jsonBean.getExtend().getList().get(i).getGuoshu()+" 枚人参果可兑换",""+jsonBean.getExtend().getList().get(i).getVitality()+" 活跃度",""+jsonBean.getExtend().getList().get(i).getAward()+" 枚人参果奖励",""+jsonBean.getExtend().getList().get(i).getYaoqiu()+" 步/天",jsonBean.getExtend().getList().get(i).getCode());
									taskList.add(data);
									stateList.add(jsonBean.getExtend().getList().get(i).getCode());
								}
								Log.d("--taskList tasksize",""+taskList.size());//任务状态
								Log.d("--taskList tasksize",""+taskList.get(taskList.size()-1).getTask_step());//任务状态
//								Log.d("--taskList stateList",""+stateList.get(taskList.size()));//任务状态
								TaskTree(0);
                            }

                        }else {
                            Toast.makeText(TaskActivity.this,"请求失败，错误:"+jsonBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        Log.d("--task onError",""+response.body());
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(TaskActivity.this,"请求失败,错误："+ response.body(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

	/**
	 * 兑换任务
	 * */
	private void OkGoDealTask(String task_id){
		//正在加载弹窗
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(TaskActivity.this, "","加载中，请稍候", false, false);
		} else if (progressDialog.isShowing()) {
			progressDialog.setTitle("");
			progressDialog.setMessage("加载中，请稍候");
		}
		progressDialog.show();
		/**********************/

		SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
//        Log.d("--cid",""+ getDeviceID.getDeviceID());
		OkGo.<String>post(NetWorkURL.USER_BUY_TASK)
				.tag(this)
				.isMultipart(true)
				.params("taskId",task_id)//任务id
				.params("userId",sp.getString("userid",""))//userid
				.execute(new com.lzy.okgo.callback.StringCallback() {
					@Override
					public void onSuccess(Response<String> response) {
						Log.d("--task onSuccess",""+response.body());
						//JSON.parseObject(response.toString(), NoteDataJavaBean.class);
						TaskJsonBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), TaskJsonBean.class);
						if (jsonBean.getCode() == 100){
							Toast.makeText(TaskActivity.this,"兑换成功",Toast.LENGTH_SHORT).show();
							OkGoInitTask();
						}else {
							Toast.makeText(TaskActivity.this,"兑换失败，错误:"+"人参果树不足以兑换任务",Toast.LENGTH_SHORT).show();
						}
						//关闭正在加载弹窗
						if (progressDialog != null && progressDialog.isShowing()) {
							progressDialog.dismiss();
						}
					}
					@Override
					public void onError(Response<String> response) {
						Log.d("--task onError",""+response.body());
						//关闭正在加载弹窗
						if (progressDialog != null && progressDialog.isShowing()) {
							progressDialog.dismiss();
						}
						Toast.makeText(TaskActivity.this,"请求失败,错误："+ response.body(),Toast.LENGTH_SHORT).show();
					}
				});
	}

}
