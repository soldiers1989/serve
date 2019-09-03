package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle
import android.view.View

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerAddTaskComponent
import com.easyhome.serve.di.module.AddTaskModule
import com.easyhome.serve.mvp.contract.project.AddTaskContract
import com.easyhome.serve.mvp.presenter.project.AddTaskPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.backgroundResource


/**
 * 添加日历
 */
class AddTaskActivity : JRBaseActivity<AddTaskPresenter>(), AddTaskContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerAddTaskComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .addTaskModule(AddTaskModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_add_task //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "添加日程"
        tvPageRight.text = "保存"
        tvPageRight.visibility = View.VISIBLE
        tvPageRight.backgroundResource = R.drawable.white_side
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
