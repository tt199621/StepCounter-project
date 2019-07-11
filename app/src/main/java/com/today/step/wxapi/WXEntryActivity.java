package com.today.step.wxapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.today.step.R;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private static final String APP_ID = "wx8ffccfe4717f33e7";
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
            switch (baseResp.getType()){
                case  ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX:
                    Toast.makeText(WXEntryActivity.this, "分享给微信好友成功！", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                default:
                    Toast.makeText(WXEntryActivity.this, "分享到朋友圈成功！", Toast.LENGTH_SHORT).show();
            }
            }
            else
        {
            Toast.makeText(WXEntryActivity.this, "分享失败！", Toast.LENGTH_SHORT).show();
        }
    }
}
