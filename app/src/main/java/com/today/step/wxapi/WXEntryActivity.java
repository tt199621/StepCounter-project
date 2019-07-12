package com.today.step.wxapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.today.step.R;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private static final String APP_ID = "wx591efa3c85e5608b";
    private IWXAPI wxapi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        //注册API
        wxapi = WXAPIFactory.createWXAPI(this, APP_ID);
        wxapi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.errCode==BaseResp.ErrCode.ERR_OK){
            finish();
        }

    }
}
