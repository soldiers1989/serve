package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerEditCommodityInfoComponent
import com.easyhome.serve.di.module.EditCommodityInfoModule
import com.easyhome.serve.mvp.contract.project.EditCommodityInfoContract
import com.easyhome.serve.mvp.presenter.project.EditCommodityInfoPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 编辑商品信息
 */
class EditCommodityInfoActivity : JRBaseActivity<EditCommodityInfoPresenter>(), EditCommodityInfoContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerEditCommodityInfoComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .editCommodityInfoModule(EditCommodityInfoModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_edit_commodity_info //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "商品信息"
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
