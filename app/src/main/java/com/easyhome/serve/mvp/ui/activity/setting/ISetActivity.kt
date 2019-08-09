package com.easyhome.serve.mvp.ui.activity.setting

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerISetComponent
import com.easyhome.serve.di.module.ISetModule
import com.easyhome.serve.mvp.contract.setting.ISetContract
import com.easyhome.serve.mvp.presenter.setting.ISetPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.SettingAdapter
import kotlinx.android.synthetic.main.activity_iset.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity


/**
 * 设置
 * }
 */
class ISetActivity : JRBaseActivity<ISetPresenter>(), ISetContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerISetComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .iSetModule(ISetModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_iset //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        tvPageTitle.text = "设置"
        ivPageBack.singleClick { killMyself() }
        var adapter = SettingAdapter(arrayListOf("修改密码", "清理缓存"))
        adapter.setOnItemClickListener { adapter, view, position ->

            when (position) {
                0 -> {
                    startActivity<NewPasswordActivity>()
                }
                1 -> {
                }
            }

        }
        settingRV.adapter = adapter
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
