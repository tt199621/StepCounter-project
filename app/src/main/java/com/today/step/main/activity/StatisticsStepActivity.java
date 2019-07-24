package com.today.step.main.activity;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.today.step.Diy_view.Chart_view;
import com.today.step.Diy_view.Chart_view2;
import com.today.step.Diy_view.Chart_view3;
import com.today.step.Diy_view.Help_update_view;
import com.today.step.Diy_view.Update_view;
import com.today.step.NetWorkURL;
import com.today.step.main.activity.jsonbean.Msg;
import com.today.step.main.activity.jsonbean.StatisticsJsonBean;
import com.today.step.main.activity.jsonbean.The_Month;
import com.today.step.main.fragment.HomeFragment;
import com.today.step.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 运动统计
 */
public class StatisticsStepActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, Update_view {


    //声明所需变量
    private List<String> step_date = new ArrayList<>();//日期
    private List<String> step = new ArrayList<>();//每天步数
    private List<String> step_kilometres = new ArrayList<>();//里程
    private List<String> step_energy = new ArrayList<>();//能量

    private List<String> step_week = new ArrayList<>();//每周
    private List<String> week_data = new ArrayList<>();//每周日期
    private List<String> week_step_kilometres = new ArrayList<>();//里程
    private List<String> week_step_energy = new ArrayList<>();//能量

    private List<String> step_month = new ArrayList<>();//每月
    private List<String> month_data = new ArrayList<>();//每月日期
    private List<String> month_step_kilometres = new ArrayList<>();//里程
    private List<String> month_step_energy = new ArrayList<>();//能量

    private TextView tv_step, tv_km, tv_step1, tv_hot;
    private ProgressDialog progressDialog;
    private SharedPreferences sp;
    private LinearLayout fu_lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_step);
        sp = getSharedPreferences("data", MODE_PRIVATE);
        /******标题栏初始化******/
        TextView title = (TextView) findViewById(R.id.title_text);
        title.setText("运动统计");
        //标题右侧按钮
        TextView title1 = (TextView) findViewById(R.id.title_btn);
        title1.setText("分享");
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StatisticsStepActivity.this, StepShareActivity.class));
            }
        });
        //标题返回按钮
        Button button = (Button) findViewById(R.id.title_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tv_step = (TextView) findViewById(R.id.statistics_step);
        tv_step1 = (TextView) findViewById(R.id.statistics_step1);
        tv_km = (TextView) findViewById(R.id.statistics_km);
        tv_hot = (TextView) findViewById(R.id.statistics_hot);
        Help_update_view.setHelp_update_view(this);
        update_step();
        MyViewpager();
        OkGoInit();
        OkGoInit2();
        OkGoInit3();
    }

    private void update_step() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_step.setText(HomeFragment.mStepSum + "");//总步数
                tv_step1.setText(sp.getString("tstep", ""));//总步数
                tv_km.setText(sp.getString("km", ""));//总路程
                tv_hot.setText(sp.getString("calorie", "") + "k");//消耗能量
            }
        });

    }

    /**
     * viewpager切换控件
     */
    private View view1, view2, view3;
    private ViewPager vpager_four;//对应的viewpager
    private List<View> viewList;//view数组

    private HorizontalScrollView scr_one,scr_two,scr_three;

    private ImageView img_cursor;
    private TextView tv_one;
    private TextView tv_two;
    private TextView tv_three;

    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
    private int two = 0; //滑动条移动两页的距离

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void MyViewpager() {
        tv_one = findViewById(R.id.tv_one);
        tv_two = findViewById(R.id.tv_two);
        tv_three = findViewById(R.id.tv_three);
        img_cursor = findViewById(R.id.img_cursor);

        //下划线动画的相关设置：
        bmpWidth = BitmapFactory.decodeResource(getResources(), R.mipmap.line).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpWidth) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        img_cursor.setImageMatrix(matrix);// 设置动画初始位置
        //移动的距离
        one = offset * 2 + bmpWidth;// 移动一页的偏移量,比如1->2,或者2->3
        two = one * 2;// 移动两页的偏移量,比如1直接跳3

        vpager_four = findViewById(R.id.myviewpager);
        vpager_four.setOffscreenPageLimit(3);
        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.layout1, null, false);
        scr_one=view1.findViewById(R.id.scr_one);
        view2 = inflater.inflate(R.layout.layout2, null, false);
        scr_two=view2.findViewById(R.id.scr_two);
        view3 = inflater.inflate(R.layout.layout3, null, false);
        scr_three=view3.findViewById(R.id.scr_three);
        viewList = new ArrayList<View>();//将要分页显示的view装入其中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        scr_one.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                for (int a = 0; a< Chart_view.py.length; a++){
                    if (Chart_view.py[a]-700<=i&& Chart_view.py[a]-620>=i){
                        hd(a);
                    }
                }
            }
        });
        scr_two.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                for (int a = 0; a< Chart_view.py.length; a++){
                    if (Chart_view2.py2[a]-700<=i&& Chart_view.py[a]-620>=i){
                        hd2(a);
                    }
                }
            }
        });
        scr_three.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                for (int a = 0; a< Chart_view.py.length; a++){
                    if (Chart_view3.py3[a]-700<=i&& Chart_view.py[a]-620>=i){
                        hd3(a);
                    }
                }
            }
        });
        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }


        };
        vpager_four.setAdapter(adapter);
        vpager_four.setCurrentItem(0);
        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);
        tv_three.setOnClickListener(this);
        vpager_four.addOnPageChangeListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_one:
                vpager_four.setCurrentItem(0);
                break;
            case R.id.tv_two:
                vpager_four.setCurrentItem(1);
                break;
            case R.id.tv_three:
                vpager_four.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int index) {
        Animation animation = null;
        switch (index) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }

                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }

                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }

                break;
        }
        currIndex = index;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        img_cursor.startAnimation(animation);//开始动画
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * 全部数据请求
     */
    private void OkGoInit() {
        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(StatisticsStepActivity.this, "", "加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();
        //向服务器发送请求
        OkGo.<String>post(NetWorkURL.USER_SELECT_STEP)
                .tag(this)
                .isMultipart(true)//
                .params("userId", "" + sp.getString("userid", ""))//手机号
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        StatisticsJsonBean jsonBean = JSON.parseObject(response.body(), StatisticsJsonBean.class);
                        if (jsonBean.getCode() == 100) {

                            if (jsonBean.getExtend().getList().size() > 0) {
                                for (int i = 0; i < jsonBean.getExtend().getList().size(); i++) {
                                    step_date.add("" + jsonBean.getExtend().getList().get(i).getTime());
                                    step.add("" + jsonBean.getExtend().getList().get(i).getStep());
                                    step_kilometres.add("" + jsonBean.getExtend().getList().get(i).getKilometres());
                                    step_energy.add("" + jsonBean.getExtend().getList().get(i).getEnergy());
                                }
                            }
                            update_step1();
                        } else {

                            Toast.makeText(StatisticsStepActivity.this, "连接服务器失败:" + jsonBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.d("--statis onError", "" + response.body());
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(StatisticsStepActivity.this, "连接服务器失败" + response.body(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void OkGoInit2() {
        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(StatisticsStepActivity.this, "", "加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();
        //向服务器发送请求
        OkGo.<String>post(NetWorkURL.USER_WEEK_STEP)
                .tag(this)
                .isMultipart(true)//
                .params("userId", "" + sp.getString("userid", ""))//手机号
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Msg msg = JSON.parseObject(response.body(), Msg.class);
                        if (msg.getCode() == 100) {
                            if (msg.getExtend().getList().size() > 0) {
                                for (int i = 0; i < msg.getExtend().getList().size(); i++) {
                                    step_week.add("" + msg.getExtend().getList().get(i).getStepNumber());
                                    week_data.add("" + msg.getExtend().getList().get(i).getDate());
                                    week_step_kilometres.add("" + msg.getExtend().getList().get(i).getKilometre());
                                    week_step_energy.add("" + msg.getExtend().getList().get(i).getCatabiotic());
                                }
                            }
                            update_step2();
//                            Toast.makeText(StatisticsStepActivity.this, "请求成功" + msg.getExtend().getList().toString(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(StatisticsStepActivity.this, "连接服务器失败", Toast.LENGTH_LONG).show();
                        }
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.d("--statis onError", "" + response.body());
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        //Toast.makeText(StatisticsStepActivity.this, "请求失败5,错误：" + response.body(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void OkGoInit3() {
        //正在加载弹窗
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(StatisticsStepActivity.this, "", "加载中，请稍候", false, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("加载中，请稍候");
        }
        progressDialog.show();
        //向服务器发送请求
        OkGo.<String>post(NetWorkURL.USER_MONTH_STEP)
                .tag(this)
                .isMultipart(true)//
                .params("userId", "" + sp.getString("userid", ""))//手机号
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        The_Month the_month = JSON.parseObject(response.body(), The_Month.class);
                        if (the_month.getCode() == 100) {
                            if (the_month.getExtend().getList().size() > 0) {
                                for (int i = 0; i < the_month.getExtend().getList().size(); i++) {
                                    step_month.add("" + the_month.getExtend().getList().get(i).getStepNumber());
                                    month_data.add("" + the_month.getExtend().getList().get(i).getDate());
                                    month_step_kilometres.add("" + the_month.getExtend().getList().get(i).getKilometre());
                                    month_step_energy.add("" + the_month.getExtend().getList().get(i).getCatabiotic());
                                }
                            }
                            update_step3();
//                            Toast.makeText(StatisticsStepActivity.this, "请求成功" + msg.getExtend().getList().toString(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(StatisticsStepActivity.this, "连接服务器失败", Toast.LENGTH_LONG).show();
                        }
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.d("--statis onError", "" + response.body());
                        //关闭正在加载弹窗
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(StatisticsStepActivity.this, "连接服务器失败" + response.body(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void update_step1() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fu_lin = findViewById(R.id.fu_lin1);
                Chart_view.step_data1 = step_date;
                Chart_view.step = step;
                Chart_view chart_view = new Chart_view(StatisticsStepActivity.this);
                chart_view.setMinimumWidth(step.size() * 380+1060);
                chart_view.setMinimumHeight(ActionBar.LayoutParams.MATCH_PARENT);
                fu_lin.addView(chart_view);
            }
        });
    }

    private void update_step2() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fu_lin = findViewById(R.id.fu_lin2);
                Chart_view2.step_data1 = week_data;
                Chart_view2.step = step_week;
                Chart_view2 chart_view = new Chart_view2(StatisticsStepActivity.this);
                chart_view.setMinimumWidth(step_week.size() * 380+1060);
                chart_view.setMinimumHeight(ActionBar.LayoutParams.MATCH_PARENT);
                fu_lin.addView(chart_view);
            }
        });
    }

    private void update_step3() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fu_lin = findViewById(R.id.fu_lin3);
                Chart_view3.step_data1 = month_data;
                Chart_view3.step = step_month;
                Chart_view3 chart_view = new Chart_view3(StatisticsStepActivity.this);
                chart_view.setMinimumWidth(step_month.size() *380+1060);
                chart_view.setMinimumHeight(ActionBar.LayoutParams.MATCH_PARENT);
                fu_lin.addView(chart_view);
            }
        });
    }

    @Override
    public void setview(int code, final int i) {
        switch (code) {
            case 1:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_step1.setText(step.get(i));//总步数
                        tv_hot.setText(step_energy.get(i));//总路程
                        tv_km.setText(step_kilometres.get(i));//消耗能量
                    }
                });
                break;
            case 2:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_step1.setText(step_week.get(i));//总步数
                        tv_hot.setText(week_step_energy.get(i));//总路程
                        tv_km.setText(week_step_kilometres.get(i));//消耗能量
                    }
                });
                break;
            case 3:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_step1.setText(step_month.get(i));//总步数
                        tv_hot.setText(month_step_energy.get(i));//总路程
                        tv_km.setText(month_step_kilometres.get(i));//消耗能量
                    }
                });
                break;
        }

    }
    private void hd(final int a){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_step1.setText(step.get(a));//总步数
                        tv_hot.setText(step_energy.get(a));//总路程
                        tv_km.setText(step_kilometres.get(a));//消耗能量
                    }
                });
    }
    private void hd2(final int a){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_step1.setText(step_week.get(a));//总步数
                tv_hot.setText(week_step_energy.get(a));//总路程
                tv_km.setText(week_step_kilometres.get(a));//消耗能量
            }
        });
    }
    private void hd3(final int a){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_step1.setText(step_month.get(a));//总步数
                tv_hot.setText(month_step_energy.get(a));//总路程
                tv_km.setText(month_step_kilometres.get(a));//消耗能量
            }
        });
    }
}
