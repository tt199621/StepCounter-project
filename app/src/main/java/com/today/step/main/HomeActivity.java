package com.today.step.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.today.step.MyActivity;
import com.today.step.main.fragment.DealFragment;
import com.today.step.main.fragment.HomeFragment;
import com.today.step.main.fragment.PersonFragment;
import com.today.step.main.fragment.ShopFragment;
import com.today.step.main.fragment.UniteFragment;
import com.today.step.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录进入的首页activity
 * */
public class HomeActivity extends MyActivity implements View.OnClickListener{

    /**
     * 五个导航
     */
    LinearLayout lintonOne;
    LinearLayout lintonTwo;
    LinearLayout lintonThree;
    LinearLayout lintonFour;
    LinearLayout lintonFive;
    /**
     * 作为页面容器的ViewPager
     */
    ViewPager mViewPager;
    /**
     * 页面集合
     */
    List<Fragment> fragmentList;
    /**
     * 五个Fragment（页面）
     */
    HomeFragment oneFragment;
    DealFragment twoFragment;
    ShopFragment threeFragment;
    UniteFragment fourFragment;
    PersonFragment fiveFragment;
    //屏幕宽度
    int screenWidth;
    //当前选中的项
    int currenttab=-1;

    public static double M_money;


    TextView lifetext,wishtext,friendtext,mytext;
    ImageView homeimage,exchangeimage,recordimage,myimage,dealimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        InitView();

    }
    /**
     * 初始化组件，并关联
     * */
    private void InitView(){

        homeimage = (ImageView)findViewById(R.id.home_home);
        exchangeimage = (ImageView)findViewById(R.id.home_shop);
        recordimage = (ImageView)findViewById(R.id.home_unite);
        myimage = (ImageView)findViewById(R.id.home_my);
        dealimage = (ImageView)findViewById(R.id.home_deal);

        lintonOne=(LinearLayout) findViewById(R.id.lin_1);
        lintonTwo=(LinearLayout)findViewById(R.id.lin_2);
        lintonThree=(LinearLayout)findViewById(R.id.lin_3);
        lintonFour=(LinearLayout)findViewById(R.id.lin_4);
        lintonFive=(LinearLayout)findViewById(R.id.lin_5);

        //
        lintonOne.setOnClickListener(this);
        lintonTwo.setOnClickListener(this);
        lintonThree.setOnClickListener(this);
        lintonFour.setOnClickListener(this);
        lintonFive.setOnClickListener(this);

        mViewPager=(ViewPager) findViewById(R.id.main_viewpager);

        fragmentList=new ArrayList<Fragment>();
        oneFragment=new HomeFragment();
        twoFragment=new DealFragment();
        threeFragment=new ShopFragment();
        fourFragment=new UniteFragment();
        fiveFragment=new PersonFragment();

        fragmentList.add(oneFragment);
        fragmentList.add(twoFragment);
        fragmentList.add(threeFragment);
        fragmentList.add(fourFragment);
        fragmentList.add(fiveFragment);

        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lin_1:
                changeView(0);
                break;
            case R.id.lin_2:
                changeView(1);
                break;
            case R.id.lin_3:
                changeView(2);
                break;
            case R.id.lin_4:
                changeView(3);
                break;
            case R.id.lin_5:
                changeView(4);
                break;
        }
    }

    /**
     * 定义自己的ViewPager适配器。
     */
    class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter
    {

        public MyFrageStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        /**
         * 每次更新完成ViewPager的内容后，调用该接口，此处复写主要是为了让导航按钮上层的覆盖层能够动态的移动
         */
        @Override
        public void finishUpdate(ViewGroup container)
        {
            super.finishUpdate(container);//这句话要放在最前面，否则会报错
            //获取当前的视图是位于ViewGroup的第几个位置，用来更新对应的覆盖层所在的位置

            int currentItem=mViewPager.getCurrentItem();
            if (currentItem==currenttab)
            {
                return ;
            }
            //    imageMove(mViewPager.getCurrentItem());

            currenttab=mViewPager.getCurrentItem();
            if (currenttab==0){
                homeimage.setSelected(true);
                exchangeimage.setSelected(false);
                recordimage.setSelected(false);
                myimage.setSelected(false);
                dealimage.setSelected(false);
            }
            if (currenttab==1){
                dealimage.setSelected(true);
                homeimage.setSelected(false);
                exchangeimage.setSelected(false);
                recordimage.setSelected(false);
                myimage.setSelected(false);
            }
            if (currenttab==2){
                homeimage.setSelected(false);
                exchangeimage.setSelected(true);
                recordimage.setSelected(false);
                myimage.setSelected(false);
                dealimage.setSelected(false);
                //setM_money(new HomeFragment().getBalance());
            }
            if (currenttab==3){
                homeimage.setSelected(false);
                exchangeimage.setSelected(false);
                recordimage.setSelected(true);
                myimage.setSelected(false);
                dealimage.setSelected(false);
            }
            if (currenttab==4){
                homeimage.setSelected(false);
                exchangeimage.setSelected(false);
                recordimage.setSelected(false);
                myimage.setSelected(true);
                dealimage.setSelected(false);
            }

        }
    }
    //手动设置ViewPager要显示的视图
    private void changeView(int desTab) {
        mViewPager.setCurrentItem(desTab, true);
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("==onResume-A","***************");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("==onPause-A","***************");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("==onStop-A","***************");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("==onDestroy-A","***************");
    }
}
