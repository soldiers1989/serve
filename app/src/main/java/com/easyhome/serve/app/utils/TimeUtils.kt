@file:Suppress("UNREACHABLE_CODE")

package com.easyhome.serve.app.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
object TimeUtils {

    fun long2String(time: Long): String {
        var sec = time.toInt() / 1000
        val min = sec / 60
        sec %= 60
        return if (min < 10) if (sec < 10) "0$min:0$sec" else "0$min:$sec" else if (sec < 10) min.toString() + ":0" + sec else min.toString() + ":" + sec
    }

    fun getCurrentTime(): String {
        val sdf = SimpleDateFormat("yyyyMMddHHmmss")
        return sdf.format(java.lang.Long.valueOf(System.currentTimeMillis()))
    }

    fun getCuTime(time: Long?): String {
        val sdf = SimpleDateFormat("yyyy.MM.dd")
        return sdf.format(time)
    }
}