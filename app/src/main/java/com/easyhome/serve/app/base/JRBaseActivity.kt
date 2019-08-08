package com.easyhome.serve.app.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper
import com.jess.arms.base.BaseActivity
import com.jess.arms.mvp.IPresenter
import org.jetbrains.anko.matchParent
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.content.IntentFilter
import com.easyhome.serve.R
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.app.receiver.NetWorkChangReceiver
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.layout_title.view.*


/**
 * Time 2018/9/3  下午3:11
 *
 * JRBaseActivity
 */
abstract class JRBaseActivity<P : IPresenter> : BaseActivity<P>(), BGASwipeBackHelper.Delegate, BaseTitleManager, IView {
    private var isRegistered = false
    private var netWorkChangReceiver: NetWorkChangReceiver? = null
    /**
     * 是否需要使用带有TitleBar的父容器
     */
    override fun isUseParentLayout() = true

    /**
     * 是否需要显示TitleBar右侧按钮
     */
    override fun isUseTitleRight() = false

    //设置改页面是否检查登录状态，默认需要检查
    open fun isToken() = true

    protected lateinit var mSwipeBackHelper: BGASwipeBackHelper

    lateinit var netErrorLayout: View
    private var savedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // 顺序不可变 需在调用super之前置为false 不需要BaseActivity来创建布局
        isNeedBaseActivityCreateLayout = false
        initSwipeBackFinish()
        super.onCreate(savedInstanceState)

        netErrorLayout = LayoutInflater.from(this).inflate(R.layout.layout_net_error, null)
        netErrorLayout.singleClick {
            netErrorLayout.visibility = View.GONE
            initData(savedInstanceState)
        }

        if (isUseParentLayout()) {
            setContentView(R.layout.activity_base)
            baseContent.addView(LayoutInflater.from(this).inflate(initView(savedInstanceState), null))
            baseContent.addView(netErrorLayout)
        } else {
            setContentView(initView(savedInstanceState))
            addContentView(netErrorLayout, FrameLayout.LayoutParams(matchParent, matchParent))
        }
        netErrorLayout.visibility = View.GONE

        initData(savedInstanceState)

        if (isUseParentLayout() && isUseTitleRight()) {
            baseTitle.ivPageRight.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        netWorkChangReceiver = NetWorkChangReceiver()
        /*netWorkChangReceiver!!.setCallBack() { msg ->
            if (DeviceUtils.getVersionCode(this).toString() in msg.androidVesionMsg?.forceUpdateVersions?: arrayListOf()) {
                //如果在强制更新列表，则直接执行 强制更新
                DialogUtils.showUpDataDialog(this, msg.androidVesionMsg.updateDescription, msg.androidVesionMsg.apkUrl, View.GONE)
            } else if (DeviceUtils.getVersionCode(this) <= msg.androidVesionMsg.minVersion.strToInt()) {//当前版本不高于最小版本，需要强制更新
                DialogUtils.showUpDataDialog(this, msg.androidVesionMsg.updateDescription, msg.androidVesionMsg.apkUrl, View.GONE)
            } else if (DeviceUtils.getVersionCode(this) < msg.androidVesionMsg.nowVersion.strToInt()) {//当前版本小于最新版本 建议更新
                DialogUtils.showUpDataDialog(this, msg.androidVesionMsg.updateDescription, msg.androidVesionMsg.apkUrl)
            }
        }*/

        val filter = IntentFilter()
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(netWorkChangReceiver, filter)
        isRegistered = true
    }

    override fun onPause() {
        super.onPause()
        //解绑
        if (isRegistered) {
            unregisterReceiver(netWorkChangReceiver);
        }

    }

    override fun showNetError() {
        netErrorLayout.visibility = View.VISIBLE
    }

    override fun isSupportSwipeBack() = true


    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private fun initSwipeBackFinish() {
        mSwipeBackHelper = BGASwipeBackHelper(this, this)

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(isSupportSwipeBack)
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true)
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true)
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow)
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true)
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true)
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f)
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false)
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    override fun onSwipeBackLayoutSlide(slideOffset: Float) {}

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    override fun onSwipeBackLayoutCancel() {}

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    override fun onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward()
    }

    override fun onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding) {
            return
        }
        mSwipeBackHelper.backward()
    }



    open fun activityResult(requestCode: Int, resultCode: Int, data: Intent?) {}
//    /**
//     * 设置状态栏颜色
//     *
//     * @param color
//     */
//    fun setStatusBarColor(@ColorInt color: Int) {
//        setStatusBarColor(color, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA)
//    }
//
//    /**
//     * 设置状态栏颜色
//     *
//     * @param color
//     * @param statusBarAlpha 透明度
//     */
//    fun setStatusBarColor(@ColorInt color: Int, @IntRange(from = 0, to = 255) statusBarAlpha: Int) {
//        StatusBarUtil.setColorForSwipeBack(this, color, statusBarAlpha)
//    }

}