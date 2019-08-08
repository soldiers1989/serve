@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.easyhome.serve.app.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.signature.ObjectKey
import com.bumptech.glide.util.Util
import com.jess.arms.http.imageloader.ImageConfig
import com.jess.arms.utils.ArmsUtils
import com.easyhome.serve.app.imageloader.JRImageConfig
import com.easyhome.serve.R
import com.easyhome.serve.api.Api
import com.easyhome.serve.app.listener.SingleClickListener
import com.easyhome.serve.app.manager.UserInfoManager
import org.jetbrains.anko.toast
import java.util.*


/**
 * Created by 宗传
 * Time 2018/8/31  下午4:57
 *
 * View扩展类
 **/

fun View.singleClick(isCheckLogin: Boolean = false, listener: (View?) -> Unit) {
    setOnClickListener(SingleClickListener(isCheckLogin, listener))
}

fun View.singleClick(isCheckLogin: Boolean = false, t: Int, listener: (View?) -> Unit) {
    setOnClickListener(SingleClickListener(isCheckLogin, t, listener))
}

fun <T : ImageConfig> ImageView.loadImage(config: T) {
    ArmsUtils.obtainAppComponentFromContext(this.context)
            .imageLoader().loadImage(this.context, config)
}

fun ImageView.loadImage(url: String?) {
    loadImage(url.strToString(), 0)
}

fun ImageView.loadImage(url: String, placeholder: Int) {
    ArmsUtils.obtainAppComponentFromContext(this.context)
            .imageLoader().loadImage(this.context, JRImageConfig.Builder()
                    .errorPic(placeholder)
                    .url(url.strToString())
                    .placeholder(placeholder)
                    .imageView(this)
                    .build())
}

/**
 * 添加默认图片
 */
fun ImageView.loadImage(url: String, placeholder: Int, mipmap: Int) {
    ArmsUtils.obtainAppComponentFromContext(this.context)
            .imageLoader().loadImage(this.context, JRImageConfig.Builder()
                    .errorPic(mipmap)
                    .url(url.strToString())
                    .placeholder(placeholder)
                    .imageView(this)
                    .build())
}

fun ImageView.loadImage(url: String, placeholder: Int, isCircle: Boolean = false) {
    ArmsUtils.obtainAppComponentFromContext(this.context)
            .imageLoader().loadImage(this.context, JRImageConfig.Builder()
                    .url(url.strToString())
                    .errorPic(R.mipmap.ic_launcher)
                    .placeholder(placeholder)
                    .isCircle(isCircle)
                    .imageView(this)
                    .build())
}

fun TextView.setText(title: String, content: String) {
    val style: SpannableStringBuilder = SpannableStringBuilder(title + content)
    style.setSpan(ForegroundColorSpan(Color.parseColor("#222222")), 0, title.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
    style.setSpan(ForegroundColorSpan(Color.parseColor("#656565")), title.length, content.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
    this.text = style
}

fun EditText.inputMoney() {
    this.addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            var s = s
            // 限制最多能输入9位整数
            if (s.toString().contains(".")) {
                if (s.toString().indexOf(".") > 9) {
                    s = s.toString().subSequence(0, 9).toString() + s.toString().substring(s.toString().indexOf("."))
                    this@inputMoney.setText(s)
                    this@inputMoney.setSelection(9)
                }
            } else {
                if (s.toString().length > 9) {
                    s = s.toString().subSequence(0, 9)
                    this@inputMoney.setText(s)
                    this@inputMoney.setSelection(9)
                }
            }
            // 判断小数点后只能输入两位
            if (s.toString().contains(".")) {
                if (s.length - 1 - s.toString().indexOf(".") > 2) {
                    s = s.toString().subSequence(0,
                            s.toString().indexOf(".") + 3)
                    this@inputMoney.setText(s)
                    this@inputMoney.setSelection(s.length)
                }
            }
            //如果第一个数字为0，第二个不为点，就不允许输入
            if (s.toString().startsWith("0") && s.toString().trim { it <= ' ' }.length > 1) {
                if (s.toString().substring(1, 2) != ".") {
                    this@inputMoney.setText(s.subSequence(0, 1))
                    this@inputMoney.setSelection(1)
                    return
                }
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun afterTextChanged(s: Editable) {
            if (this@inputMoney.text.toString().trim() != "") {
                if (this@inputMoney.text.toString().trim().substring(0, 1) == ".") {
                    this@inputMoney.setText("0" + this@inputMoney.text.toString().trim())
                    this@inputMoney.setSelection(1)
                }
            }
        }
    })
}

/**
 * 软键盘弹出 隐藏
 */
fun EditText.showInputWindow(activity: Activity, isboolean: Boolean) {
    if (isboolean) {
        this.isFocusable = true
        this.isFocusableInTouchMode = true
        this.requestFocus()
        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    } else {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                val manager = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }, 1000)
    }
}

/**
 * 获取图形验证码
 */
fun getImageView(context: Activity, button: Button, imageView: ImageView) {
    if (Util.isOnMainThread()) {
        Glide.with(context)
                .asDrawable()
                .load("${Api.APP_LOGO_URl}/dynamic/addSub?uuid=${UserInfoManager.getInstance().uuid}")
                .apply(getRequest())
                .into(object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        button.visibility = View.INVISIBLE
                        button.isClickable = false
                        imageView.visibility = View.VISIBLE
                        imageView.isClickable = true
                        imageView.setImageDrawable(resource)
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        super.onLoadFailed(errorDrawable)
                        button.visibility = View.VISIBLE
                        button.isClickable = true
                        imageView.visibility = View.INVISIBLE
                        imageView.isClickable = false
                        context.toast("获取图形验证码失败")
                    }
                })
    }
}

fun getRequest(): RequestOptions {
    return RequestOptions().signature(ObjectKey(System.currentTimeMillis())).fitCenter()//.diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true)
}

@SuppressLint("NewApi")
        /**
         * 判断是否显示button背景颜色和是否点击
         */
fun getEdittext(context: Context, editText: EditText, button: Button) {
    if (editText.text.toString().isNotEmpty()) {
        button.isClickable = true
        button.setBackgroundColor(context.resources.getColor(R.color.color50A))
    } else {
        button.isClickable = false
        button.setBackgroundColor(context.resources.getColor(R.color.colorD3D))
    }
}

