package com.today.step.main.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.today.step.NetWorkURL;
import com.today.step.chat.DealInforActivity;
import com.today.step.main.activity.jsonbean.ItemBean;
import com.today.step.R;

public class ItemActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    Button button;
    Button ok_button;
    Button dealButton;
    TextView title;
    TextView orderNumber;
    TextView transactionStatus;
    TextView creatTime;
    TextView quantity;
    TextView transactionNumber;
    TextView unitPrice;
    TextView saller;
    TextView sallerTrueNamen;
    TextView alipayNumber;

    //item传过来的id和交易状态
    String id;
    String transactionStatu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        setContentView(R.layout.activity_item);

        initView();
        initData();
        sendRequest();
        if(transactionStatu.equals("已付款")){
            dealButton.setVisibility(View.INVISIBLE);
        }
        else if(transactionStatu.equals("已取消")){
            dealButton.setText("重新下单");
        }else if (transactionStatu.equals("已完成")){
            dealButton.setText("我要申诉");
            dealButton.setBackgroundColor(Color.BLUE);
        } else if (transactionStatu.equals("待付款")){
            dealButton.setText("取消订单");
        }else if (transactionStatu.equals("申诉中")){
            dealButton.setText("取消申诉");
        }
    }

    public void initView(){
        button=findViewById(R.id.title_back);
        //取消和确认
        dealButton=findViewById(R.id.deal_button);
        ok_button=findViewById(R.id.ok_button);
        dealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkGoCreateOrder();
            }
        });
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkGoCreateOrder2();
            }
        });
        title=findViewById(R.id.title_text);
        orderNumber=findViewById(R.id.orderNumber);//订单号
        transactionStatus=findViewById(R.id.transactionStatus);//交易状态
        creatTime=findViewById(R.id.creatTime);//时间
        quantity=findViewById(R.id.Quantity);//订单数量
        transactionNumber=findViewById(R.id.TransactionNumber);//交易金额
        unitPrice=findViewById(R.id.UnitPrice);//单价
        saller=findViewById(R.id.saller);//卖方
        sallerTrueNamen=findViewById(R.id.sallerTrueNamen);//卖方真名
        alipayNumber=findViewById(R.id.alipayNumber);//支付宝

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("订单详情");
    }
    private void OkGoCreateOrder() {
        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(ItemActivity.this, "", "加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();

        OkGo.<String>post(NetWorkURL.USER_CANCEL_ORDER)
                .tag(this)
                .isMultipart(true)
                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(ItemActivity.this, "取消订单成功", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);
                        Log.d("进度",progress+"");
                    }
                });

    }
    private void OkGoCreateOrder2() {
        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(ItemActivity.this, "", "加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();

        OkGo.<String>post(NetWorkURL.USER_OK_ORDER)
                .tag(this)
                .isMultipart(true)
                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(ItemActivity.this, "确认订单成功", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);
                        Log.d("进度",progress+"");
                    }
                });

    }

    public void initData(){
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        transactionStatu=intent.getStringExtra("transactionStatus");//交易状态
        transactionStatus.setText(transactionStatu);//设置交易状态
    }


    //根据id查询交易订单
    public void sendRequest() {
        OkGo.<String>post(NetWorkURL.USER_SELECT_ORDER)
                .tag(this)
                .isMultipart(true)
                .params("id", ""+id)//手机号
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        ItemBean itemBean=com.alibaba.fastjson.JSON.parseObject(response.body(), ItemBean.class);
                        //添加信息
                        orderNumber.setText((CharSequence) itemBean.getExtend().getTradeOrder().getOrderNumber());
                        creatTime.setText(itemBean.getExtend().getTradeOrder().getCreatTime());
                        quantity.setText(String.valueOf(itemBean.getExtend().getTradeOrder().getQuantity()));
                        transactionNumber.setText("¥ "+itemBean.getExtend().getTradeOrder().getTransactionNumber());
                        unitPrice.setText(itemBean.getExtend().getTradeOrder().getUnitPrice()+"个/元");
                        saller.setText(itemBean.getExtend().getTradeOrder().getSeller());
                        sallerTrueNamen.setText(itemBean.getExtend().getTradeOrder().getSellerRealName());
                        alipayNumber.setText(itemBean.getExtend().getTradeOrder().getAlipay());

                    }
                });
    }
}
