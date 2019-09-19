package com.today.step.dealAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

public class BuyFragment extends Fragment {


    List<DealBean.ExtendBean.ListBean> dealList=new ArrayList<>();
    View view;
    ListView listViewBuy;
    DealAdapter dealAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.buy_fragment,container,false);
        sendRequest(NetWorkURL.SELECT_ALL_ORDER);
        listViewBuy=view.findViewById(R.id.buy_List_view);
        listViewBuy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DealBean.ExtendBean.ListBean list=dealList.get(i);
                Intent intent=new Intent(getActivity(), ItemActivity.class);
                intent.putExtra("id",list.getId());//id
                intent.putExtra("transactionStatus",list.getTransactionStatus());//交易状态
                startActivity(intent);
            }
        });
        //下拉刷新
        swipeRefreshLayout=view.findViewById(R.id.buy_swip);
        swipeRefreshLayout.setColorSchemeColors(Color.RED);
        swipeRefreshLayout.setProgressViewEndTarget (false,200);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                sendRequest(NetWorkURL.SELECT_ALL_ORDER);
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (dealAdapter==null){
            sendRequest(NetWorkURL.SELECT_ALL_ORDER);
            //Toast.makeText(getActivity(), "BUY==="+dealList.size(), Toast.LENGTH_SHORT).show();
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
                .params("userId",userID)//手机号
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {
                        DealBean dealBean=com.alibaba.fastjson.JSON.parseObject(response.body(), DealBean.class);
                        dealList.clear();
                        if (dealBean.getCode()==100){
                            for (int i = 0; i <dealBean.getExtend().getList().size() ; i++) {
                                DealBean.ExtendBean.ListBean listBean=new DealBean.ExtendBean.ListBean();
                                listBean.setId( dealBean.getExtend().getList().get(i).getId());
                                listBean.setCreatTime(dealBean.getExtend().getList().get(i).getCreatTime());
                                listBean.setOrderNumber(dealBean.getExtend().getList().get(i).getOrderNumber());
                                listBean.setQuantity( dealBean.getExtend().getList().get(i).getQuantity());
                                listBean.setTransactionNumber( dealBean.getExtend().getList().get(i).getTransactionNumber());
                                listBean.setTransactionStatus( dealBean.getExtend().getList().get(i).getTransactionStatus());
                                if (dealBean.getExtend().getList().get(i).getOutOrIn()==1){
                                    dealList.add(listBean);
                                }
                            }
                        }
                            dealAdapter=new DealAdapter(getActivity(), R.layout.item_deal,  dealList);
                            listViewBuy.setAdapter(dealAdapter);
                        //Toast.makeText(getActivity(), "BUY请求成功"+response.body().length()+"----"+dealList.size()+"----", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
