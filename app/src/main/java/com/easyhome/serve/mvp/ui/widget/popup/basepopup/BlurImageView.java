package com.easyhome.serve.mvp.ui.widget.popup.basepopup;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by 大灯泡 on 2017/12/27.
 * <p>
 * 模糊imageview
 */
@SuppressLint("AppCompatCustomView")
public class BlurImageView extends ImageView {
    private static final String TAG = "BlurImageView";

    private static final long BLUR_TASK_WAIT_TIMEOUT = 1000;//图片模糊超时1秒
    private volatile boolean abortBlur = false;
    private WeakReference<PopupBlurOption> mBlurOption;
    private AtomicBoolean blurFinish = new AtomicBoolean(false);
    private volatile boolean isAnimating = false;
    private AtomicBoolean waitForBlurTask = new AtomicBoolean(false);
    private long startDuration;
    private long startTime;


    public BlurImageView(Context context) {
        this(context, null);
    }

    public BlurImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlurImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFocusable(false);
        setFocusableInTouchMode(false);
        setScaleType(ScaleType.MATRIX);
        setAlpha(0f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(null);
        } else {
            setBackgroundDrawable(null);
        }
    }

    public void applyBlurOption(PopupBlurOption option) {
        if (option == null) return;
        mBlurOption = new WeakReference<PopupBlurOption>(option);
        View anchorView = option.getBlurView();
        if (anchorView == null) {
//            PopupLogUtil.trace(LogTag.e, TAG, "模糊锚点View为空，放弃模糊操作...");
            return;
        }
        if (option.isBlurAsync()) {
//            PopupLogUtil.trace(LogTag.i, TAG, "子线程blur");
            startBlurTask(anchorView);
        } else {
            try {
//                PopupLogUtil.trace(LogTag.i, TAG, "主线程blur");
                if (!BlurHelper.renderScriptSupported()) {
//                    PopupLogUtil.trace(LogTag.e, TAG, "不支持脚本模糊。。。最低支持api 17(Android 4.2.2)，将采用fastblur");
                }
                setImageBitmapOnUiThread(BlurHelper.blur(getContext(), anchorView, option.getBlurPreScaleRatio(), option.getBlurRadius(), option.isFullScreen()));
            } catch (Exception e) {
//                PopupLogUtil.trace(LogTag.e, TAG, "模糊异常");
                e.printStackTrace();
            }
        }
    }

    PopupBlurOption getOption() {
        if (mBlurOption == null) return null;
        return mBlurOption.get();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        abortBlur = true;
    }

    /**
     * alpha进场动画
     *
     * @param duration
     */
    public void start(long duration) {
        startDuration = duration;
        if (!blurFinish.get() || waitForBlurTask.get()) {
            startTime = System.currentTimeMillis();
            waitForBlurTask.compareAndSet(false, true);
//            PopupLogUtil.trace(LogTag.e, TAG, "等待模糊完成");
            return;
        }
        if (isAnimating) return;
//        PopupLogUtil.trace(LogTag.i, TAG, "开始模糊imageview alpha动画");
        isAnimating = true;
        if (duration > 0) {
            animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setInterpolator(new DecelerateInterpolator())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isAnimating = false;
                        }
                    })
                    .start();
        } else if (duration == -2) {
            animate()
                    .alpha(1f)
                    .setDuration(getOption() == null ? 300 : getOption().getBlurInDuration())
                    .setInterpolator(new DecelerateInterpolator())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isAnimating = false;
                        }
                    })
                    .start();
        } else {
            setAlpha(1f);
        }
    }

    /**
     * alpha退场动画
     *
     * @param duration
     */
    public void dismiss(long duration) {
        isAnimating = false;
//        PopupLogUtil.trace(LogTag.i, TAG, "dismiss模糊imageview alpha动画");
        if (duration > 0) {
            animate()
                    .alpha(0f)
                    .setDuration(duration)
                    .setInterpolator(new DecelerateInterpolator())
                    .start();
        } else if (duration == -2) {
            animate()
                    .alpha(0f)
                    .setDuration(getOption() == null ? 300 : getOption().getBlurOutDuration())
                    .setInterpolator(new DecelerateInterpolator())
                    .start();
        } else {
            setAlpha(0f);
        }
    }

    /**
     * 子线程模糊
     *
     * @param anchorView
     */
    private void startBlurTask(View anchorView) {
        ThreadPoolManager.execute(new CreateBlurBitmapRunnable(BlurHelper.getViewBitmap(anchorView, getOption().isFullScreen())));
    }

    /**
     * 判断是否处于主线程，并进行设置bitmap
     *
     * @param blurBitmap
     */
    private void setImageBitmapOnUiThread(final Bitmap blurBitmap) {
        if (isUiThread()) {
            handleSetImageBitmap(blurBitmap);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    handleSetImageBitmap(blurBitmap);
                }
            });
        }
    }

    /**
     * 设置bitmap，并进行后续处理（此方法必定运行在主线程）
     *
     * @param bitmap
     */
    private void handleSetImageBitmap(Bitmap bitmap) {
        if (bitmap != null) {
//            PopupLogUtil.trace(LogTag.i, "bitmap: 【" + bitmap.getWidth() + "," + bitmap.getHeight() + "】");
        }
        setAlpha(0f);
        setImageBitmap(bitmap);
        if (getOption() != null) {
            PopupBlurOption option = getOption();
            if (!option.isFullScreen()) {
                //非全屏的话，则需要将bitmap变化到对应位置
                View anchorView = option.getBlurView();
                if (anchorView == null) return;
                Rect rect = new Rect();
                anchorView.getGlobalVisibleRect(rect);
                Matrix matrix = getImageMatrix();
                matrix.setTranslate(rect.left, rect.top);
                setImageMatrix(matrix);
            }
        }
        blurFinish.compareAndSet(false, true);
        if (waitForBlurTask.get()) {
            if (System.currentTimeMillis() - startTime >= BLUR_TASK_WAIT_TIMEOUT) {
//                PopupLogUtil.trace(LogTag.e, TAG, "模糊等待超时");
                waitForBlurTask.set(false);
            } else {
                waitForBlurTask.compareAndSet(true, false);
                start(startDuration);
            }
        }
//        PopupLogUtil.trace(LogTag.i, TAG, "设置成功：" + blurFinish.get());
    }

    private boolean isUiThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    public void destroy() {
        setImageBitmap(null);
        abortBlur = true;
        if (mBlurOption != null) {
            mBlurOption.clear();
            mBlurOption = null;
        }
        blurFinish.set(false);
        isAnimating = false;
        startDuration = 0;
    }

    class CreateBlurBitmapRunnable implements Runnable {

        private Bitmap bitmap;

        CreateBlurBitmapRunnable(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        @Override
        public void run() {
            if (abortBlur || getOption() == null) {
//                PopupLogUtil.trace(LogTag.e, TAG, "放弃模糊，可能是已经移除了布局");
                return;
            }
            setImageBitmapOnUiThread(BlurHelper.blur(getContext(), bitmap, getOption().getBlurPreScaleRatio(), getOption().getBlurRadius()));
        }
    }
}
