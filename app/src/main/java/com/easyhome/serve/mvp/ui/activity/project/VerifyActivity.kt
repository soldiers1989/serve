package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerVerifyComponent
import com.easyhome.serve.di.module.VerifyModule
import com.easyhome.serve.mvp.contract.project.VerifyContract
import com.easyhome.serve.mvp.presenter.project.VerifyPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.VerifyAdapter
import kotlinx.android.synthetic.main.activity_verify.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 验收
 */
class VerifyActivity : JRBaseActivity<VerifyPresenter>(), VerifyContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerVerifyComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .verifyModule(VerifyModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_verify //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "项目详情"
        verifyRV.adapter = VerifyAdapter(arrayListOf("", "", ""))
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
