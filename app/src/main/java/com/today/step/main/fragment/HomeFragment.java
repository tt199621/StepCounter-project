package com.today.step.main.fragment;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.today.step.Diy_view.Tv_information;
import com.today.step.NetWorkURL;
import com.today.step.beforelogin.LoginActivity;
import com.today.step.loader.GlideImageLoader;
import com.today.step.main.activity.InformationActivity;
import com.today.step.main.activity.StatisticsStepActivity;
import com.today.step.main.activity.TaskActivity;
import com.today.step.main.activity.jsonbean.HomeCJsonBean;
import com.today.step.main.activity.jsonbean.HomeFragmentBean;
import com.today.step.utils.StepData;
import com.today.step.R;
import com.today.step.lib.ISportStepInterface;
import com.today.step.lib.TodayStepManager;
import com.today.step.lib.TodayStepService;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.today.step.TSApplication.getApplication;


/**
 * 首页fragment
 */
public class HomeFragment extends Fragment {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    tv_step.setText(mStepSum+"");
                    break;
            }

        }
    };

    /**************************/
    private static String TAG = "MainActivity";
    private SharedPreferences sp;
    private static final int REFRESH_STEP_WHAT = 0;

    //循环取当前时刻的步数中间的间隔时间
    private long TIME_INTERVAL_REFRESH = 3000;

    private Handler mDelayHandler = new Handler(new TodayStepCounterCall());

    private ISportStepInterface iSportStepInterface;


    private TextView tv_step;
    int service_step = 0;//服务器初始步数
    public static int mStepSum;//最终步数

    List<Integer> stepList = new ArrayList<>();//步数测试


    private ImageView img_infor;//首页消息图标
    private LinearLayout l_club, l_team, l_task, l_school;
    View view;

    /**
     * Activity和Service通过aidl进行通信
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //Activity和Service通过aidl进行通信
            iSportStepInterface = ISportStepInterface.Stub.asInterface(service);
            try {
                mStepSum = iSportStepInterface.getCurrentTimeSportStep();//初始值为0
//                locat_step = mStepSum;//记录初始步数
//                Log.d("---local step",""+locat_step);
                updateStepCount();//更新界面显示步数
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mDelayHandler.sendEmptyMessageDelayed(REFRESH_STEP_WHAT, TIME_INTERVAL_REFRESH);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private ImageView img_scan;
    private Intent intent;
    private TextView tv_today_rsg;
    private TextView tv_vip_lv;
    private Tv_information tv_information;
    private TextView tv_contribution;
    private TextView tv_live_v;
    private TextView tv_all_rsg;
    private ProgressDialog progressDialog;
    private ImageView step_img;
    private Banner banner;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        } else {//解决重复加载问题
            view = inflater.inflate(R.layout.home_fragment, container, false);
//            initImagePager();
            initView();


            //初始化计步模块
            TodayStepManager.startTodayStepService(getApplication());

            //开启计步Service，同时绑定Activity进行aidl通信
            intent = new Intent(getActivity(), TodayStepService.class);
            getActivity().startService(intent);
            Log.d("----star", "111");
            getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

            //计时器
            mhandmhandlele.post(timeRunable);
            sp = getActivity().getSharedPreferences("data", MODE_PRIVATE);
            /*********************/

        }
        return view;
    }

    //初始化应用View以及点击事件
    private void initView() {
        banner=view.findViewById(R.id.banner);
        //放图片地址的集合
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();

        list_path.add("https://www.wxxcx.club/images/commodity/qb/1.png");
        list_path.add("https://www.wxxcx.club/images/commodity/qb/2.png");
        list_path.add("https://www.wxxcx.club/images/commodity/qb/3.png");
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list_path);
        //banner设置方法全部调用完毕时最后调用
        banner.start();


        tv_step = view.findViewById(R.id.home_fg_step);//步数
        tv_today_rsg = view.findViewById(R.id.home_fg_today_rsg);//今日奖励人参果数
        tv_vip_lv = view.findViewById(R.id.home_fg_vip_lv);//会员等级
        tv_contribution = view.findViewById(R.id.home_fg_contribution);//会员等级
        tv_live_v = view.findViewById(R.id.home_fg_live_value);//活跃度
        tv_all_rsg = view.findViewById(R.id.home_fg_all_rsg);//总人参果数

        step_img = (ImageView) view.findViewById(R.id.home_fg_step_img);
        step_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), StatisticsStepActivity.class));
            }
        });

        //消息通知
        tv_information =  view.findViewById(R.id.home_fragment_information);

        //消息
        img_infor = view.findViewById(R.id.home_fg_infor);
        img_infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), InformationActivity.class));
            }
        });

        //俱乐部
        l_club = view.findViewById(R.id.home_fg_lin_club);
        l_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "正在开发中！！", Toast.LENGTH_SHORT).show();

                //startActivity(new Intent(getActivity(),TaskActivity.class));
            }
        });

        //组队
        l_team = view.findViewById(R.id.home_fg_lin_team);
        l_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "正在开发中！！", Toast.LENGTH_SHORT).show();

                //startActivity(new Intent(getActivity(),TaskActivity.class));
            }
        });

        //任务
        l_task = view.findViewById(R.id.home_fg_lin_task);
        l_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TaskActivity.class));
            }
        });

        //商学院
        l_school = view.findViewById(R.id.home_fg_lin_school);
        l_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "正在开发中！！", Toast.LENGTH_SHORT).show();

                //startActivity(new Intent(getActivity(),TaskActivity.class));
            }
        });//

        //扫一扫
        img_scan = view.findViewById(R.id.home_fg_scan);
        img_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "正在开发中！！", Toast.LENGTH_SHORT).show();
                //killAllProcess();
                //restartApplication();
            }
        });
    }

    /**
     * 杀死所有进程(包括前台服务、后台服务)
     */
    private void killAllProcess() {
        ActivityManager mActivityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> mList = mActivityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : mList) {
            if (runningAppProcessInfo.pid != android.os.Process.myPid()) {
                android.os.Process.killProcess(runningAppProcessInfo.pid);
            }
        }
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    /**
     * 重启程序
     */
    private void restartApplication() {
        Intent i = getActivity().getPackageManager()
                .getLaunchIntentForPackage(getActivity().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }




    class TodayStepCounterCall implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what) {
                case REFRESH_STEP_WHAT: {
                    //每隔500毫秒获取一次计步数据刷新UI
                    if (null != iSportStepInterface) {
                        int step = 0;
//                        steps = 0;
                        try {
                            //当前最终步数
                            step = iSportStepInterface.getCurrentTimeSportStep();
//                            stepList.add(step);
//                            if (stepList.size() > 1){
//                                steps = stepList.get(stepList.size()-1) - stepList.get(stepList.size()-2);
//                            }
//                            Log.d("---tsteps",""+steps);
//                            Log.d("---tmStepSum",""+mStepSum);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        if (mStepSum != step) {
                            mStepSum = step;
                            updateStepCount();
                            // TODO 上传步数

//							service_step += steps;//展示步数
                            /***步数上传请求*/
                            //OkGoUpDataStep(mStepSum);
                        }
                    }
                    mDelayHandler.sendEmptyMessageDelayed(REFRESH_STEP_WHAT, TIME_INTERVAL_REFRESH);

                    break;
                }
            }
            return false;
        }
    }

    /**
     * 更新界面显示步数
     **/
    private void updateStepCount() {
        Log.d("----updata step", "111");
        Log.e(TAG, "updateStepCount : " + mStepSum);
//        service_step += steps;//展示步数
//        tv_step.setText(mStepSum  + "");//界面显示步数  + mStepSum
        handler.sendEmptyMessage(1);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stepArrayButton: {
                //获取所有步数列表
                if (null != iSportStepInterface) {
                    try {
                        String stepArray = iSportStepInterface.getTodaySportStepArray();
//                        mStepArrayTextView.setText(stepArray);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            default:
                break;
        }
    }


    /*****************计时器*******************/
    private Runnable timeRunable = new Runnable() {
        @Override
        public void run() {

            currentSecond = currentSecond + 1000;
//            timeTextView.setText(getFormatHMS(currentSecond));
            if (!isPause) {
                //递归调用本runable对象，实现每隔一秒一次执行任务
                mhandmhandlele.postDelayed(this, 1000);
            }
        }
    };
    //计时器
    private Handler mhandmhandlele = new Handler();
    private boolean isPause = false;//是否暂停
    private long currentSecond = 0;//当前毫秒数
    /*****************计时器*******************/
    /**
     * 根据毫秒返回时分秒
     *
     * @param time
     * @return
     */
    public static String getFormatHMS(long time) {
        time = time / 1000;//总秒数
        int s = (int) (time % 60);//秒
        int m = (int) (time / 60);//分
        int h = (int) (time / 3600);//秒
        return String.format("%02d:%02d:%02d", h, m, s);
    }


    /**
     * 初始化首页数据 请求
     */
    private void OkGoInitData() {
        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(getActivity(), "", "加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();
        /**********************/
//        Log.d("--cid",""+ getDeviceID.getDeviceID());
//        SharedPreferences sp = getActivity().getSharedPreferences("data", MODE_PRIVATE);

        OkGo.<String>post(NetWorkURL.USER_INIT_HOME)
                .tag(this)
                .isMultipart(true)
                .params("userId", sp.getString("userid", ""))//手机号

                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("--Hg_init", "" + response.body());
                        //JSON.parseObject(response.toString(), NoteDataJavaBean.class);
                        HomeFragmentBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), HomeFragmentBean.class);
                        if (jsonBean.getCode() == 100) {
                            StepData.setStep(jsonBean.getExtend().getMember().getSteps());//步数
                            service_step = StepData.getStep();
                            Log.e("今日步数", service_step + "");
                            tv_today_rsg.setText("今日人参果奖励     " + jsonBean.getExtend().getMember().getTodayfruiter());
                            ;//今日奖励人参果数
                            tv_vip_lv.setText("Lv " + jsonBean.getExtend().getMember().getGradeMember());
                            ;//会员等级
                            tv_contribution.setText("" + jsonBean.getExtend().getMember().getContributionValue());
                            ;//贡献值
                            tv_live_v.setText("" + jsonBean.getExtend().getMember().getLivenessValue() + " + " + jsonBean.getExtend().getMember().getTodaylivenes());
                            ;//活跃度
                            tv_all_rsg.setText("" + jsonBean.getExtend().getMember().getFruiterValue());
                            ;//总人参果数
                            jsonBean.getExtend().getMember().getPromotionCode();//个人推广ID
//                            startActivity(new Intent(ForgetPwActivity.this,HomeActivity.class));
//                            finish();
                            SharedPreferences sp = getActivity().getSharedPreferences("data", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("hg_p_contribution", "" + jsonBean.getExtend().getMember().getContributionValue());//贡献值
                            editor.putString("hg_p_live", "" + "" + jsonBean.getExtend().getMember().getLivenessValue() + " + " + jsonBean.getExtend().getMember().getTodaylivenes());//活跃度
                            editor.putString("hg_p_code", "" + jsonBean.getExtend().getMember().getPromotionCode());//个人推广id
                            editor.putString("hg_p_lv", "Lv " + jsonBean.getExtend().getMember().getGradeMember());//lv
                            editor.commit();
                            Toast.makeText(getActivity(), "解析成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "解析失败，错误:" + jsonBean.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.d("--Hg_init", "" + response.body());
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        } else {
                            Log.e("测试", sp.getString("userid", ""));
                            Toast.makeText(getActivity(), "请求失败1,错误：" + response.body() + sp.getString("userid", ""), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    /**
     * 更新步数记录数据 请求
     */
    private void OkGoUpData() {
//        Log.d("--cid",""+ getDeviceID.getDeviceID());

        OkGo.<String>post(NetWorkURL.USER_INFORMATION)
                .tag(this)
                .isMultipart(true)
                .params("userId", sp.getString("userid", ""))//手机号

                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("--Hg_step", "" + response.body());
                        //JSON.parseObject(response.toString(), NoteDataJavaBean.class);
                        HomeCJsonBean jsonBean = com.alibaba.fastjson.JSON.parseObject(response.body(), HomeCJsonBean.class);
                        if (jsonBean.getCode() == 100) {
                            tv_information.setText("                                                                      " +
                                    "                  "+ jsonBean.getExtend().getResult().getContent());
                            tv_information.setSelected(true);
                            //Toast.makeText(getActivity(),"解析成功",Toast.LENGTH_SHORT).show();
                        } else {
                            //Toast.makeText(getActivity(),"解析失败，错误:"+jsonBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.d("--Hg_step", "" + response.body());
                        Log.e("测试", sp.getString("userid", ""));
                        Toast.makeText(getActivity(), "请求失败2,错误：" + response.body(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("==onDestroy", "***************");
    }

    @Override
    public void onResume() {
        super.onResume();
        OkGoInitData();
        OkGoUpData();
        Log.d("==onResume", "***************");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("==onPause", "***************");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("==onStop", "***************");
    }


}
