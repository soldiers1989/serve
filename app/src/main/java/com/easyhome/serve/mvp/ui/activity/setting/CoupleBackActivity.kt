package com.easyhome.serve.mvp.ui.activity.setting

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerCoupleBackComponent
import com.easyhome.serve.di.module.CoupleBackModule
import com.easyhome.serve.mvp.contract.setting.CoupleBackContract
import com.easyhome.serve.mvp.presenter.setting.CoupleBackPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.PhotoAdapter
import com.easyhome.serve.mvp.ui.adapter.Tag2Adapter
import com.easyhome.serve.mvp.ui.adapter.TagAdapter
import kotlinx.android.synthetic.main.activity_couple_back.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 意见反馈
 */
class CoupleBackActivity : JRBaseActivity<CoupleBackPresenter>(), CoupleBackContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerCoupleBackComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .coupleBackModule(CoupleBackModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_couple_back //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        tvPageTitle.text = "意见反馈"
        ivPageBack.singleClick { killMyself() }
        tagRV.adapter = Tag2Adapter(arrayListOf("", "", ""))
        photoRV.adapter = PhotoAdapter(arrayListOf(""))
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
