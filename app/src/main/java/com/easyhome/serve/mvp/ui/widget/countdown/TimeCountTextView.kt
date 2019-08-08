package com.easyhome.serve.mvp.ui.widget.countdown

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.widget.TextView
import com.easyhome.serve.R


@SuppressLint("NewApi")
class TimeCountTextView(private val context: Context, millisInFuture: Long, private val textView: TextView) : CountDownTimer(millisInFuture, TIME_TASCK.toLong()) {

    override fun onFinish() {// 计时完毕
        textView.setTextColor(context.resources.getColor(R.color.color222))
//        textView.setBackgroundColor(context.getColor(R.color.colorD3D))
        textView.text = context.getString(R.string.lg_verification_code)
        textView.isClickable = true
    }

    @SuppressLint("StringFormatInvalid", "SetTextI18n")
    override fun onTick(millisUntilFinished: Long) {// 计时过程
        textView.setTextColor(context.resources.getColor(R.color.color222))
        textView.isClickable = false//防止重复点击
//        textView.setBackgroundColor(context.getColor(R.color.color50A))
        textView.text = "等待${millisUntilFinished / TIME_TASCK}秒"
    }

    companion object {
        private val TIME_TASCK = 1000
    }
}