package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerVerifyNoComponent
import com.easyhome.serve.di.module.VerifyNoModule
import com.easyhome.serve.mvp.contract.project.VerifyNoContract
import com.easyhome.serve.mvp.presenter.project.VerifyNoPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.PhotoAdapter
import kotlinx.android.synthetic.main.activity_verify_no.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 验收-不通过
 */
class VerifyNoActivity : JRBaseActivity<VerifyNoPresenter>(), VerifyNoContract.View {
    override fun getMyself(): BaseActivity<*> = this
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerVerifyNoComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .verifyNoModule(VerifyNoModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_verify_no //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "验收不通过"
        photoRV.adapter=PhotoAdapter(arrayListOf(""))

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
