package com.easyhome.serve.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerScheduleComponent
import com.easyhome.serve.di.module.ScheduleModule
import com.easyhome.serve.mvp.contract.ScheduleContract
import com.easyhome.serve.mvp.presenter.SchedulePresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.activity.project.MapActivity
import com.easyhome.serve.mvp.ui.adapter.ScheduleAdapter
import com.easyhome.serve.mvp.ui.adapter.WaitThingAdapter
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity


/**
 * 我的日程
 */
class ScheduleActivity : JRBaseActivity<SchedulePresenter>(), ScheduleContract.View {
    override fun getMyself(): BaseActivity<*> = this
    override fun isUseParentLayout(): Boolean = false


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerScheduleComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .scheduleModule(ScheduleModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_schedule //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "我的日程"
        ivPageRight.setImageResource(R.mipmap.calendar_icon)
        ivPageRight.visibility = View.VISIBLE
        scheduleRV.adapter = ScheduleAdapter(arrayListOf("", "", ""))

        ivPageRight.singleClick {
            topCalendar.visibility = View.VISIBLE
            listRV.visibility = View.GONE
        }
        schedule2.singleClick {
            topCalendar.visibility = View.GONE
            listRV.visibility = View.VISIBLE
        }
        val wAdapter = WaitThingAdapter(arrayListOf("", "", "", "", "", ""))
        wAdapter.setOnItemChildClickListener { adapter, view, position ->

            when (view.id) {
                R.id.locationTV -> {
                    startActivity<MapActivity>()
                }
            }
        }
        waitThing.adapter = wAdapter
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
