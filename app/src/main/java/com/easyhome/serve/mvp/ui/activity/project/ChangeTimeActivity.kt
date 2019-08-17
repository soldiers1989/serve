package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerChangeTimeComponent
import com.easyhome.serve.di.module.ChangeTimeModule
import com.easyhome.serve.mvp.contract.project.ChangeTimeContract
import com.easyhome.serve.mvp.presenter.project.ChangeTimePresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.AssignHistoryAdapter
import com.easyhome.serve.mvp.ui.adapter.PInfoAdapter
import kotlinx.android.synthetic.main.activity_change_time.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 变更时间
 */
class ChangeTimeActivity : JRBaseActivity<ChangeTimePresenter>(), ChangeTimeContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerChangeTimeComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .changeTimeModule(ChangeTimeModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_change_time //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "变更预交底/量房时间"

        info.adapter = PInfoAdapter(
            arrayListOf(
                "客户编号：0001",
                "客户姓名：张三",
                "客户地址：北京市西城区xxxxxx",
                "量房日期：点击设置日期",
                "量房时间：点击设置时间"
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
