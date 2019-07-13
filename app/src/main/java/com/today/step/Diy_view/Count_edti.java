package com.today.step.Diy_view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

public class Count_edti extends EditText {
    public Count_edti(Context context) {
        super(context);
        init();
    }

    public Count_edti(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                    settv();
            }
        });
    }
    private void settv(){
        if (length()<1){
            Help_Set_Count_edti.doedit(0);
        }else {
            Help_Set_Count_edti.doedit(1);
        }
    }
}

