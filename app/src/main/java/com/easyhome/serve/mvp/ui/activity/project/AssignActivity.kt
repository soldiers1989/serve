package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerAssignComponent
import com.easyhome.serve.di.module.AssignModule
import com.easyhome.serve.mvp.contract.project.AssignContract
import com.easyhome.serve.mvp.presenter.project.AssignPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.AssignHistoryAdapter
import com.easyhome.serve.mvp.ui.adapter.AssignWorkerAdapter
import kotlinx.android.synthetic.main.activity_assign.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 指派--类型1
 */
class AssignActivity : JRBaseActivity<AssignPresenter>(), AssignContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerAssignComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .assignModule(AssignModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_assign //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "指派"
        workerRV.adapter = AssignWorkerAdapter(
            arrayListOf(
                Pair("门店：丽泽桥", true),
                Pair("客户顾问：张三", true),
                Pair("设计师：李四", true),
                Pair("材料员：点击选择人员", false)
            )
        )
        history.adapter = AssignHistoryAdapter(arrayListOf("", "", ""))
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
