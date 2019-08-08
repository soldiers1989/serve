@file:Suppress("DEPRECATION")

package com.easyhome.serve.app.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.os.CountDownTimer
import android.widget.Button
import com.easyhome.serve.R

/**
 * @Time : 2018/9/6 no 17:41
 * @USER : vvguoliang
 * @File : TimeCountButtonUtil.java
 * @Software: Android Studio
 *code is far away from bugs with the god animal protecting
 *   I love animals. They taste delicious.
 * ***┏┓   ┏ ┓
 * **┏┛┻━━━┛ ┻┓
 * **┃        ┃
 * **┃ ┳┛  ┗┳ ┃
 * **┃    ┻   ┃
 * **┗━┓    ┏━┛
 * ****┃    ┗━━━┓
 * ****┃ 神兽保佑 ┣┓
 * ****┃ 永无BUG！┏┛
 * ****┗┓┓┏━┳┓┏┛┏┛
 * ******┃┫┫  ┃┫┫
 * ******┗┻┛  ┗┻┛
 * 倒计时工具 Button
 */
class TimeCountButtonUtil// 在这个构造方法里需要传入三个参数，一个是Activity，一个是总的时间millisInFuture，一个是countDownInterval，然后就是你在哪个按钮上做这个是，就把这个按钮传过来就可以了
(private val mActivity: Activity, millisInFuture: Long, countDownInterval: Long, private val txt: Button//按钮
) : CountDownTimer(millisInFuture, countDownInterval) {


    @SuppressLint("NewApi", "SetTextI18n")
    override fun onTick(millisUntilFinished: Long) {
        txt.isClickable = false//设置不能点击
        txt.text = "获取(${millisUntilFinished / 1000})s"//设置倒计时时间
        // txt.setBackground(mActivity.getResources().getDrawable(R.drawable.edittext_background_code));

        //        Spannable span = new SpannableString(txt.getText().toString());//获取按钮的文字
        //        span.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//讲倒计时时间显示为红色tim
        //        txt.setText(span);
        txt.setTextColor(mActivity.resources.getColor(R.color.white))

    }

    @SuppressLint("NewApi")
    override fun onFinish() {
        txt.text = "重新发送"
        txt.isClickable = true//重新获得点击
        txt.setTextColor(mActivity.resources.getColor(R.color.white))
        //txt.setBackground(mActivity.getResources().getDrawable(R.drawable.btn_bgred));//还原背景色

    }
}