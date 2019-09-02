package com.today.step;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.lzy.okgo.OkGo;
import com.rpc.enumerate.LivenessTypeEnum;
import com.rpc.manager.RPCSDKManager;

import java.util.ArrayList;
import java.util.List;


public class TSApplication extends Application {

    private static TSApplication sApplication;

    private int appCount = 0;

    private String appId = "1umkCqwr";
    private String appKey = "KdihwGKY";
    private String licenseId = "yuebu-sdk-face-android";
    @Override
    public void onCreate() {
        super.onCreate();
        // 主要是添加下面这句代码
        MultiDex.install(this);


        sApplication = this;

        OkGo.getInstance().init(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                appCount++;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                appCount--;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

        //实人认证SDK初始化
        RPCSDKManager.getInstance().init(getApplicationContext(), appId, appKey, licenseId);
        //自定义活体检测步骤，默认：Eye,Mouth, HeadLeft,HeadRight,HeadLeftOrRight, HeadUp, HeadDown
        List<LivenessTypeEnum> list = new ArrayList<>();
        list.add(LivenessTypeEnum.Eye);
        RPCSDKManager.getInstance().setLivenessTypeEnum(list);
    }

    /**
     * app是否在前台
     * @return true前台，false后台
     */
    public boolean isForeground(){
        return appCount > 0;
    }

    public static TSApplication getApplication() {
        return sApplication;
    }

}
