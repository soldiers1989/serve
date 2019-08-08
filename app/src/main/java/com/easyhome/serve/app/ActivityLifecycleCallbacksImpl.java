package com.easyhome.serve.app;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.KeyboardPatch;

import com.easyhome.serve.R;
import timber.log.Timber;

/**
 * ================================================
 * 展示 {@link Application.ActivityLifecycleCallbacks} 的用法
 * <p>
 * Created by MVPArmsTemplate
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@SuppressWarnings("deprecation")
public class ActivityLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Timber.w(activity + " - onActivityCreated");
        // 所有子类都将继承这些相同的属性,请在设置界面之后设置
        if (!activity.toString().contains("com.yalantis.ucrop.UCropActivity")) {
            if (!activity.toString().contains("com.yalantis.ucrop.PictureMultiCuttingActivity")) {

                if (!activity.toString().contains("H5PayActivity")) {
                    ImmersionBar.with(activity)
                            .statusBarDarkFont(true, 0.8f)
                            //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，
                            // 如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                            .init();
                }
            }
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        //全局设置  如果页面有输入框防止软键盘覆盖输入框
        KeyboardPatch.patch(activity, activity.findViewById(android.R.id.content)).enable(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        Timber.w(activity + " - onActivityStarted");
        if (!activity.getIntent().getBooleanExtra("isInitToolbar", false)) {
            //由于加强框架的兼容性,故将 setContentView 放到 onActivityCreated 之后,onActivityStarted 之前执行
            //而 findViewById 必须在 Activity setContentView() 后才有效,所以将以下代码从之前的 onActivityCreated 中移动到 onActivityStarted 中执行
            activity.getIntent().putExtra("isInitToolbar", true);
            //这里全局给Activity设置toolbar和title,你想象力有多丰富,这里就有多强大,以前放到BaseActivity的操作都可以放到这里
            if (activity.findViewById(R.id.toolbar) != null) {
                if (activity instanceof AppCompatActivity) {
                    ((AppCompatActivity) activity).setSupportActionBar(activity.findViewById(R.id.toolbar));
                    ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        activity.setActionBar(activity.findViewById(R.id.toolbar));
                        activity.getActionBar().setDisplayShowTitleEnabled(false);
                    }
                }
            }
            /*if (activity.findViewById(R.id.tvPageTitle) != null) {
                ((TextView) activity.findViewById(R.id.tvPageTitle)).setText(activity.getTitle());
            }
            if (activity.findViewById(R.id.ivPageBack) != null) {
                activity.findViewById(R.id.ivPageBack).setOnClickListener(v -> activity.onBackPressed());
            }*/
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
//        Timber.w(activity + " - onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//        Timber.w(activity + " - onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
//        Timber.w(activity + " - onActivityDestroyed");
        //横竖屏切换或配置改变时, Activity 会被重新创建实例, 但 Bundle 中的基础数据会被保存下来,移除该数据是为了保证重新创建的实例可以正常工作
        activity.getIntent().removeExtra("isInitToolbar");
        // 必须调用该方法，防止内存泄漏
        ImmersionBar.with(activity).destroy();
    }

//    private String CALLBACK_RECEIVER_ACTION = "callback_receiver_action";
//
//    private void getUmeng(Activity activity) {
//        UMConfigure.init(activity, "5be502d9f1f556316e0001d3", "JR000", UMConfigure.DEVICE_TYPE_PHONE, "404757f4d6efd441e972c8f3dc5f79d4");
//        PushAgent mPushAgent = PushAgent.getInstance(activity);
//        mPushAgent.register(new IUmengRegisterCallback() {
//            @Override
//            public void onSuccess(String deviceToken) {
//                SettingHelper.getInstance().saveString("deviceToken", deviceToken);
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//
//            }
//        });
//        // 通知栏点击事件
//        mPushAgent.setNotificationClickHandler(new UmengNotificationClickHandler() {
//                                                   @Override
//                                                   public void dealWithCustomAction(Context context, UMessage uMessage) {
//                                                       Toast.makeText(context, uMessage.custom, Toast.LENGTH_LONG).show();
//                                                   }
//
//                                                   @Override
//                                                   public void dismissNotification(Context context, UMessage uMessage) {
//                                                       super.dismissNotification(context, uMessage);
//                                                       Toast.makeText(context, uMessage.custom, Toast.LENGTH_LONG).show();
//                                                   }
//
//                                                   @Override
//                                                   public void openUrl(Context context, UMessage uMessage) {
//                                                       super.openUrl(context, uMessage);
//                                                       Intent intent = new Intent(activity, SplashActivity.class);
//                                                       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                                       intent.putExtra("Umeng", uMessage.url);
//                                                       activity.startActivity(intent);
//                                                   }
//                                               }
//        );
//
//        mPushAgent.setMessageHandler(new UmengMessageHandler() {
//            private Notification.Builder builder;
//
//            @Override
//            public Notification getNotification(Context context, UMessage uMessage) {
//                if (Build.VERSION.SDK_INT >= 26) {
//                    NotificationManager manager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
//                    NotificationChannel channel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_HIGH);
//                    manager.createNotificationChannel(channel);
//                    builder = new Notification.Builder(activity, "channel_id");
//                    builder.setSmallIcon(R.mipmap.ic_launcher)
//                            .setWhen(System.currentTimeMillis())
//                            .setLargeIcon(BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher))
//                            .setContentTitle(uMessage.title)
//                            .setContentText(uMessage.text)
//                            .setAutoCancel(true)
//                            .setOnlyAlertOnce(true);
//                    return builder.getNotification();
//                } else {
//                    builder = new Notification.Builder(activity);
//                    builder.setSmallIcon(R.mipmap.ic_launcher)
//                            .setWhen(System.currentTimeMillis())
//                            .setLargeIcon(BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher))
//                            .setContentTitle(uMessage.title)
//                            .setContentText(uMessage.text)
//                            .setAutoCancel(true)
//                            .setOnlyAlertOnce(true);
//                    return builder.getNotification();
//                }
//            }
//
//            @Override
//            public void dealWithNotificationMessage(Context context, UMessage uMessage) {
//                super.dealWithNotificationMessage(context, uMessage);
//                new Handler().post(() -> {
//                    // 对自定义消息的处理方式，点击或者忽略
//                    UTrack.getInstance(activity).trackMsgClick(uMessage);
//                });
//            }
//        });
//
//        mPushAgent.setDisplayNotificationNumber(2);
//        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER);
//        mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SERVER);
//        mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SERVER);
//
//        mPushAgent.setRegisterCallback(new IUmengRegisterCallback() {
//            @Override
//            public void onSuccess(String s) {
//                activity.sendBroadcast(new Intent(CALLBACK_RECEIVER_ACTION));
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//
//            }
//        });
//    }
}
