package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle
import android.view.View

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerMaterialsOrderInfoComponent
import com.easyhome.serve.di.module.MaterialsOrderInfoModule
import com.easyhome.serve.mvp.contract.project.MaterialsOrderInfoContract
import com.easyhome.serve.mvp.presenter.project.MaterialsOrderInfoPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.MaterialsOrder2Adapter
import com.easyhome.serve.mvp.ui.adapter.PInfoAdapter
import com.easyhome.serve.mvp.ui.adapter.PhotoAdapter
import kotlinx.android.synthetic.main.activity_materials_order_info.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import kotlin.math.PI


/**
 * 订单详情
 */
class MaterialsOrderInfoActivity : JRBaseActivity<MaterialsOrderInfoPresenter>(), MaterialsOrderInfoContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMaterialsOrderInfoComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .materialsOrderInfoModule(MaterialsOrderInfoModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_materials_order_info //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "订单详情"
        tvPageRight.text = "提醒发货"
        tvPageRight.visibility = View.VISIBLE
        tvPageRight.setBackgroundResource(R.drawable.white_side)
        tvPageRight.singleClick {
            //startActivity<EditCommodityInfoActivity>()
        }
        consumer.adapter = PInfoAdapter(
            arrayListOf(
                "测量日期：2019-07-21",
                "是否复尺：是",
                "复尺日期：2019-07-29",
                "送货日期：2019-08-12",
                "送货人：张哈哈",
                "送货人电话：13245645678",
                "安装日期：2019-01-01"
            )
        )
        measure.adapter = PhotoAdapter(arrayListOf("", ""))
        val commodityAdapter = MaterialsOrder2Adapter(arrayListOf(R.mipmap.test_icon_4, R.mipmap.test_icon_4))
        commodityAdapter.setOnItemChildClickListener { adapter, view, position ->

            when (view.id) {
                R.id.edit -> {
                    startActivity<EditMaterialsInfoActivity>()
                }
            }

        }
        commodity.adapter = commodityAdapter
        order.adapter = PInfoAdapter(
            arrayListOf(
                " 订单编号：11111",
                "销售合同号：1111",
                "品牌：欧派",
                "供应商：北京某某欧派销售有限公司",
                "交易时间：2019-07-11",
                "交易门店：111",
                "订单类型：套餐",
                "订单金额：99999元"
            )
        )
        afterSaleRV.adapter = PInfoAdapter(
            arrayListOf(
                "保修五年",
                "售后电话：010-6769789"
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
