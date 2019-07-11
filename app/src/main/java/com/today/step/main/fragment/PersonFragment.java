package com.today.step.main.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.today.step.R;
import com.today.step.main.activity.AmountActivity;
import com.today.step.main.activity.FeedbackActivity;
import com.today.step.main.activity.IdentityActivity;
import com.today.step.main.activity.MyTeamActivity;
import com.today.step.main.activity.SettingActivity;
import com.today.step.main.activity.UpDataActivity;

import static android.content.Context.MODE_PRIVATE;


/**
 * 个人中心fragment
 * */
public class PersonFragment extends Fragment {

    View view;
    private RelativeLayout person_team;
    private RelativeLayout person_amount;
    private RelativeLayout person_updata;
    private RelativeLayout person_feedback;
    private ImageView person_set;
    private RelativeLayout person_identity;

    private TextView id,liveness,contribution,lv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        } else {//解决重复加载问题
            view = inflater.inflate(R.layout.person_fragment, container, false);
            initView();
        }
        return view;
    }


    private void initView(){
        SharedPreferences sp = getActivity().getSharedPreferences("data", MODE_PRIVATE);

        id = (TextView)view.findViewById(R.id.person_fg_id);
        liveness = (TextView)view.findViewById(R.id.person_fg_hot);
        contribution = (TextView)view.findViewById(R.id.person_fg_ct);
        lv = (TextView)view.findViewById(R.id.person_fg_lv);
        id.setText("推广ID  "+sp.getString("hg_p_code",""));
        contribution.setText("0.0+"+sp.getString("hg_p_contribution",""));
        liveness.setText(""+sp.getString("hg_p_live",""));
        lv.setText("  "+sp.getString("hg_p_lv","")+"  ");

        //我的团队
        person_team = (RelativeLayout)view.findViewById(R.id.person_fg_r7);
        person_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MyTeamActivity.class));
            }
        });

        //人参果总数
        person_amount = (RelativeLayout)view.findViewById(R.id.person_fg_r1);
        person_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AmountActivity.class));
            }
        });//person_fg_r9

        //版本更新
        person_updata = (RelativeLayout)view.findViewById(R.id.person_fg_r9);
        person_updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),UpDataActivity.class));
            }
        });//

        //问题反馈
        person_feedback = (RelativeLayout)view.findViewById(R.id.person_fg_r8);
        person_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),FeedbackActivity.class));
            }
        });

        //设置
        person_set = (ImageView)view.findViewById(R.id.person_fg_setting);
        person_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),SettingActivity.class));
            }
        });

        //身份信息
        person_identity = (RelativeLayout)view.findViewById(R.id.person_fg_r4);
        person_identity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),IdentityActivity.class));
            }
        });

    }
}
