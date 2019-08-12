package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle
import android.view.View

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerAbarbeitungComponent
import com.easyhome.serve.di.module.AbarbeitungModule
import com.easyhome.serve.mvp.contract.project.AbarbeitungContract
import com.easyhome.serve.mvp.presenter.project.AbarbeitungPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.AbarbeitungAdapter
import kotlinx.android.synthetic.main.activity_abarbeitung.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 整改
 */
class AbarbeitungActivity : JRBaseActivity<AbarbeitungPresenter>(), AbarbeitungContract.View {
    override fun getMyself(): BaseActivity<*> = this


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerAbarbeitungComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .abarbeitungModule(AbarbeitungModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_abarbeitung //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        tvPageTitle.text = "整改"
        tvPageRight.text = "发起"
        tvPageRight.visibility = View.VISIBLE
        ivPageBack.singleClick { killMyself() }
        tvPageRight.setBackgroundResource(R.drawable.white_side)
        abarbeitungRV.adapter = AbarbeitungAdapter(arrayListOf("", "", ""))
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
