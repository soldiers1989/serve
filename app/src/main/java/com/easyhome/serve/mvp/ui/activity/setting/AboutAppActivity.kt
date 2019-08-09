package com.easyhome.serve.mvp.ui.activity.setting

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerAboutAppComponent
import com.easyhome.serve.di.module.AboutAppModule
import com.easyhome.serve.mvp.contract.setting.AboutAppContract
import com.easyhome.serve.mvp.presenter.setting.AboutAppPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.mvp.ui.adapter.SettingOptionAdapter
import kotlinx.android.synthetic.main.activity_about_app.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 如果没presenter
 * 你可以这样写
 *
 * @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
class AboutAppActivity : JRBaseActivity<AboutAppPresenter>(), AboutAppContract.View {
    override fun getMyself(): BaseActivity<*> = this


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerAboutAppComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .aboutAppModule(AboutAppModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_about_app //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {

        tvPageTitle.text = "关于APP"
        optionRV.adapter = SettingOptionAdapter(arrayListOf("", ""))
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
