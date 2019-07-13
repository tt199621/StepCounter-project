package com.today.step.chat;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.today.step.Diy_view.Count_edti;
import com.today.step.Diy_view.Et_alipay;
import com.today.step.Diy_view.Help_Set_Count_edti;
import com.today.step.Diy_view.Help_et_alipat;
import com.today.step.Diy_view.Set_Count_edti;
import com.today.step.Diy_view.Set_Et_alipay;
import com.today.step.MyActivity;
import com.today.step.NetWorkURL;
import com.today.step.chat.jsonbean.ChatJsonBean;
import com.today.step.R;

import org.greenrobot.eventbus.EventBus;


/**
 * 发起交易订单
 */
public class StartDealActivity extends MyActivity implements Set_Count_edti, Set_Et_alipay {


    private EditText  et_nickname, et_realname, et_alipay;
    private TextView et_moeny;
    private Et_alipay et_pic;
    private Count_edti et_amount;
    private ProgressDialog progressDialog;
    private SharedPreferences sp;
    private Button btn;

    private int unit_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_deal);

        sp = getSharedPreferences("data", MODE_PRIVATE);

        /******标题栏初始化******/
        TextView title = (TextView) findViewById(R.id.title_text);
        title.setText("发起交易");
        //标题右侧按钮
        final TextView title1 = (TextView) findViewById(R.id.title_btn);
        title1.setVisibility(View.GONE);
//		title1.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
//			}
//		});
        //标题返回按钮
        Button button = (Button) findViewById(R.id.title_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //Toast.makeText(IdentityInfoActivity.this,"onclick button",Toast.LENGTH_SHORT).show();
            }
        });

        initView();

    }

    private void initView() {
        Help_Set_Count_edti.setSet_count_edti(this);
        Help_et_alipat.setEt_alipay(this);
        et_pic =  findViewById(R.id.start_deal_pic);//单价
        et_amount =  findViewById(R.id.start_deal_amount);//交易数量
        et_amount.setEnabled(false);
        et_moeny = (TextView) findViewById(R.id.start_deal_moeny);//交易金额
        et_nickname = (EditText) findViewById(R.id.start_deal_nickname);//买方
        et_realname = (EditText) findViewById(R.id.start_deal_real_name);//买方真实姓名
        et_alipay = (EditText) findViewById(R.id.start_deal_buy_alipay);//付款支付宝
        btn = (Button) findViewById(R.id.start_deal_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OkGoCreateOrder();
            }
        });

    }

    /**
     * 发起交易订单
     */
    private void OkGoCreateOrder() {
        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(StartDealActivity.this, "", "加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();
        /**********************/

//        Log.d("--cid",""+ getDeviceID.getDeviceID());
        OkGo.<String>post(NetWorkURL.USER_CREATE_ORDER)
                .tag(this)
                .isMultipart(true)
                .params("buyerRealName", "" + et_realname.getText().toString())//买方真实姓名
                .params("sellerRealName", "" + et_nickname.getText().toString())//买方昵称
                .params("buyer", "" + sp.getString("userid", ""))//买家id
                .params("seller", "" + sp.getString("team_id", ""))//卖家id
                .params("transactionNumber", "" + et_moeny.getText().toString())//交易额 +et_pic.getText().toString()
                .params("unitPrice", "0.5")// "")//手续费
                .params("quantity", "" + et_amount.getText().toString())//数量
                .params("alipay", "" + et_alipay.getText().toString())//支付宝账号
                .params("orderNumber", "" + et_pic.getText().toString())//单价
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        Log.d("--forget onSuccess", "" + response.body());
                        //JSON.parseObject(response.toString(), NoteDataJavaBean.class);
                        ChatJsonBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), ChatJsonBean.class);
                        if (jsonBean.getCode() == 100) {

                            //SharedPreferences.Editor editor = sp.edit();
                            String orderid = jsonBean.getExtend().getId();
                            //editor.putString("orderid", jsonBean.getExtend().getId());//订单id
                            String buyerRealName = et_realname.getText().toString();
                            //editor.putString("buyerRealName", et_realname.getText().toString());//真实姓名
                            String nickName = et_nickname.getText().toString();
                            //editor.putString("sellerRealName", et_nickname.getText().toString());//昵称
                            String buyer = sp.getString("team_id", "");
                            //editor.putString("buyer", sp.getString("team_id",""));//买家id
                            String seller = sp.getString("userid", "");
                            //editor.putString("seller", sp.getString("userid",""));//卖家id
                            String transactionNumber = et_moeny.getText().toString();
                            //editor.putString("transactionNumber", "1100");//交易额
                            String unitPrice = "0.05";
                            //editor.putString("unitPrice", "0.05");//手续费
                            String quantity = et_amount.getText().toString();
                            //editor.putString("quantity", et_amount.getText().toString());//数量
                            String alipay = et_alipay.getText().toString();
                            //editor.putString("alipay", et_alipay.getText().toString());//支付宝

                            //发送消息
//							EventBus.getDefault().post(eventBusCarrier); //普通事件发布
                            EventBus.getDefault().post(new ChatMessageEvent("" + orderid, "" + quantity, "" + transactionNumber));
                            finish();

                            //editor.commit();
                            //Toast.makeText(StartDealActivity.this,"成功",Toast.LENGTH_SHORT).show();

                            //startActivity(new Intent(StartDealActivity.this,ChatActivity.class));

                        } else if (jsonBean.getCode() == 200) {
                            Toast.makeText(StartDealActivity.this, "发起失败，错误:请先实名认证(未实名不能交易)", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.d("--forget onError", "" + response.body());
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(StartDealActivity.this, "请求失败,错误：" + response.body(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void settext( int s) {
           if (s==1){
               et_moeny.setText((Integer.valueOf(et_pic.getText().toString())*Integer.valueOf(et_amount.getText().toString()))+"");
           }else {
               et_moeny.setText("");
           }
    }

    @Override
    public void jk_et_alipay(int s) {
        if (s==0){
            et_amount.setEnabled(false);
        }else {
            et_amount.setEnabled(true);
        }
    }
}
