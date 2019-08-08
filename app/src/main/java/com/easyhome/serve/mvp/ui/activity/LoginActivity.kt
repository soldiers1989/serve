package com.easyhome.serve.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerLoginComponent
import com.easyhome.serve.di.module.LoginModule
import com.easyhome.serve.mvp.contract.LoginContract
import com.easyhome.serve.mvp.presenter.LoginPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.extension.singleClick
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity


/**
 * 登录
 */
class LoginActivity : BaseActivity<LoginPresenter>(), LoginContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun showNetError() {
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .loginModule(LoginModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {


        accountTV.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                println("输入的是${p0.toString()}")
                if (p0.toString().length > 0) {
                    accountClean.visibility = View.VISIBLE
                    if (passwordED.text.trim().length > 0) {//判断是否输入账号，如果如果输入则可点击
                        loginB.isChecked = true
                        loginB.isEnabled = true
                    }
                } else {
                    accountClean.visibility = View.GONE
                    loginB.isChecked = false
                    loginB.isEnabled = false
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
                    if (accountTV.text.trim().length > 0) {//判断是否输入账号，如果如果输入则可点击
                        loginB.isChecked = true
                        loginB.isEnabled = true
                    }
                } else {
                    passwordClean.visibility = View.GONE
                    loginB.isChecked = false
                    loginB.isEnabled = false
                }
            }

        })
        accountClean.singleClick {
            accountTV.text.clear()
        }
        passwordClean.singleClick {
            passwordED.text.clear()
        }
        loginB.singleClick {
              startActivity<MainActivity>()
           /* mPresenter!!.login(accountTV.text.toString().trim(), passwordED.text.toString()) {
                startActivity<Main2Activity>()
            }*/
        }

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
