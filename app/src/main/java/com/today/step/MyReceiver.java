package com.today.step;

import android.content.Context;
import android.content.Intent;

import com.today.step.lib.BaseClickBroadcast;
import com.today.step.main.HomeActivity;

public class MyReceiver extends BaseClickBroadcast {

    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        TSApplication tsApplication = (TSApplication) context.getApplicationContext();
        if (!tsApplication.isForeground()) {
            /**********点击通知跳转**********/
            Intent mainIntent = new Intent(context, HomeActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mainIntent);
        } else {

        }
    }
}