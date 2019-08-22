package com.easyhome.serve.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
import com.jess.arms.utils.DeviceUtils;

public class MoveTextView extends TextView {

    private static final String TAG = "MoveTextView";

    private int lastX = 0;
    private int lastY = 0;

    private static int screenWidth;
    private static int screenHeight;

    public MoveTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        screenWidth = (int) (DeviceUtils.getScreenWidth(context));
        screenHeight = (int) (DeviceUtils.getScreenHeight(context));

        System.out.println("screenWidth==" + screenWidth + "   screenHeight=" + screenHeight);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();

                break;
            case MotionEvent.ACTION_MOVE:


                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;

                int left = getLeft() + dx;
                int top = getTop() + dy;
                int right = getRight() + dx;
                int bottom = getBottom() + dy;
                if (left < 0) {
                    left = 0;
                    right = left + getWidth();
                }
                if (right > screenWidth) {
                    right = screenWidth;
                    left = right - getWidth();
                }
                if (top < 0) {
                    top = 0;
                    bottom = top + getHeight();
                }
                if (bottom > screenHeight) {
                    bottom = screenHeight;
                    top = bottom - getHeight();
                }
                layout(left, top, right, bottom);
                System.out.println("dx==" + dx + "   dy=" + dy + "   lastX=" + lastX + "  lastY==" + lastY);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();


                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;
    }
}

