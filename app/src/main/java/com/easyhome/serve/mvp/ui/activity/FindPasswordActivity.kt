package com.easyhome.serve.mvp.ui.activity

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerFindPasswordComponent
import com.easyhome.serve.di.module.FindPasswordModule
import com.easyhome.serve.mvp.contract.FindPasswordContract
import com.easyhome.serve.mvp.presenter.FindPasswordPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import kotlinx.android.synthetic.main.activity_find_password.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity


/**
 *
 * 找回密码
 *
 */
class FindPasswordActivity : JRBaseActivity<FindPasswordPresenter>(), FindPasswordContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerFindPasswordComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .findPasswordModule(FindPasswordModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_find_password //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        tvPageTitle.text = "忘记密码"
        ivPageBack.singleClick { killMyself() }
        nextB.singleClick {
            startActivity<SetPasswordActivity>()
        }
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
