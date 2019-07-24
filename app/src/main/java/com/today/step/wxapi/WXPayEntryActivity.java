package com.today.step.wxapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.today.step.R;
import com.today.step.main.activity.IdentityActivity;
import com.today.step.main.activity.RealNameActivity;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    IWXAPI msgApi;
    public static String isOK="未认证";
    public static WXPayEntryActivity wxPayEntryActivity;

// 将该app注册到微信

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);
//        msgApi = WXAPIFactory.createWXAPI(this, null);
//        msgApi.registerApp("wx591efa3c85e5608b");
        msgApi = WXAPIFactory.createWXAPI(this,"wx591efa3c85e5608b");
        msgApi.handleIntent(getIntent(), this);

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode){
            case 0:
                Toast.makeText(this, "支付成功！", Toast.LENGTH_SHORT).show();
                isOK="已认证";
                finish();//关闭当前活动
                RealNameActivity.realNameActivity.finish();//关闭认证活动
                IdentityActivity.identityActivity.finish();

                break;
            case -1:
                Toast.makeText(this, "支付失败！", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case -2:
                Toast.makeText(this, "取消支付！", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }


}
