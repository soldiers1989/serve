package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle
import android.view.View

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerWorkingPlanComponent
import com.easyhome.serve.di.module.WorkingPlanModule
import com.easyhome.serve.mvp.contract.project.WorkingPlanContract
import com.easyhome.serve.mvp.presenter.project.WorkingPlanPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.WorkingPlanAdapter
import kotlinx.android.synthetic.main.activity_working_plan.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 施工排期计划
 */
class WorkingPlanActivity : JRBaseActivity<WorkingPlanPresenter>(), WorkingPlanContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerWorkingPlanComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .workingPlanModule(WorkingPlanModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_working_plan //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {

        tvPageTitle.text = "施工排期计划"
        ivPageBack.singleClick { killMyself() }
        ivPageRight.setImageResource(R.mipmap.calendar_icon)
        ivPageRight.visibility = View.VISIBLE

        planRV.adapter = WorkingPlanAdapter(arrayListOf("", "", ""))

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
