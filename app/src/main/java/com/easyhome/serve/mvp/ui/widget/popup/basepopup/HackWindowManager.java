package com.easyhome.serve.mvp.ui.widget.popup.basepopup;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

/**
 * Created by 大灯泡 on 2017/12/25.
 * <p>
 * 代理掉popup的windowmanager，在addView操作，拦截decorView的操作
 */
final class HackWindowManager extends InnerPopupWindowStateListener implements WindowManager {
    private static final String TAG = "HackWindowManager";
    private WindowManager mWindowManager;
    private WeakReference<PopupTouchController> mPopupController;
    private WeakReference<HackPopupDecorView> mHackPopupDecorView;
    private WeakReference<BasePopupHelper> mPopupHelper;
    private WeakReference<PopupMaskLayout> mMaskLayout;
    private static int statusBarHeight;

    public HackWindowManager(WindowManager windowManager, PopupTouchController popupTouchController) {
        mWindowManager = windowManager;
        mPopupController = new WeakReference<PopupTouchController>(popupTouchController);
    }

    @Override
    public Display getDefaultDisplay() {
        return mWindowManager == null ? null : mWindowManager.getDefaultDisplay();
    }

    @Override
    public void removeViewImmediate(View view) {
        if (mWindowManager == null) return;
        checkStatusBarHeight(view.getContext());
        if (isPopupInnerDecorView(view) && getHackPopupDecorView() != null) {
            if (getMaskLayout() != null) {
                try {
                    mWindowManager.removeViewImmediate(getMaskLayout());
                    mMaskLayout.clear();
                    mMaskLayout = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            HackPopupDecorView hackPopupDecorView = getHackPopupDecorView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (!hackPopupDecorView.isAttachedToWindow())return;
            }
            mWindowManager.removeViewImmediate(hackPopupDecorView);
            mHackPopupDecorView.clear();
            mHackPopupDecorView = null;
        } else {
            mWindowManager.removeViewImmediate(view);
        }
    }

    @Override
    public void addView(View view, ViewGroup.LayoutParams params) {
        if (mWindowManager == null) return;
        checkStatusBarHeight(view.getContext());
        if (isPopupInnerDecorView(view)) {
            /**
             * 此时的params是WindowManager.LayoutParams，需要留意强转问题
             * popup内部有scrollChangeListener，会有params强转为WindowManager.LayoutParams的情况
             */
            BasePopupHelper helper = getBasePopupHelper();

            //添加popup主体
            final HackPopupDecorView hackPopupDecorView = new HackPopupDecorView(view.getContext());
            params = hackPopupDecorView.addPopupDecorView(view, params, helper, getPopupController());
            mMaskLayout = new WeakReference<>(PopupMaskLayout.create(view.getContext(), helper, fitLayoutParamsPosition(params)));
            mHackPopupDecorView = new WeakReference<HackPopupDecorView>(hackPopupDecorView);

            if (getPopupController() instanceof BasePopupWindow) {
                ((BasePopupWindow) getPopupController()).setOnInnerPopupWindowStateListener(this);
            }
            hackPopupDecorView.setOnAttachListener(new HackPopupDecorView.OnAttachListener() {
                @Override
                public void onAttachtoWindow() {
                    if (getMaskLayout() != null) {
                        getMaskLayout().handleStart(-2);
                    }
                }
            });
            if (getMaskLayout() != null) {
                mWindowManager.addView(getMaskLayout(), createBlurBackgroundWindowParams(params));
            }
            mWindowManager.addView(hackPopupDecorView, fitLayoutParamsPosition(params));
        } else {
            mWindowManager.addView(view, params);
        }
    }

    private ViewGroup.LayoutParams fitLayoutParamsPosition(ViewGroup.LayoutParams params) {
        if (params instanceof LayoutParams) {
            LayoutParams p = (LayoutParams) params;
            BasePopupHelper helper = getBasePopupHelper();
            if (helper != null && helper.isShowAsDropDown() && p.y <= helper.getAnchorY()) {
                int y = helper.getAnchorY() + helper.getAnchorHeight() + helper.getInternalOffsetY();
                p.y = y <= 0 ? 0 : y;
            }
        }
        return params;
    }

    @Override
    public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
        if (mWindowManager == null) return;
        checkStatusBarHeight(view.getContext());
        if (isPopupInnerDecorView(view) && getHackPopupDecorView() != null) {
            HackPopupDecorView hackPopupDecorView = getHackPopupDecorView();
            mWindowManager.updateViewLayout(hackPopupDecorView, fitLayoutParamsPosition(params));
        } else {
            mWindowManager.updateViewLayout(view, params);
        }
    }

    @Override
    public void removeView(View view) {
        if (mWindowManager == null) return;
        checkStatusBarHeight(view.getContext());
        if (isPopupInnerDecorView(view) && getHackPopupDecorView() != null) {
            if (getMaskLayout() != null) {
                try {
                    mWindowManager.removeView(getMaskLayout());
                    mMaskLayout.clear();
                    mMaskLayout = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            HackPopupDecorView hackPopupDecorView = getHackPopupDecorView();
            mWindowManager.removeView(hackPopupDecorView);
            mHackPopupDecorView.clear();
            mHackPopupDecorView = null;
        } else {
            mWindowManager.removeView(view);
        }
    }

    public void clear() {
        try {
            removeViewImmediate(mHackPopupDecorView.get());
            removeViewImmediate(getMaskLayout());
            mHackPopupDecorView.clear();
            mMaskLayout.clear();
        } catch (Exception e) {
            //no print
        }
    }


    /**
     * 生成blurimageview的params
     *
     * @param params
     * @return
     */
    private ViewGroup.LayoutParams createBlurBackgroundWindowParams(ViewGroup.LayoutParams params) {
        ViewGroup.LayoutParams result = new ViewGroup.LayoutParams(params);

        if (params instanceof LayoutParams) {
            LayoutParams mResults = new LayoutParams();
            mResults.copyFrom((LayoutParams) params);

            mResults.flags |= LayoutParams.FLAG_NOT_FOCUSABLE;
            mResults.flags |= LayoutParams.FLAG_NOT_TOUCHABLE;
            mResults.flags |= LayoutParams.FLAG_LAYOUT_IN_SCREEN;
            mResults.windowAnimations = 0;
            mResults.x = 0;
            mResults.y = 0;
            mResults.format = PixelFormat.RGBA_8888;

            result = mResults;
        }
        result.width = ViewGroup.LayoutParams.MATCH_PARENT;
        result.height = ViewGroup.LayoutParams.MATCH_PARENT;
        return result;
    }

    private boolean isPopupInnerDecorView(View v) {
        if (v == null) return false;
        String viewSimpleClassName = v.getClass().getSimpleName();
        return TextUtils.equals(viewSimpleClassName, "PopupDecorView") || TextUtils.equals(viewSimpleClassName, "PopupViewContainer");
    }

    private HackPopupDecorView getHackPopupDecorView() {
        if (mHackPopupDecorView == null) return null;
        return mHackPopupDecorView.get();
    }

    private PopupTouchController getPopupController() {
        if (mPopupController == null) return null;
        return mPopupController.get();
    }

    private BasePopupHelper getBasePopupHelper() {
        if (mPopupHelper == null) return null;
        return mPopupHelper.get();
    }

    private PopupMaskLayout getMaskLayout() {
        if (mMaskLayout == null) return null;
        return mMaskLayout.get();
    }

    void bindPopupHelper(BasePopupHelper helper) {
        mPopupHelper = new WeakReference<BasePopupHelper>(helper);
    }

    private void checkStatusBarHeight(Context context) {
        if (statusBarHeight != 0 || context == null) return;
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        statusBarHeight = result;
    }

    @Override
    void onAnimateDismissStart() {
        if (getMaskLayout() != null) {
            getMaskLayout().handleDismiss(-2);
        }
    }

    @Override
    void onNoAnimateDismiss() {
        if (getMaskLayout() != null) {
            getMaskLayout().handleDismiss(0);
        }
    }
}
