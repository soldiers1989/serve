package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle
import android.text.Html

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerApplyPostponeComponent
import com.easyhome.serve.di.module.ApplyPostponeModule
import com.easyhome.serve.mvp.contract.project.ApplyPostponeContract
import com.easyhome.serve.mvp.presenter.project.ApplyPostponePresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.ANodeAdapter
import com.easyhome.serve.mvp.ui.adapter.PhotoAdapter
import kotlinx.android.synthetic.main.activity_apply_postpone.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 发起延期申请
 */
class ApplyPostponeActivity : JRBaseActivity<ApplyPostponePresenter>(), ApplyPostponeContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerApplyPostponeComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .applyPostponeModule(ApplyPostponeModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_apply_postpone //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "延期申请"
        nodeRV.adapter = ANodeAdapter(arrayListOf("", "", ""))
        photoRV.adapter = PhotoAdapter(arrayListOf(""))
        poscontent1.text = Html.fromHtml("延期申请编号：<font color='#777777'>1234</font>")
        poscontent2.text = Html.fromHtml("计划施工日期：<font color='#777777'>2019-05-12  ~  2019-05-22</font>")
        poscontent4.text = Html.fromHtml("延期开始日期：<font color='#777777'>点击设置日期</font>")
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
