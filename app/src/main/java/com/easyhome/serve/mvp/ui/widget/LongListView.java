package com.easyhome.serve.mvp.ui.widget;

import android.content.Context;
import android.widget.ListView;

public class LongListView extends ListView {
    public LongListView(Context context) {
        super(context);
    }


    public LongListView(Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
