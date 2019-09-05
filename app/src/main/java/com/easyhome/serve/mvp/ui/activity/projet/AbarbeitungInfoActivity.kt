package com.easyhome.serve.mvp.ui.activity.projet

import android.content.Intent
import android.os.Bundle
import android.text.Html

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerAbarbeitungInfoComponent
import com.easyhome.serve.di.module.AbarbeitungInfoModule
import com.easyhome.serve.mvp.contract.projet.AbarbeitungInfoContract
import com.easyhome.serve.mvp.presenter.projet.AbarbeitungInfoPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.AddAbarbeitungAdapter
import com.easyhome.serve.mvp.ui.adapter.PInfoAdapter
import com.easyhome.serve.mvp.ui.adapter.PhotoAdapter
import kotlinx.android.synthetic.main.activity_abarbeitung_info.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 整改详情
 */
class AbarbeitungInfoActivity : JRBaseActivity<AbarbeitungInfoPresenter>(), AbarbeitungInfoContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerAbarbeitungInfoComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .abarbeitungInfoModule(AbarbeitungInfoModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_abarbeitung_info //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        tvPageTitle.text = "整改"
        ivPageBack.singleClick { killMyself() }
        submit.text="作废"
        infoRV.adapter = PInfoAdapter(arrayListOf("", "", "", "", ""))
        abarbeitungRV.adapter = AddAbarbeitungAdapter(arrayListOf("", "", ""))
        photoRV.adapter = PhotoAdapter(arrayListOf("", "", "", "", ""))
        total.setText(Html.fromHtml("合计:<font color='#3669F8'>罚款999元 扣10分</font>"))
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
