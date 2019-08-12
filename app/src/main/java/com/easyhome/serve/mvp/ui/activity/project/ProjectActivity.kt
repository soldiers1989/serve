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
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.*
import kotlinx.android.synthetic.main.activity_project.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity


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
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "项目详情"
        progressRV.adapter = ProjectProgressAdapter(
            arrayListOf(
                Pair(R.mipmap.progress_icon_2, "量房"),
                Pair(R.mipmap.progress_icon_2, "预交底"),
                Pair(R.mipmap.progress_icon_2, "待开工"),
                Pair(R.mipmap.progress_icon_2, "施工中\n基础"),
                Pair(R.mipmap.progress_icon_2, "结算")
            )
        )

        val ada1 = ProjectActionAdapter(
            arrayListOf(
                Pair(R.mipmap.action_icon_1, "变更时间"),
                Pair(R.mipmap.action_icon_2, "发起量房"),
                Pair(R.mipmap.action_icon_3, "施工动态"),
                Pair(R.mipmap.action_icon_4, "指派"),
                Pair(R.mipmap.action_icon_5, "整改"),
                Pair(R.mipmap.action_icon_6, "施工进度"),
                Pair(R.mipmap.action_icon_7, "提醒客户"),
                Pair(R.mipmap.action_icon_8, "验收")
            )
        )
        ada1.setOnItemClickListener { adapter, view, position ->

            when (position) {
                0 -> {
                }
                1 -> {
                }
                2 -> {
                }
                3 -> {
                }
                4 -> {
                    startActivity<AbarbeitungActivity>()
                }
                5 -> {
                }
                6 -> {
                }
                7 -> {
                    startActivity<VerifyActivity>()
                }

            }

        }
        actionRV.adapter = ada1


        serveRV.adapter = ProjectServeAdapter(arrayListOf("", "", "", "", "", "", "", ""))
        dataRV.adapter = WaitThingAdapter(arrayListOf("", "", "", "", "", "", "", "", "", ""))
        workerRV.adapter = WorkerAdapter(arrayListOf("", "", "", "", "", ""))
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
