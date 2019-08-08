package com.easyhome.serve.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerConfirmComponent
import com.easyhome.serve.di.module.ConfirmModule
import com.easyhome.serve.mvp.contract.ConfirmContract
import com.easyhome.serve.mvp.presenter.ConfirmPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import kotlinx.android.synthetic.main.activity_confirm.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 确认修改密码
 */
class ConfirmActivity : JRBaseActivity<ConfirmPresenter>(), ConfirmContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerConfirmComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .confirmModule(ConfirmModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_confirm //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.setImageResource(R.mipmap.back)
        ivPageBack.singleClick { finish() }

        accountTV.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                println("输入的是${p0.toString()}")
                if (p0.toString().length > 0) {
                    accountClean.visibility = View.VISIBLE
                } else {
                    accountClean.visibility = View.GONE
                }
            }

        })
        passwordED.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                println("输入的是${p0.toString()}")
                if (p0.toString().length > 0) {
                    passwordClean.visibility = View.VISIBLE
                } else {
                    passwordClean.visibility = View.GONE
                }

                if (p0.toString().length > 0 && accountTV.text.toString().trim().length > 0) {
                    confirmB.isChecked = true
                    confirmB.isEnabled = true
                } else {
                    confirmB.isChecked = false
                    confirmB.isEnabled = false
                }
            }

        })
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
