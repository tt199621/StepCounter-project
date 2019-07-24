package com.today.step.main.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.today.step.R;
import com.today.step.dealAdapter.AppealingFragment;
import com.today.step.dealAdapter.BuyFragment;
import com.today.step.dealAdapter.CanceledFragment;
import com.today.step.dealAdapter.FinishFragment;
import com.today.step.dealAdapter.HavePayFragment;
import com.today.step.dealAdapter.SaleFragment;
import com.today.step.dealAdapter.WaitPayFragment;


/**
 * 交易fragment
 * */
public class DealFragment extends Fragment implements View.OnClickListener {

    View view;
    private LinearLayout buy_btn;//买入
    private LinearLayout sale_btn;//卖出
    private TextView sale_text;
    private TextView buy_text;
    private TextView all;//全部
    private TextView waitPay;//待付款
    private TextView havePay;//已付款
    private TextView finish;//已完成
    private TextView canceled;//已取消
    private TextView appealing;//申诉中

    private Fragment buyFragment;
    private Fragment saleFragment;
    private Fragment waitPayFragment;
    private Fragment havePayFragment;
    private Fragment finishFragment;
    private Fragment canceledFragment;
    private Fragment appealingFragment;

    public static int DealCode=1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        } else {//解决重复加载问题
            view = inflater.inflate(R.layout.deal_fragment, container, false);
            initView();
        }
        return view;
    }

    public void initView() {
        buy_btn = view.findViewById(R.id.buy_btn);
        sale_btn = view.findViewById(R.id.sale_btn);
        buy_text = view.findViewById(R.id.buy_text);
        sale_text = view.findViewById(R.id.sale_text);
        all=view.findViewById(R.id.select_all);
        waitPay=view.findViewById(R.id.select_waitpay);
        havePay=view.findViewById(R.id.select_payed);
        finish=view.findViewById(R.id.select_finish);
        canceled=view.findViewById(R.id.select_cancel);
        appealing=view.findViewById(R.id.select_Appealing);

        buyFragment=new BuyFragment();
        saleFragment=new SaleFragment();
        waitPayFragment=new WaitPayFragment();
        havePayFragment=new HavePayFragment();
        finishFragment=new FinishFragment();
        canceledFragment=new CanceledFragment();
        appealingFragment=new AppealingFragment();

        buy_btn.setOnClickListener(this);
        sale_btn.setOnClickListener(this);
        all.setOnClickListener(this);
        waitPay.setOnClickListener(this);
        havePay.setOnClickListener(this);
        finish.setOnClickListener(this);
        canceled.setOnClickListener(this);
        appealing.setOnClickListener(this);
        //初始化页面
        replaceFragment(buyFragment);
        all.setTextColor(Color.YELLOW);
        buy_btn.setBackgroundColor(Color.WHITE);
        buy_text.setTextColor(Color.BLACK);
        sale_btn.setBackgroundColor(Color.BLACK);
        sale_text.setTextColor(Color.WHITE);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buy_btn://买入
                buy_btn.setBackgroundColor(Color.WHITE);
                buy_text.setTextColor(Color.BLACK);
                sale_btn.setBackgroundColor(Color.BLACK);
                sale_text.setTextColor(Color.WHITE);
                all.setTextColor(Color.YELLOW);
                waitPay.setTextColor(Color.WHITE);
                havePay.setTextColor(Color.WHITE);
                finish.setTextColor(Color.WHITE);
                canceled.setTextColor(Color.WHITE);
                appealing.setTextColor(Color.WHITE);
                replaceFragment(buyFragment);
                DealCode=1;
                break;
            case R.id.sale_btn://卖出
                sale_btn.setBackgroundColor(Color.WHITE);
                sale_text.setTextColor(Color.BLACK);
                buy_btn.setBackgroundColor(Color.BLACK);
                buy_text.setTextColor(Color.WHITE);
                all.setTextColor(Color.YELLOW);
                waitPay.setTextColor(Color.WHITE);
                havePay.setTextColor(Color.WHITE);
                finish.setTextColor(Color.WHITE);
                canceled.setTextColor(Color.WHITE);
                appealing.setTextColor(Color.WHITE);
                replaceFragment(saleFragment);
                DealCode=2;
                break;
            case R.id.select_all://全部
                all.setTextColor(Color.YELLOW);
                waitPay.setTextColor(Color.WHITE);
                havePay.setTextColor(Color.WHITE);
                finish.setTextColor(Color.WHITE);
                canceled.setTextColor(Color.WHITE);
                appealing.setTextColor(Color.WHITE);
                //点击全部默认展示已付款的全部信息

                if (DealCode==1){
                    buy_btn.setBackgroundColor(Color.WHITE);
                    buy_text.setTextColor(Color.BLACK);
                    sale_btn.setBackgroundColor(Color.BLACK);
                    sale_text.setTextColor(Color.WHITE);
                    replaceFragment(buyFragment);
                }else{
                    sale_btn.setBackgroundColor(Color.WHITE);
                    sale_text.setTextColor(Color.BLACK);
                    buy_btn.setBackgroundColor(Color.BLACK);
                    buy_text.setTextColor(Color.WHITE);
                    replaceFragment(saleFragment);
                }
                break;
            case R.id.select_waitpay://待付款
                replaceFragment(waitPayFragment);
                waitPay.setTextColor(Color.YELLOW);
                all.setTextColor(Color.WHITE);
                havePay.setTextColor(Color.WHITE);
                finish.setTextColor(Color.WHITE);
                canceled.setTextColor(Color.WHITE);
                appealing.setTextColor(Color.WHITE);
                break;
            case R.id.select_payed://已付款
                replaceFragment(havePayFragment);
                havePay.setTextColor(Color.YELLOW);
                all.setTextColor(Color.WHITE);
                waitPay.setTextColor(Color.WHITE);
                finish.setTextColor(Color.WHITE);
                canceled.setTextColor(Color.WHITE);
                appealing.setTextColor(Color.WHITE);
                break;
            case R.id.select_finish://已完成
                replaceFragment(finishFragment);
                finish.setTextColor(Color.YELLOW);
                all.setTextColor(Color.WHITE);
                waitPay.setTextColor(Color.WHITE);
                havePay.setTextColor(Color.WHITE);
                canceled.setTextColor(Color.WHITE);
                appealing.setTextColor(Color.WHITE);
                break;
            case R.id.select_cancel://已取消
                replaceFragment(canceledFragment);
                canceled.setTextColor(Color.YELLOW);
                all.setTextColor(Color.WHITE);
                waitPay.setTextColor(Color.WHITE);
                havePay.setTextColor(Color.WHITE);
                finish.setTextColor(Color.WHITE);
                appealing.setTextColor(Color.WHITE);
                break;
            case R.id.select_Appealing://申诉中
                replaceFragment(appealingFragment);
                appealing.setTextColor(Color.YELLOW);
                canceled.setTextColor(Color.WHITE);
                all.setTextColor(Color.WHITE);
                waitPay.setTextColor(Color.WHITE);
                havePay.setTextColor(Color.WHITE);
                finish.setTextColor(Color.WHITE);
                break;
        }
    }

    //替换Fragment
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();//Fragment中只能用孩子的FragmentManager
        transaction.replace(R.id.buy_sale_Fragment, fragment);
        transaction.commit();
    }


    //覆盖和显示Fragment
    public void switchContent(Fragment old, Fragment new1) {

            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            if (!new1.isAdded()) {    // 先判断是否被add过
                transaction.hide(old).add(R.id.buy_sale_Fragment, new1).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(old).show(new1).commit(); // 隐藏当前的fragment，显示下一个
            }
    }

}
