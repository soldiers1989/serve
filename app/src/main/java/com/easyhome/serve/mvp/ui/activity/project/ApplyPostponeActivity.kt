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
import com.easyhome.serve.mvp.model.entity.MPair
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

        val adapter = ANodeAdapter(
            arrayListOf(
                MPair(true, "全选"),
                MPair(true, "水电施工    2019-07-01 ~ 2019-07-07"),
                MPair(false, "水电施工    2019-07-01 ~ 2019-07-07"),
                MPair(false, "水电施工    2019-07-01 ~ 2019-07-07")
            )
        )
        adapter.setOnItemChildClickListener { adapter, view, position ->
            val data = adapter.data as List<MPair<Boolean,String>>
            when (position) {

                0 -> {
                    val b = !data[0].first
                    data.forEachIndexed { index, mPair ->
                        mPair.first = b
                    }

                }
                else -> {
                    data[position].first = !data[position].first
                }

            }
            adapter.notifyDataSetChanged()

        }
        nodeRV.adapter = adapter
        photoRV.adapter = PhotoAdapter(arrayListOf(""))
        poscontent1.text = Html.fromHtml("延期申请编号：<font color='#777777'>1234</font>")
        poscontent2.text = Html.fromHtml("计划施工日期：<font color='#777777'>2019-05-12  ~  2019-05-22</font>")
        poscontent4.text = Html.fromHtml("延期开始日期：<font color='#777777'>点击设置日期</font>")


        content1.text = Html.fromHtml("客户编号：<font color='#777777'>1234</font>")
        content2.text = Html.fromHtml("客户姓名：<font color='#777777'>李阳</font>")
        content3.text = Html.fromHtml("工期：<font color='#777777'>110天</font>")
        content5.text = Html.fromHtml("建筑面积：<font color='#777777'>100.98m²</font>")
        content6.text = Html.fromHtml("项目地址：<font color='#777777'>北京市西城区车公庄大街9号院锦官苑院1号楼</font>")

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
