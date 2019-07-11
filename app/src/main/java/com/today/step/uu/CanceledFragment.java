package com.today.step.uu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.today.step.NetWorkURL;
import com.today.step.R;
import com.today.step.main.activity.ItemActivity;
import com.today.step.main.activity.adapter.DealAdapter;
import com.today.step.main.activity.jsonbean.DealBean;

import java.util.ArrayList;
import java.util.List;

public class CanceledFragment extends Fragment {
    List<DealBean.ExtendBean.ListBean> dealList=new ArrayList<>();
    View view;
    ListView listViewCanceled;
    DealAdapter dealAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.canceled_fragment,container,false);
        sendRequest(NetWorkURL.SELECT_ALL_ORDER);
        listViewCanceled=view.findViewById(R.id.canceled_listView);
        listViewCanceled.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DealBean.ExtendBean.ListBean list=dealList.get(i);
                Intent intent=new Intent(getActivity(), ItemActivity.class);
                intent.putExtra("id",list.getId());//id
                intent.putExtra("transactionStatus",list.getTransactionStatus());//交易状态
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (dealAdapter == null) {
            sendRequest(NetWorkURL.SELECT_ALL_ORDER);
        }
    }

    //发送订单查询请求
    public void sendRequest(String addres) {
        SharedPreferences sp = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String userID = sp.getString("userid", "");
        Log.d("userID", userID);
        OkGo.<String>post(addres)
                .tag(this)
                .isMultipart(true)
                .params("userId", "" +userID)//手机号
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {
                        DealBean dealBean=com.alibaba.fastjson.JSON.parseObject(response.body(),DealBean.class);
                        dealList.clear();
                        for (int i = 0; i <dealBean.getExtend().getList().size() ; i++) {
                            DealBean.ExtendBean.ListBean listBean=new DealBean.ExtendBean.ListBean();
                            listBean.setId( dealBean.getExtend().getList().get(i).getId());
                            listBean.setCreatTime(dealBean.getExtend().getList().get(i).getCreatTime());
                            listBean.setOrderNumber(dealBean.getExtend().getList().get(i).getOrderNumber());
                            listBean.setQuantity( dealBean.getExtend().getList().get(i).getQuantity());
                            listBean.setTransactionNumber( dealBean.getExtend().getList().get(i).getTransactionNumber());
                            listBean.setTransactionStatus( dealBean.getExtend().getList().get(i).getTransactionStatus());
                            if (dealBean.getExtend().getList().get(i).getTransactionStatus().equals("已取消")){
                                dealList.add(listBean);
                            }
                        }
                        dealAdapter=new DealAdapter(getActivity(), R.layout.item_deal,  dealList);
                        listViewCanceled.setAdapter(dealAdapter);
                        //Toast.makeText(getActivity(), "已取消请求成功"+response.body().length()+"----"+dealList.size()+"----", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
