package com.easyhome.serve.mvp.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.easyhome.serve.R;


import java.util.Objects;


public class BottomAnimDialog extends Dialog {


    private final Context mContext;

    private final String mItem1Name;
    private final String mItem2Name;
    private final String mItem3Name;


    private BottonAnimDialogListener mListener;
    private TextView mTvItem1;
    private TextView mTvItem2;
    private TextView mTvItem3;

    public BottomAnimDialog(Context context, String item1Name, String item2Name, String item3Name) {

        super(context, R.style.dialog);
        this.mContext = context;

        this.mItem1Name = item1Name;
        this.mItem2Name = item2Name;
        this.mItem3Name = item3Name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_anim_dialog_layout);

        mTvItem1 = findViewById(R.id.tv_item1);
        mTvItem2 = findViewById(R.id.tv_item2);
        mTvItem3 = findViewById(R.id.tv_item3);

        mTvItem1.setOnClickListener(new clickListener());
        mTvItem2.setOnClickListener(new clickListener());
        mTvItem3.setOnClickListener(new clickListener());
        setData();
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = Objects.requireNonNull(getWindow()).getAttributes();
        layoutParams.gravity=Gravity.BOTTOM;
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height= WindowManager.LayoutParams.WRAP_CONTENT;

        getWindow().getDecorView().setPadding(0, 0, 0, 0);

        getWindow().setAttributes(layoutParams);
    }

    private void setData() {
        mTvItem1.setText(mItem1Name);
        mTvItem2.setText(mItem2Name);
        mTvItem3.setText(mItem3Name);
    }

    public void setItem1TextColor(int colorId) {//设置item的字体颜色
        if (mTvItem1 != null) {
            mTvItem1.setTextColor(colorId);
        }
    }

    public void setItem2TextColor(int colorId) {
        if (mTvItem2 != null) {
            mTvItem2.setTextColor(colorId);
        }
    }

    public void setItem3TextColor(int colorId) {
        if (mTvItem3 != null) {
            mTvItem3.setTextColor(colorId);
        }
    }

    public void setClickListener(BottonAnimDialogListener listener) {
        this.mListener = listener;
    }

    public interface BottonAnimDialogListener {
        void onItem1Listener();

        void onItem2Listener();

        void onItem3Listener();
    }


    private class clickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_item1:
                    if (mListener != null) {
                        mListener.onItem1Listener();
                    }
                    break;
                case R.id.tv_item2:
                    if (mListener != null) {
                        mListener.onItem2Listener();
                    }
                    break;
                case R.id.tv_item3:
                    if (mListener != null) {
                        mListener.onItem3Listener();
                    }
                    break;
            }
        }
    }
}

