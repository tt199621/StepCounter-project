package com.today.step.Diy_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

public class Tv_information extends TextView {
    public Tv_information(Context context) {
        super(context);
    }

    public Tv_information(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
