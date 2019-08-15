package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerCustomerTagComponent
import com.easyhome.serve.di.module.CustomerTagModule
import com.easyhome.serve.mvp.contract.project.CustomerTagContract
import com.easyhome.serve.mvp.presenter.project.CustomerTagPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.EvaluateAdapter
import com.easyhome.serve.mvp.ui.adapter.TagAdapter
import kotlinx.android.synthetic.main.activity_customer_tag.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 客户标签
 */
class CustomerTagActivity : JRBaseActivity<CustomerTagPresenter>(), CustomerTagContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerCustomerTagComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .customerTagModule(CustomerTagModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_customer_tag //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {

        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "客户标签"
        evaluate.adapter = EvaluateAdapter(arrayListOf("", "", ""))
        tag.adapter = TagAdapter(arrayListOf("", "", "", "", "", "", "", ""))
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
