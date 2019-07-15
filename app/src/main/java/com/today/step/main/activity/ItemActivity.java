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
import com.today.step.R;
import com.today.step.main.activity.jsonbean.ItemBean;
import com.today.step.main.fragment.DealFragment;
import com.today.step.main.fragment.HomeFragment;

public class ItemActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    Button button;
    Button btn1;
    Button btn2,btn3;
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
    String ToastTip;

    //请求地址
    String btnaddres1,btnaddres2,btnaddres3;
    TextView explain;

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
        if(transactionStatu.equals("已付款")&& DealFragment.DealCode==1){
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
        }
        if (transactionStatu.equals("已付款")&& DealFragment.DealCode==2){
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setText("确认收款");
            btn3.setBackgroundColor(Color.YELLOW);
            btnaddres3=NetWorkURL.SELLER_CONFIGURE_ORDER;
            ToastTip="确认收款成功";
        }

        if(transactionStatu.equals("已取消")){
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
        }
        if (transactionStatu.equals("已完成")){
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setText("我要申诉");
            btn3.setBackgroundColor(Color.RED);
            btnaddres3=NetWorkURL.Appeal;
            ToastTip="申诉成功";
        }

        if (transactionStatu.equals("待付款")&& DealFragment.DealCode==1){
            btn3.setVisibility(View.GONE);
            btnaddres2=NetWorkURL.USER_OK_ORDER;
            btnaddres1=NetWorkURL.USER_CANCEL_ORDER;
            ToastTip="操作成功";
        }
        if (transactionStatu.equals("待付款")&& DealFragment.DealCode==2){
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setText("取消订单");
            btn3.setBackgroundColor(Color.BLUE);
            btnaddres3=NetWorkURL.USER_CANCEL_ORDER;
            ToastTip="取消订单成功";
        }

        if (transactionStatu.equals("申诉中")){
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setText("取消申诉");
            btn3.setBackgroundColor(Color.GREEN);
            btnaddres3=NetWorkURL.CANCEL_APPEALING;
            ToastTip="取消申诉成功";
        }
        if (HomeFragment.VIPlv.equals(0)){
            explain.setText("禁止交易！");
        }
        if (HomeFragment.VIPlv.equals(1)){
            explain.setText("本次交易平台将收取%50手续费！");
        }
        if (HomeFragment.VIPlv.equals(2)){
            explain.setText("本次交易平台将收取%35手续费！");
        }
        if (HomeFragment.VIPlv.equals(3)){
            explain.setText("本次交易平台将收取%30手续费！");
        }
        if (HomeFragment.VIPlv.equals(4)){
            explain.setText("本次交易平台将收取%28手续费！");
        }
        if (HomeFragment.VIPlv.equals(5)){
            explain.setText("本次交易平台将收取%25手续费！");
        }
    }

    public void initView(){
        button=findViewById(R.id.title_back);
        //取消和确认
        btn1=findViewById(R.id.deal_button);
        btn2=findViewById(R.id.ok_button);
        btn3=findViewById(R.id.btn3);
        explain=findViewById(R.id.transactionExplain);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkGoCreateOrder(btnaddres1);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkGoCreateOrder(btnaddres2);
                finish();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkGoCreateOrder(btnaddres3);
                Toast.makeText(ItemActivity.this, ""+ToastTip, Toast.LENGTH_SHORT).show();
                finish();
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


    public void initData(){
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        transactionStatu=intent.getStringExtra("transactionStatus");//交易状态
        transactionStatus.setText(transactionStatu);//设置交易状态
    }

    private void OkGoCreateOrder(String addres1) {
        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(ItemActivity.this, "", "加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();

        OkGo.<String>post(addres1)
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
                    }
                    @Override
                    public void uploadProgress(Progress progress) {
                        super.uploadProgress(progress);
                        Log.d("进度",progress+"");
                    }
                });

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
                        orderNumber.setText((CharSequence) itemBean.getExtend().getTradeOrder().getSellerBank());//订单号
                        creatTime.setText(itemBean.getExtend().getTradeOrder().getCreatTime());
                        quantity.setText(String.valueOf(itemBean.getExtend().getTradeOrder().getQuantity()));
                        transactionNumber.setText("¥ "+itemBean.getExtend().getTradeOrder().getTransactionNumber());
                        unitPrice.setText(itemBean.getExtend().getTradeOrder().getOrderNumber()+"元/个");
                        saller.setText(itemBean.getExtend().getTradeOrder().getSeller());
                        sallerTrueNamen.setText(itemBean.getExtend().getTradeOrder().getSellerRealName());
                        alipayNumber.setText(itemBean.getExtend().getTradeOrder().getAlipay());
                    }
                });
    }
}
