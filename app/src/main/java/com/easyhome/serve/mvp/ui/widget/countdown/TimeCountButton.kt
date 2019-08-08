package com.easyhome.serve.mvp.ui.widget.countdown

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.widget.Button
import com.easyhome.serve.R


@SuppressLint("NewApi")
class TimeCountButton(private val context: Context, millisInFuture: Long, private val button: Button) : CountDownTimer(millisInFuture, TIME_TASCK.toLong()) {

    override fun onFinish() {// 计时完毕
        button.setTextColor(context.resources.getColor(R.color.color222))
        button.setBackgroundColor(context.resources.getColor(R.color.colorD3D))
        button.text = context.getString(R.string.lg_verification_code)
        button.isClickable = true
    }

    @SuppressLint("StringFormatInvalid", "SetTextI18n")
    override fun onTick(millisUntilFinished: Long) {// 计时过程
        button.setTextColor(context.resources.getColor(R.color.white))
        button.isClickable = false//防止重复点击
        button.setBackgroundColor(context.resources.getColor(R.color.color50A))
        button.text = "${millisUntilFinished / TIME_TASCK}s"
    }

    companion object {
        private val TIME_TASCK = 1000
    }
}
