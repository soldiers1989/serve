package com.easyhome.serve.mvp.ui.activity.user

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerDeliveryAddressComponent
import com.easyhome.serve.di.module.DeliveryAddressModule
import com.easyhome.serve.mvp.contract.user.DeliveryAddressContract
import com.easyhome.serve.mvp.presenter.user.DeliveryAddressPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.mvp.ui.adapter.DeliveryAddressAdapter
import kotlinx.android.synthetic.main.activity_delivery_address.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 送货地址
 */
class DeliveryAddressActivity : JRBaseActivity<DeliveryAddressPresenter>(), DeliveryAddressContract.View {
    lateinit var adapter: DeliveryAddressAdapter
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerDeliveryAddressComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .deliveryAddressModule(DeliveryAddressModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_delivery_address //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        tvPageTitle.text = "送货地址"
        adapter = DeliveryAddressAdapter(
            arrayListOf(
                DeliveryAddressAdapter.Info("张三", "13611111111", "北京北京北京", true),
                DeliveryAddressAdapter.Info("张三", "13611111111", "北京北京北京")
            )
        )
        /*deliveryAddressRV.addItemDecoration(
            RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 10, getResources().getColor(R.color.colorD8D)
            )
        );*/
        deliveryAddressRV.adapter = adapter

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
