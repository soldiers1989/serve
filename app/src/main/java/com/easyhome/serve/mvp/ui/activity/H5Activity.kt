package com.easyhome.serve.mvp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerH5Component
import com.easyhome.serve.di.module.H5Module
import com.easyhome.serve.mvp.contract.H5Contract
import com.easyhome.serve.mvp.presenter.H5Presenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.widget.ButtomDialogView
import com.easyhome.serve.mvp.ui.widget.X5WebView
import com.tencent.smtt.export.external.interfaces.SslError
import com.tencent.smtt.export.external.interfaces.SslErrorHandler
import com.tencent.smtt.sdk.*
import kotlinx.android.synthetic.main.activity_h5.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 *h5
 */
class H5Activity : JRBaseActivity<H5Presenter>(), H5Contract.View {
    override fun getMyself(): BaseActivity<*> = this
    private var x5WebView: X5WebView? = null

    private var pageUrl: String? = null
    private var title: String = ""


    lateinit var dialog: ButtomDialogView

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerH5Component //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .h5Module(H5Module(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_h5 //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    @SuppressLint("NewApi")
    override fun initData(savedInstanceState: Bundle?) {


        if (!QbSdk.isTbsCoreInited()) {
            QbSdk.preInit(this, null)  // 设置X5初始化完成的回调接口
        }
        pageUrl = intent.getStringExtra("pageUrl")
        title = intent.getStringExtra("title")
        tvPageTitle.text = title
        ivPageBack.singleClick {
            killMyself()
        }

        x5WebView = X5WebView(this)
        h5Content.addView(x5WebView)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        x5WebView!!.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE)
        x5WebView!!.setWebViewClient(getWebViewClient())
        x5WebView!!.removeCookie()
        x5WebView?.loadUrl(pageUrl)
        x5WebView!!.visibility = View.VISIBLE

    }

    override fun onResume() {
        super.onResume()
        x5WebView!!.MyTexeView(object : X5WebView.MyTexeView {
            override fun Mytext(text: String) {
                tvPageTitle.visibility = View.VISIBLE
                tvPageTitle.text = text
            }
        })
    }


    private fun getWebViewClient(): WebViewClient {
        return object : WebViewClient() {
            override fun onPageFinished(p0: WebView?, p1: String?) {
                val cookieManager = CookieManager.getInstance()
                cookieManager.setAcceptCookie(true)
                val endCookie = cookieManager.getCookie(p1)
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    CookieSyncManager.getInstance().sync()//同步cookie
                } else {
                    CookieManager.getInstance().flush()
                }
                super.onPageFinished(p0, p1)

                title = p0!!.title
                title = if (!TextUtils.isEmpty(title)) {
                    if (title != null && title.length > X5WebView.MAX_LENGTH) {
                        title.subSequence(0, X5WebView.MAX_LENGTH).toString() + "..."
                    } else {
                        title!!
                    }
                } else {
                    this@H5Activity.title
                }
                tvPageTitle.visibility = View.VISIBLE
                tvPageTitle.text = title
            }

            override fun shouldOverrideUrlLoading(p0: WebView?, p1: String?): Boolean {
                return false
            }

            override fun onLoadResource(p0: WebView?, p1: String?) {
                super.onLoadResource(p0, p1)


            }

            override fun onReceivedSslError(p0: WebView?, p1: SslErrorHandler?, p2: SslError?) {
                super.onReceivedSslError(p0, p1, p2)
                p1!!.proceed()
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            killMyself()
            false
        } else {
            super.onKeyDown(keyCode, event)
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
        if (x5WebView!!.canGoBack()) {
            x5WebView!!.goBack()
        } else {
            x5WebView!!.removeAllViews()
            x5WebView!!.destroy()
            h5Content.removeAllViews()
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        x5WebView = null
    }
}
