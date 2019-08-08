@file:Suppress("DEPRECATION")

package com.easyhome.serve.mvp.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import com.easyhome.serve.R
import com.tencent.smtt.sdk.*


class X5WebView : WebView {

    internal var progressBar: ProgressBar? = null
    private lateinit var tvTitle: String
    private var imageView: ImageView? = null

    private lateinit var textView: MyTexeView

    private lateinit var openFiel: H5openFiel

    private val chromeClient = object : WebChromeClient() {
        @SuppressLint("SetTextI18n")
        override fun onReceivedTitle(view: WebView?, title: String?) {
            if (TextUtils.isEmpty(title)) {
                return
            }
            tvTitle = if (title != null && title.length > MAX_LENGTH) {
                title.subSequence(0, MAX_LENGTH).toString() + "..."
            } else {
                title!!
            }
            textView.Mytext(tvTitle)
        }

        //监听进度
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            progressBar!!.progress = newProgress
            if (progressBar != null && newProgress != 100) {
                //Webview加载没有完成 就显示我们自定义的加载图
                progressBar!!.visibility = View.VISIBLE

            } else if (progressBar != null) {
                //Webview加载完成 就隐藏进度条,显示Webview
                progressBar!!.visibility = View.GONE
                imageView!!.visibility = View.GONE
            }
        }

        override fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String, capture: String) {
            super.openFileChooser(uploadMsg, acceptType, capture)
            openFiel.openFileChooserImpl(uploadMsg)
        }

        override fun onShowFileChooser(mWebView: WebView, filePathCallback: ValueCallback<Array<Uri>>, fileChooserParams: WebChromeClient.FileChooserParams): Boolean {
            openFiel.openShowFileChooserImpl(filePathCallback)
            return true
        }
    }


    internal var downloadListener: DownloadListener = DownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }


    constructor(context: Context) : super(context) { initUI() }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { initUI() }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) { initUI() }

    fun setShowProgress(showProgress: Boolean) {
        if (showProgress) {
            progressBar!!.visibility = View.VISIBLE
        } else {
            progressBar!!.visibility = View.GONE
        }
    }

    private fun initUI() {
        //        getX5WebViewExtension().setScrollBarFadingEnabled(true);
        isHorizontalScrollBarEnabled = false//水平不显示小方块
        isVerticalScrollBarEnabled = false //垂直不显示小方块

        //      setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);//滚动条在WebView内侧显示
        //      setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//滚动条在WebView外侧显示
        progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
        progressBar!!.max = 100
        progressBar!!.progressDrawable = this.resources.getDrawable(R.drawable.progress_bar_states)

        addView(progressBar, FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 6))
        imageView = ImageView(context)
        imageView!!.scaleType = ImageView.ScaleType.CENTER
        //      加载图 根据自己的需求去集成使用
        imageView!!.setImageResource(R.mipmap.ic_launcher)
        imageView!!.visibility = View.VISIBLE
        addView(imageView, FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

        initWebViewSettings()
    }

    @SuppressLint("SetJavaScriptEnabled")
//   基本的WebViewSetting
    fun initWebViewSettings() {
        setBackgroundColor(resources.getColor(android.R.color.white))
        setWebChromeClient(chromeClient)
        setDownloadListener(downloadListener)
        isClickable = true
        setOnTouchListener { v, event -> false }
        val webSetting = settings
        webSetting.javaScriptEnabled = true
        webSetting.builtInZoomControls = true
        webSetting.javaScriptCanOpenWindowsAutomatically = true
        webSetting.domStorageEnabled = true
        webSetting.allowFileAccess = true
        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webSetting.setSupportZoom(true)
        webSetting.useWideViewPort = true
        webSetting.setSupportMultipleWindows(true)
        webSetting.setAppCacheEnabled(true)
        webSetting.setGeolocationEnabled(true)
        webSetting.setAppCacheMaxSize(java.lang.Long.MAX_VALUE)
        webSetting.pluginState = WebSettings.PluginState.ON_DEMAND
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH)
        //android 默认是可以打开_bank的，是因为它默认设置了WebSettings.setSupportMultipleWindows(false)
        //在false状态下，_bank也会在当前页面打开……
        //而x5浏览器，默认开启了WebSettings.setSupportMultipleWindows(true)，
        // 所以打不开……主动设置成false就可以打开了
        //需要支持多窗体还需要重写WebChromeClient.onCreateWindow
        webSetting.setSupportMultipleWindows(false)
        //        webSetting.setCacheMode(WebSettings.LOAD_NORMAL);
        //        getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && this.canGoBack()) {
            this.goBack() // goBack()表示返回WebView的上一页面
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun syncCookie(url: String, context: Context) {
        CookieSyncManager.createInstance(context)
        if (!TextUtils.isEmpty(url)) {
            val cookieManager = CookieManager.getInstance()
            cookieManager.setAcceptCookie(true)
            cookieManager.removeSessionCookie()// 移除
            cookieManager.removeAllCookie()

//            //这里的拼接方式是伪代码
//            val split = cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//            for (string in split) {
//                //为url设置cookie
//                // ajax方式下  cookie后面的分号会丢失
//                cookieManager.setCookie(url, string)
//            }
            val newCookie = cookieManager.getCookie(url)
            //sdk21之后CookieSyncManager被抛弃了，换成了CookieManager来进行管理。
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                CookieSyncManager.getInstance().sync()//同步cookie
            } else {
                CookieManager.getInstance().flush()
            }
        } else {

        }

    }

    //删除Cookie
    fun removeCookie() {
        CookieSyncManager.createInstance(context)
        val cookieManager = CookieManager.getInstance()
        cookieManager.removeSessionCookie()
        cookieManager.removeAllCookie()
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync()
        } else {
            CookieManager.getInstance().flush()
        }

    }

    fun getDoMain(url: String): String {
        var domain = ""
        val start = url.indexOf(".")
        if (start >= 0) {
            val end = url.indexOf("/", start)
            if (end < 0) {
                domain = url.substring(start)
            } else {
                domain = url.substring(start, end)
            }
        }
        return domain
    }

    companion object {
        val MAX_LENGTH = 8
    }

    fun MyTexeView(textView: MyTexeView) {
        this.textView = textView
    }

    interface MyTexeView {
        fun Mytext(text: String)
    }

    fun H5openFiel(openFiel: H5openFiel) {
        this.openFiel = openFiel
    }

    interface H5openFiel {
        fun openFileChooserImpl(uploadMsg: ValueCallback<Uri>)
        fun openShowFileChooserImpl(uploadMsg: ValueCallback<Array<Uri>>)
    }

}
