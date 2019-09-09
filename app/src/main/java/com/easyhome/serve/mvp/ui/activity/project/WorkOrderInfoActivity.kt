package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerWorkOrderInfoComponent
import com.easyhome.serve.di.module.WorkOrderInfoModule
import com.easyhome.serve.mvp.contract.project.WorkOrderInfoContract
import com.easyhome.serve.mvp.presenter.project.WorkOrderInfoPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.HistoryAdapter
import com.easyhome.serve.mvp.ui.adapter.IssueAdapter
import com.easyhome.serve.mvp.ui.adapter.PInfoAdapter
import kotlinx.android.synthetic.main.activity_work_order_info.*
import kotlinx.android.synthetic.main.activity_work_order_info.view.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 工单详情
 */
class WorkOrderInfoActivity : JRBaseActivity<WorkOrderInfoPresenter>(), WorkOrderInfoContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerWorkOrderInfoComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .workOrderInfoModule(WorkOrderInfoModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_work_order_info //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "工单页"
        dateRV.adapter = PInfoAdapter(arrayListOf("签约日期：2019-06-28", "竣工日期：2019-06-28", "结算日期：2019-06-28"))
        info.adapter = PInfoAdapter(arrayListOf("工单信息：1111", "工单类型：客诉", "发起人：客服-张三"))
        workorderRV.adapter = IssueAdapter(arrayListOf(""))
        historyRV.adapter = HistoryAdapter(arrayListOf(""))
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
