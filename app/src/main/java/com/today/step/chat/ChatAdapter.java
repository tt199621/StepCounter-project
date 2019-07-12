package com.today.step.chat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.today.step.R;

import java.util.List;


/**
 * 聊天适配器
 * */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

	Context context;
	List<ChatData> dataList;
	public ChatAdapter(Context context, List<ChatData> dataList) {
		this.context = context;
		this.dataList = dataList;
	}

	public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {
		public TextView obj_text,my_text;//对方发送的内容，自己发送的内容
		public ImageView obj_head,my_head;//对方的头像，自己的头像
		public RelativeLayout obj_layout,my_layout;//对方布局，自己布局
		//初始化viewHolder，此处绑定后在onBindViewHolder中可以直接使用(绑定对应控件)
		public ViewHolder(View itemView){
			super(itemView);
			obj_text = itemView.findViewById(R.id.item_chat_ob_text);
			obj_head = itemView.findViewById(R.id.item_chat_ob_img);
			obj_layout = itemView.findViewById(R.id.item_chat_r1);

			my_text = itemView.findViewById(R.id.item_chat_me_text);
			my_head = itemView.findViewById(R.id.item_chat_me_img);
			my_layout = itemView.findViewById(R.id.item_chat_r2);
		}

	}
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View views= LayoutInflater.from(parent.getContext()).inflate(
				R.layout.item_chat_recycler,parent,false);
		ViewHolder holder = new ViewHolder(views);
		return holder;
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, final int position) {
		//建立起ViewHolder中试图与数据的关联
		final ChatData message = dataList.get(position);


		if ((message.getMsg_type()).equals("1")){
			//对方发送的消息
			holder.my_layout.setVisibility(View.GONE);

			holder.obj_layout.setVisibility(View.VISIBLE);
			holder.obj_text.setText(message.getMsg_text());
			holder.obj_head.setImageResource(message.getMsg_head());
		}else if ((message.getMsg_type()).equals("2")){
			//自己发送的消息
			holder.obj_layout.setVisibility(View.GONE);

			holder.my_layout.setVisibility(View.VISIBLE);
			holder.my_text.setText(message.getMsg_text());
			holder.my_head.setImageResource(message.getMsg_head());

		}else if ((message.getMsg_type()).equals("3")){
			//接收对方发送的订单消息
			//对方发送的消息
			holder.my_layout.setVisibility(View.GONE);

			holder.obj_layout.setVisibility(View.VISIBLE);
			holder.obj_text.setText(message.getMsg_text());
			holder.obj_head.setImageResource(message.getMsg_head());

			holder.obj_text.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.d("---id",""+message.getOrder_id());

					Intent intent = new Intent(context, DealInforActivity.class);

					intent.putExtra("orderId",message.getOrder_id());
					context.startActivity(intent);
				}
			});

		}else if ((message.getMsg_type()).equals("4")){
			//自己发送的消息
			holder.obj_layout.setVisibility(View.GONE);

			holder.my_layout.setVisibility(View.VISIBLE);
			holder.my_text.setText(message.getMsg_text());
			holder.my_head.setImageResource(message.getMsg_head());
			holder.my_text.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, DealInforActivity.class);
					intent.putExtra("orderId",message.getOrder_id());
					context.startActivity(intent);
				}
			});
		}

	}


	//设置显示内容数量(setAdapter的时候调用)
	@Override
	public int getItemCount() {
		return dataList.size();
	}

}