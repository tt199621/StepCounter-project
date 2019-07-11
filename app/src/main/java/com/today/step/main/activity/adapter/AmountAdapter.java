package com.today.step.main.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.today.step.R;

import java.util.List;


/**
 * 首页右上角中 消息Recyclerview的适配器
 * */
public class AmountAdapter extends RecyclerView.Adapter<AmountAdapter.ViewHolder> {

	Context context;
	List<AmountData> list;
	public AmountAdapter(Context context, List<AmountData> list) {
		this.context = context;
		this.list = list;
	}

	public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {
		public TextView text,time,number;
		//初始化viewHolder，此处绑定后在onBindViewHolder中可以直接使用
		public ViewHolder(View itemView){
			super(itemView);
			text = itemView.findViewById(R.id.item_amount_inft);
			time = itemView.findViewById(R.id.item_amount_time);
			number = itemView.findViewById(R.id.item_amount_num);
		}

	}
	@Override
	public AmountAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View views= LayoutInflater.from(parent.getContext()).inflate(
				R.layout.item_amount_recycler,parent,false);
		AmountAdapter.ViewHolder holder = new AmountAdapter.ViewHolder(views);
		return holder;
	}

	@Override
	public void onBindViewHolder(final AmountAdapter.ViewHolder holder, final int position) {
		//建立起ViewHolder中试图与数据的关联
		AmountData informationData = list.get(position);

		holder.text.setText(informationData.getInform_text());//消息通知显示内容
		holder.time.setText(informationData.getInform_time());//消息通知显示时间
		holder.number.setText(informationData.getInfor_number());//金额出入值

	}


	//设置显示内容数量(setAdapter的时候调用)
	@Override
	public int getItemCount() {
		return list.size();
	}

}