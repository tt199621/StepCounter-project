package com.today.step.utils;

import android.app.Application;

import com.rpc.enumerate.LivenessTypeEnum;
import com.rpc.manager.RPCSDKManager;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    //    private String appId = "lv2dRPNm";
//    private String appKey = "3M3R5ks3";
//    private String appId = "kCBUWH7D";
//    private String appKey = "4ZGu6RQv";
//    private String appId = "cLkGS9WU";
//    private String appKey = "mqnOBHUm";
//    private String appId = "5Zwj3S3a";
//    private String appKey = "dBTGKAJw";
    private String appId = "1umkCqwr";
    private String appKey = "KdihwGKY";
    private String licenseId = "yuebu-sdk-face-android";

    @Override
    public void onCreate() {
        super.onCreate();
        //实人认证SDK初始化
        RPCSDKManager.getInstance().init(getApplicationContext(), appId, appKey, licenseId);
        //自定义活体检测步骤，默认：Eye,Mouth, HeadLeft,HeadRight,HeadLeftOrRight, HeadUp, HeadDown
        List<LivenessTypeEnum> list = new ArrayList<>();
        list.add(LivenessTypeEnum.Eye);
        RPCSDKManager.getInstance().setLivenessTypeEnum(list);
    }
}
