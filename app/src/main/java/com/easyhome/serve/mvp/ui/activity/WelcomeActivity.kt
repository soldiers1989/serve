package com.easyhome.serve.mvp.ui.activity

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerWelcomeComponent
import com.easyhome.serve.di.module.WelcomeModule
import com.easyhome.serve.mvp.contract.WelcomeContract
import com.easyhome.serve.mvp.presenter.WelcomePresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.extension.singleClick
import kotlinx.android.synthetic.main.activity_welcome.*
import org.jetbrains.anko.startActivity


/**
 * 欢迎页
 *
 */
class WelcomeActivity : BaseActivity<WelcomePresenter>(), WelcomeContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerWelcomeComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .welcomeModule(WelcomeModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_welcome //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {

        skipB.singleClick {
            startActivity<LoginActivity>()
        }
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
