package com.today.step.main.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.today.step.main.activity.jsonbean.DealBean;
import com.today.step.R;

import java.util.List;

public class DealAdapter extends ArrayAdapter<DealBean.ExtendBean.ListBean> {
    private int resourceId;
    public DealAdapter(@NonNull Context context, int resource, @NonNull List<DealBean.ExtendBean.ListBean> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DealBean.ExtendBean.ListBean listBean=getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.timeView=view.findViewById(R.id.deal_time);
            viewHolder.dealNoView=view.findViewById(R.id.deal_number);
            viewHolder.fruitNoView=view.findViewById(R.id.fruit_Number);
            viewHolder.dealStateView=view.findViewById(R.id.deal_state);
            viewHolder.moneyView=view.findViewById(R.id.deal_money);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.timeView.setText( listBean.getCreatTime()+"");//时间
        viewHolder.dealNoView.setText((Integer) listBean.getOrderNumber()+"");//订单号
        viewHolder.fruitNoView.setText((int) listBean.getQuantity()+"");//水果数量
        viewHolder.dealStateView.setText(listBean.getTransactionStatus()+"");//交易状态
        viewHolder.moneyView.setText(listBean.getTransactionNumber()+"");//交易金额

        return view;
    }

    class ViewHolder{
        TextView timeView;
        TextView dealNoView;
        TextView fruitNoView;
        TextView dealStateView;
        TextView moneyView;
    }
}
