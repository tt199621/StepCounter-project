package com.today.step.rpc;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.rpc.manager.IRPCLister;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class RPCListener implements IRPCLister {
    private Activity activity;
    private String score;
    public static String idcardFront;

    public RPCListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onFail(String code, String failMsg) {
        if (code.equals("1007") || code.equals("1008")) {
            Intent it = new Intent(activity, VerificationActivity.class);
            it.putExtra("score", "0");
            activity.startActivity(it);
        }

    }

    @Override
    public void onVerifaction(String code, String score) {
        this.score = score;
        startVerification();

    }

    @Override
    public void onIdentityCardAuth(String code, String msg) {
        try {
            JSONObject object = new JSONObject(msg);
            JSONObject data = object.getJSONObject("data");
            String result = data.optString("result");
            if (result.equals("01")) {
                //返回结果 01-认证一致(收费) 02-认证不一致(收费) 03-认证不确定（不收费） 04-认证失败(不收费)
                startVerification();
            } else {
                Intent it = new Intent(activity, VerificationActivity.class);
                RpcActivity.rpcActivity.finish();
                it.putExtra("score", "0");
                activity.startActivity(it);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onVerifiedPic(String code, Map<String, String> picVerifiedMap) {

    }

    @Override
    public void onIdcardMsg(String code, String IdcardFront, String IdcardBack) {
        //ocr返回的身份证信息
        Log.e("UUU", "ocr识别结果 : IdcardFront=" + IdcardFront + "IdcardBack=" + IdcardBack);
        //身份信息
        idcardFront=IdcardFront;
    }

    @Override
    public void onIdcardModifiedMsg(String code, String IdcardFront, String IdcardBack) {
        //修改后的身份证信息
        Log.e("UUU", "修改后结果 : IdcardFront=" + IdcardFront + "IdcardBack=" + IdcardBack);
    }


    private boolean verification = false;

    private void startVerification() {
        if (verification) {
            verification = false;
            Intent it = new Intent(activity, VerificationActivity.class);
            RpcActivity.rpcActivity.finish();
            it.putExtra("score", score);
            activity.startActivity(it);
        } else {
            verification = true;
        }

    }

}
