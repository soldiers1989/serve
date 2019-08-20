package com.easyhome.serve.mvp.ui.activity.notification

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerNotificationInfoComponent
import com.easyhome.serve.di.module.NotificationInfoModule
import com.easyhome.serve.mvp.contract.notification.NotificationInfoContract
import com.easyhome.serve.mvp.presenter.notification.NotificationInfoPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 通知详情
 */
class NotificationInfoActivity : JRBaseActivity<NotificationInfoPresenter>(), NotificationInfoContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerNotificationInfoComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .notificationInfoModule(NotificationInfoModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_notification_info //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        tvPageTitle.text = "消息详情"
        ivPageBack.singleClick { killMyself() }
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }
}
