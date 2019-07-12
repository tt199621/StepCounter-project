package com.today.step.wxapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.today.step.R;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    IWXAPI msgApi;

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
                finish();
                Log.d("成功1","ooooooooooooooooooooo");
                break;
            case -1:
                Toast.makeText(this, "支付失败！", Toast.LENGTH_SHORT).show();
                finish();
                Log.d("失败2","1111111111111111111111");
                break;
            case -2:
                Toast.makeText(this, "取消支付！", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
