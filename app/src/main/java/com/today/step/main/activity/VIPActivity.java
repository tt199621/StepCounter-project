package com.today.step.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.today.step.R;
import com.today.step.main.fragment.HomeFragment;

public class VIPActivity extends Activity {

    Button back;
    TextView title,lv,contribut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        lv=findViewById(R.id.lv_text);
        title=findViewById(R.id.title_text);
        contribut=findViewById(R.id.contribut_text);
        back=findViewById(R.id.title_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("会员等级");
        lv.setText(HomeFragment.VIPlv);
        contribut.setText((HomeFragment.contribution));
    }
}
