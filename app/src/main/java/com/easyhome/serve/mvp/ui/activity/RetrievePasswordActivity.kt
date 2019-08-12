package com.easyhome.serve.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerRetrievePasswordComponent
import com.easyhome.serve.di.module.RetrievePasswordModule
import com.easyhome.serve.mvp.contract.RetrievePasswordContract
import com.easyhome.serve.mvp.presenter.RetrievePasswordPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import kotlinx.android.synthetic.main.activity_retrieve_password.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.textColor


/**
 * 找回密码
 */
class RetrievePasswordActivity : JRBaseActivity<RetrievePasswordPresenter>(), RetrievePasswordContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerRetrievePasswordComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .retrievePasswordModule(RetrievePasswordModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_retrieve_password //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        nextB.singleClick { startActivity<ConfirmActivity>() }
        ivPageBack.setImageResource(R.mipmap.back)
        ivPageBack.singleClick { finish() }
        nextB.singleClick {
            startActivity<ConfirmActivity>()
        }
        accountTV.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().length > 0) {
                    accountClean.visibility = View.VISIBLE

                } else {
                    accountClean.visibility = View.GONE
                }
                if (p0.toString().length == 11) {
                    smsB.isChecked = true
                    smsB.isEnabled = true
                }
            }

        })
        passwordED.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().length > 0) {
                    passwordClean.visibility = View.VISIBLE

                } else {
                    passwordClean.visibility = View.GONE
                }
                if (p0.toString().length == 4 && accountTV.text.toString().trim().length == 11) {
                    nextB.isChecked = true
                    nextB.isEnabled = true
                }else{
                    nextB.isChecked = false
                    nextB.isEnabled = false
                }
            }

        })

        accountClean.singleClick {
            accountTV.text.clear()
        }
        passwordClean.singleClick {
            passwordED.text.clear()
        }
        smsB.singleClick {
            smsB.backgroundResource = R.drawable.labels_bac_s
            smsB.textColor = R.color.color999
            smsB.text= "已发送(${60}s)"

            timer()
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

    fun timer() {
        var time: Int = 60
        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                //要做的事情
                --time
                smsB.text = "已发送(${time}s)"
                if (time > 0) {
                    handler.postDelayed(this, 1000)
                } else {
                    smsB.backgroundResource = R.drawable.login_rb_bac
                    smsB.textColor = R.color.white
                    smsB.text="获取手机验证码"
                }
            }
        }
        handler.postDelayed(runnable, 1000);
    }

    /* fun stopTimer(){
         handler.removeCallbacks(runnable);
     }*/
}
