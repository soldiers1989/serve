package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerAssignType2Component
import com.easyhome.serve.di.module.AssignType2Module
import com.easyhome.serve.mvp.contract.project.AssignType2Contract
import com.easyhome.serve.mvp.presenter.project.AssignType2Presenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.AssignHistoryAdapter
import com.easyhome.serve.mvp.ui.adapter.AssignWorker2Adapter
import com.easyhome.serve.mvp.ui.adapter.AssignWorkerAdapter
import kotlinx.android.synthetic.main.activity_assign_type2.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 指派-类型2
 */
class AssignType2Activity : JRBaseActivity<AssignType2Presenter>(), AssignType2Contract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerAssignType2Component //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .assignType2Module(AssignType2Module(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_assign_type2 //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "指派"
        workerRV.adapter = AssignWorkerAdapter(arrayListOf(
            Pair("门店：丽泽桥",false),
            Pair("客户顾问：张三",false),
            Pair("设计师：李四",false),
            Pair("材料员：王五",false),
            Pair("区域经理：狗蛋",true),
            Pair("装修管家：点击选择人员",false),
            Pair("项目经理：点击选择人员",false),
            Pair("工长：点击选择人员",false),
            Pair("工人：",false)
        )
        )
        history.adapter = AssignHistoryAdapter(arrayListOf("", "", ""))
        workerTypeRV.adapter = AssignWorker2Adapter(arrayListOf("", "", ""))
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
