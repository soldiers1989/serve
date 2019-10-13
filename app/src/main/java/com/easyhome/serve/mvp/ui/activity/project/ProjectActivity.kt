package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle
import android.text.Html
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
import com.easyhome.serve.app.extension.openH5ForUrl
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
        tagCL.singleClick {
            startActivity<CustomerTagActivity>()
        }
        editUserData.singleClick {
            startActivity<EditUserDataActivity>()
        }
        val pAdapter = ProjectProgressAdapter(
            arrayListOf(
                MPair(R.mipmap.progress_icon_2, "量房"),
                MPair(R.mipmap.progress_icon_2, "预交底"),
                MPair(R.mipmap.progress_icon_3, "待开工"),
                MPair(R.mipmap.progress_icon_1, "施工中\n基础"),
                MPair(R.mipmap.progress_icon_1, "结算")
            )
        )
        pAdapter.setOnItemChildClickListener { adapter, view, position ->

            when (position) {
                0 -> {
                    openH5ForUrl("http://111.231.114.131/guochongyang/%E6%9C%8D%E5%8A%A1%E7%AB%AF-moblie/m_%20room_node.html")
                }
            }

        }
        progressRV.adapter = pAdapter


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
                    startActivity<ChangeTimeActivity>("type" to 0)
                }
                1 -> {
                    //发起量房
                    startActivity<ChangeTimeActivity>("type" to 1)
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
                    openH5ForUrl("http://111.231.114.131/guochongyang/%E6%9C%8D%E5%8A%A1%E7%AB%AF-moblie/m_bottom_node.html")
                }
                9 -> {
                    //完成量房
                    //   openH5ForUrl("http://111.231.114.131/guochongyang/%E6%9C%8D%E5%8A%A1%E7%AB%AF-moblie/m_%20room_node.html")
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
        workerRV.adapter =
                WorkerAdapter(arrayListOf(MPair("水电工：", "张三、李四、王五"), MPair("木工：", "小张"), MPair("油漆工：", "张思")))

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



        info1.text = Html.fromHtml("客户编号：<font color='#777777'>11111</font>")
        info12.text = Html.fromHtml("客户来源：<font color='#777777'>APP咨询</font>")
        info2.text = Html.fromHtml("客户姓名：<font color='#777777'>周老大</font>")
        info3.text = Html.fromHtml("房屋熟悉：<font color='#777777'>住宅</font>")
        info4.text = Html.fromHtml("户型：<font color='#777777'>三室一厅</font>")
        info9.text = Html.fromHtml("建筑面积：<font color='#777777'>100m²</font>")
        info11.text = Html.fromHtml("报价级别：<font color='#777777'>套餐-999</font>")
        info10.text = Html.fromHtml("套内建筑面积：<font color='#777777'>90m²</font>")
        info5.text = Html.fromHtml("装修类型：<font color='#777777'>全屋装</font>")
        info6.text = Html.fromHtml("房屋类型：<font color='#777777'>新房</font>")


        source.text = Html.fromHtml("预约来源：<font color='#777777'>张三案例</font>")
        scope.text = Html.fromHtml("预算范围：<font color='#777777'>500-1000元</font>")
        caseInfo.text = Html.fromHtml("案例详情：<font color='#777777'>https://org.modao.cc/app/992df6393b4da053fd</font>")
        comment.text = Html.fromHtml("预约备注： <font color='#777777'>想要一个女设计师，擅长中式风格设计，施工队 要山东的 </font>")


        contract2.text = Html.fromHtml("合同开工日期： <font color='#777777'>2019-05-01</font>")
        contract3.text = Html.fromHtml("合同竣工日期： <font color='#777777'>2019-06-01</font>")
        contract4.text = Html.fromHtml("施工状态： <font color='#777777'>已延期</font>")
        contract8.text = Html.fromHtml("今日进度： <font color='#777777'>水电施工、木门测量</font>")
        contract6.text = Html.fromHtml("竣工日期： <font color='#777777'>2019-06-01</font>")
        contract7.text = Html.fromHtml("开工日期： <font color='#777777'>2019-05-01</font>")

        md.text = Html.fromHtml("门店： <font color='#777777'>丽泽桥店</font>")


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
