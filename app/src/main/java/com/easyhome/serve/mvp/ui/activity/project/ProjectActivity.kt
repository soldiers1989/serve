package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerProjectComponent
import com.easyhome.serve.di.module.ProjectModule
import com.easyhome.serve.mvp.contract.project.ProjectContract
import com.easyhome.serve.mvp.presenter.project.ProjectPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.mvp.ui.adapter.ProjectActionAdapter
import com.easyhome.serve.mvp.ui.adapter.ProjectProgressAdapter
import com.easyhome.serve.mvp.ui.adapter.ProjectServeAdapter
import com.easyhome.serve.mvp.ui.adapter.WaitThingAdapter
import kotlinx.android.synthetic.main.activity_project.*


/**
 * 项目详情
 */
class ProjectActivity : JRBaseActivity<ProjectPresenter>(), ProjectContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerProjectComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .projectModule(ProjectModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_project //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        progressRV.adapter = ProjectProgressAdapter(arrayListOf("", "", "", "", ""))
        actionRV.adapter = ProjectActionAdapter(arrayListOf("", "", "", "", "", "", "", ""))
        serveRV.adapter = ProjectServeAdapter(arrayListOf("", "", "", "", "", "", "", ""))
        dataRV.adapter = WaitThingAdapter(arrayListOf("", "", "", "", "", "", "", "", "", ""))
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
