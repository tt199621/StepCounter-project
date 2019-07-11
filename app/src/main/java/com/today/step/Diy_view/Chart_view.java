package com.today.step.Diy_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.today.step.R;

import java.util.ArrayList;
import java.util.List;

public class Chart_view extends View {
    private Paint mpaint, mpaint2, mpaint3, mpaint4;
    private int LEFT = 600;
    private int code = 0;
    private int size = 80;//柱形条大小
    private int space = 300;//柱形图间距
    private static final int extremity = 100000;//极限步数
    public static List<String> step_data1;
    public static List<String> step;

    public  int[] item = new int[step.size()];
    public static int []py;
    private int h, w;

    public Chart_view(Context context) {
        super(context);
        init();
        init2();
        init3();
        init4();
    }

    private void init() {
        mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setColor(getResources().getColor(R.color.blue));
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setTextSize(36);
        mpaint.setStrokeWidth(1);

    }

    private void init2() {
        mpaint2 = new Paint();
        mpaint2.setAntiAlias(true);
        mpaint2.setColor(getResources().getColor(R.color.gray));
        mpaint2.setStyle(Paint.Style.FILL);
        mpaint2.setTextSize(100);
        mpaint2.setStrokeWidth(10);

    }

    private void init3() {
        mpaint3 = new Paint();
        mpaint3.setAntiAlias(true);
        mpaint3.setColor(getResources().getColor(R.color.gray));
        mpaint3.setStyle(Paint.Style.FILL);
        mpaint3.setTextSize(10);
        mpaint3.setStrokeWidth(1);


    }

    private void init4() {
        mpaint4 = new Paint();
        mpaint4.setAntiAlias(true);
        mpaint4.setColor(getResources().getColor(R.color.gray));
        mpaint4.setStyle(Paint.Style.FILL);
        mpaint4.setTextSize(35);
        mpaint4.setStrokeWidth(4);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        code += 1;;
        canvas.drawLine(0, getHeight(), getWidth(), getHeight(), mpaint2);
        if (code == 1) {
            for (int i = 0; i < step.size(); i++) {
                canvas.drawRect(LEFT += size, (canvas.getHeight() - 100) - (int) ((float) (Integer.valueOf(step.get(i)).intValue()) / (extremity) * (canvas.getHeight() - 100)), LEFT + size, canvas.getHeight() - 10, mpaint);
                canvas.drawLine(LEFT + 40, 100, LEFT + 40, (canvas.getHeight() - 100) - (int) ((float) (Integer.valueOf(step.get(i)).intValue()) / (extremity) * (canvas.getHeight() - 100)), mpaint3);
                item[i] = LEFT;
                canvas.drawText(step_data1.get(i), LEFT - 42, 80, mpaint4);
                LEFT += space;
            }
            py=item;
        } else {
            LEFT = 600;
            for (int i = 0; i < step.size(); i++) {
                canvas.drawRect(LEFT += size, (canvas.getHeight() - 100) - (int) ((float) (Integer.valueOf(step.get(i)).intValue()) / (extremity) * (canvas.getHeight() - 100)), LEFT + size, (canvas.getHeight() - 10), mpaint);
                canvas.drawLine(LEFT + 40, 100, LEFT + 40, (canvas.getHeight() - 100) - (int) ((float) (Integer.valueOf(step.get(i)).intValue()) / (extremity) * (canvas.getHeight() - 100)), mpaint3);
                item.clone();
                item[i] = LEFT;
                canvas.drawText(step_data1.get(i), LEFT - 42, 80, mpaint4);
                LEFT += space;
            }
            py.clone();
            py=item;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                for (int i = 0; i < item.length; i++) {
                    if (item[i] >= x - 40 && item[i] <= x + 40) {
                        Help_update_view.doHelp_update_view(1, i);
                    }
                }
                break;
        }
        return true;
    }
}
