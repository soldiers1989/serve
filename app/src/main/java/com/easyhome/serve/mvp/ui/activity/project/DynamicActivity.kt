package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle
import android.view.View

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerDynamicComponent
import com.easyhome.serve.di.module.DynamicModule
import com.easyhome.serve.mvp.contract.project.DynamicContract
import com.easyhome.serve.mvp.presenter.project.DynamicPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.DynamicAdapter
import kotlinx.android.synthetic.main.activity_dynamic.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity


/**
 * 施工动态
 */
class DynamicActivity : JRBaseActivity<DynamicPresenter>(), DynamicContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerDynamicComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .dynamicModule(DynamicModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_dynamic //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "施工动态"
        ivPageRight.visibility = View.VISIBLE
        ivPageRight.setImageResource(R.mipmap.camera_icon)
        ivPageRight.singleClick {
            startActivity<AddDynamicActivity>()
        }
        dynamic.adapter = DynamicAdapter(arrayListOf("", "", ""))
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
