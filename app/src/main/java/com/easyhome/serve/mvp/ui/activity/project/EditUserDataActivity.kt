package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerEditUserDataComponent
import com.easyhome.serve.di.module.EditUserDataModule
import com.easyhome.serve.mvp.contract.project.EditUserDataContract
import com.easyhome.serve.mvp.presenter.project.EditUserDataPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import kotlinx.android.synthetic.main.activity_edit_user_data.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 编辑用户资料
 */
class EditUserDataActivity : JRBaseActivity<EditUserDataPresenter>(), EditUserDataContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerEditUserDataComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .editUserDataModule(EditUserDataModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_edit_user_data //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "客户基本信息"
        man.isChecked=true
        man.setOnCheckedChangeListener { compoundButton, b ->
            if(b) {
                woman.isChecked = false
            }
        }
        woman.setOnCheckedChangeListener { compoundButton, b ->
            if(b) {
                man.isChecked = false
            }
        }
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
