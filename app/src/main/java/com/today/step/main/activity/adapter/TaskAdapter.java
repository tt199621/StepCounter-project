package com.today.step.main.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.today.step.R;

import java.util.List;


/**
 * 首页右上角中 消息Recyclerview的适配器
 * */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

	Context context;
	List<TaskData> list;

	TaskCallBack callBack;

	public void setCallBack(TaskCallBack callBack) {
		this.callBack = callBack;
	}

	public TaskAdapter(Context context, List<TaskData> list) {
		this.context = context;
		this.list = list;
	}

	public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {
		public Button exchange;
		public TextView task_name,task_buy,task_vitiay,task_award,task_step;
		//初始化viewHolder，此处绑定后在onBindViewHolder中可以直接使用
		public ViewHolder(View itemView){
			super(itemView);

			task_name   = itemView.findViewById(R.id.item_task_name);
			task_buy    = itemView.findViewById(R.id.item_task_buy);
			task_vitiay = itemView.findViewById(R.id.item_task_vitality);
			task_award  = itemView.findViewById(R.id.item_task_arawd);
			task_step   = itemView.findViewById(R.id.item_task_need_step);
			exchange = itemView.findViewById(R.id.item_task_btn);

		}

	}
	@Override
	public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View views= LayoutInflater.from(parent.getContext()).inflate(
				R.layout.item_task_recycler,parent,false);
		TaskAdapter.ViewHolder holder = new TaskAdapter.ViewHolder(views);
		return holder;
	}

	@Override
	public void onBindViewHolder(final TaskAdapter.ViewHolder holder, final int position) {
		//建立起ViewHolder中试图与数据的关联
		TaskData taskData = list.get(position);

		holder.task_name.setText(""+taskData.getTask_name());
		holder.task_buy.setText(""+taskData.getBuy_task());
		holder.task_vitiay.setText(""+taskData.getOver_vitality());
		holder.task_award.setText(""+taskData.getOver_award());
		holder.task_step.setText(""+taskData.getTask_step());
		if (taskData.getTask_state() == 1){
			holder.exchange.setVisibility(View.VISIBLE);
		}else if (taskData.getTask_state() == 2){
			holder.exchange.setVisibility(View.GONE);
		}else if (taskData.getTask_state() == 3){
			holder.exchange.setVisibility(View.GONE);
		}
		//兑换按钮
		holder.exchange.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				callBack.setTaskPostion(position);
			}
		});
	}


	//设置显示内容数量(setAdapter的时候调用)
	@Override
	public int getItemCount() {
		return list.size();
	}

}