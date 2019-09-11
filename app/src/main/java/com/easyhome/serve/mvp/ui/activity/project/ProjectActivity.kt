package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle
import android.view.View

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
import com.easyhome.serve.mvp.model.entity.MPair
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
        customerTag.singleClick {
            startActivity<CustomerTagActivity>()
        }
        editUserData.singleClick {
            startActivity<EditUserDataActivity>()
        }
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
                Pair(R.mipmap.action_icon_8, "验收"),
                Pair(R.mipmap.action_icon_9, "发起预交底"),
                Pair(R.mipmap.action_icon_10, "完成量房"),
                Pair(R.mipmap.action_icon_11, "完成预交底"),
                Pair(R.mipmap.action_icon_12, "签约失败"),
                Pair(R.mipmap.action_icon_13, "主材信息"),
                Pair(R.mipmap.action_icon_14, "延期"),
                Pair(R.mipmap.action_icon_15, "工单"),
                Pair(R.mipmap.action_icon_16, "结算"),
                Pair(R.mipmap.action_icon_16, "取消签约失败"),
                Pair(R.mipmap.action_icon_16, "作废预交底")
            )
        )
        ada1.setOnItemClickListener { adapter, view, position ->

            when (position) {
                0 -> {
                    //变更时间
                    startActivity<ChangeTimeActivity>()
                }
                1 -> {
                    //发起量房
                }
                2 -> {
                    //施工动态
                    startActivity<DynamicActivity>()
                }
                3 -> {
                    //指派
                    //startActivity<AssignActivity>()
                    startActivity<AssignType2Activity>()
                }
                4 -> {
                    //整改
                    startActivity<AbarbeitungActivity>()
                }
                5 -> {
                    //施工进度
                    startActivity<WorkingPlanActivity>()
                }
                6 -> {
                    //提醒客户
                }
                7 -> {
                    //验收
                    startActivity<VerifyActivity>()
                }
                8 -> {
                    //发起预交底
                }
                9 -> {
                    //完成量房
                }
                10 -> {
                    //完成预交底
                }
                11 -> {
                    //签约失败
                }
                12 -> {
                    //主材信息
                    startActivity<MaterialsListActivity>()
                }
                13 -> {
                    //延期
                    startActivity<PostponeActivity>()
                }
                14 -> {
                    //工单
                    startActivity<WorkOrderActivity>()
                }
                15 -> {
                    //结算
                  //  startActivity<SettlementActivity>()
                }

            }

        }
        actionRV.adapter = ada1


        serveRV.adapter = ProjectServeAdapter(arrayListOf("客户顾问：", "装修管家：", "材料员：", "项目经理：", "设计师：", "工长："))
        dataRV.adapter = DataAdapter(arrayListOf("设计合同", "施工合同", "图纸", "报价单"))
        workerRV.adapter = WorkerAdapter(arrayListOf(MPair("水电工：", "张三、李四、王五"), MPair("木工：", "小张"), MPair("油漆工：", "张思")))

        swIV.singleClick {
            if (workerRV.visibility == View.VISIBLE) {
                workerRV.visibility = View.GONE
                swIV.setImageResource(R.mipmap.down_icon)
            } else {
                workerRV.visibility = View.VISIBLE
                swIV.setImageResource(R.mipmap.up_icon)
            }
        }

        itemRV.adapter = WorkingPlanItem1Adapter(
            arrayListOf(
                "项目编号：", "创建时间：", "量房时间：", "预交底时间：",
                "实际合同签约日期：", "设计合同金额：", "施工签约日期：",
                "施工合同金额：", "合同开工日期：", "合同竣工日期：", "结算日期："
            )
        )
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
