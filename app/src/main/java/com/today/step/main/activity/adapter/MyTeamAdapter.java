package com.today.step.main.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.today.step.main.activity.jsonbean.MyTeamJsonBean;
import com.today.step.R;

import java.util.List;


/**
 * 个人中心中  我的团队Recyclerview的适配器
 * */
public class MyTeamAdapter extends RecyclerView.Adapter<MyTeamAdapter.ViewHolder> {

	Context context;

	TeamCallBack callBack;

	public void setCallBack(TeamCallBack callBack) {
		this.callBack = callBack;
	}

	List<MyTeamJsonBean.ExtendBean.TotalTeamUserBean.ListBean> list;
	public MyTeamAdapter(Context context, List<MyTeamJsonBean.ExtendBean.TotalTeamUserBean.ListBean> list) {
		this.context = context;
		this.list = list;
	}

	public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {
		public TextView live,team_num,team_live,username,isAutonym;
		public ImageView head,message_img;
		//初始化viewHolder，此处绑定后在onBindViewHolder中可以直接使用
		public ViewHolder(View itemView){
			super(itemView);
			live = itemView.findViewById(R.id.item_myteam_active);//个人活跃度
			team_num = itemView.findViewById(R.id.item_myteam_team_pop);//团队人数
			team_live = itemView.findViewById(R.id.item_myteam_team_active);//团队活跃度
			username = itemView.findViewById(R.id.item_myteam_name);//队员昵称
			isAutonym = itemView.findViewById(R.id.item_myteam_state);//是否实名

			message_img = itemView.findViewById(R.id.item_myteam_message);//跳转聊天图标
		}

	}
	@Override
	public MyTeamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View views= LayoutInflater.from(parent.getContext()).inflate(
				R.layout.item_myteam_recycler,parent,false);
		MyTeamAdapter.ViewHolder holder = new MyTeamAdapter.ViewHolder(views);
		return holder;
	}

	@Override
	public void onBindViewHolder(final MyTeamAdapter.ViewHolder holder, final int position) {
		//建立起ViewHolder中试图与数据的关联
		MyTeamJsonBean.ExtendBean.TotalTeamUserBean.ListBean informationData = list.get(position);
		Log.d("--onbind ",""+informationData.getLiveness());
		holder.live.setText("活跃度 "+informationData.getLiveness());//活跃度
		holder.team_num.setText("团队人数 "+informationData.getTeamSize());//团队人数
		holder.team_live.setText("团队活跃度 "+informationData.getTeamLiveness());//团队活跃度
		holder.username.setText(""+informationData.getNickName());//昵称
		if (informationData.getIsAutonym() == 2){
			holder.isAutonym.setText(" 已实名 ");//是否实名
		}else {
			holder.isAutonym.setText(" 未实名 ");//是否实名
		}

		holder.message_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				callBack.setTeamData(position);
			}
		});

	}


	//设置显示内容数量(setAdapter的时候调用)
	@Override
	public int getItemCount() {
		return list.size();
	}

}