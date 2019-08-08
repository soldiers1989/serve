package com.easyhome.serve.app

import android.content.Context
import android.net.ParseException
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.jess.arms.utils.ArmsUtils
import com.easyhome.serve.BuildConfig
import com.easyhome.serve.app.extension.strIsEmpty
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener
import org.json.JSONException
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * ================================================
 * 展示 [ResponseErrorListener] 的用法
 *
 *
 * Created by MVPArmsTemplate
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
class ResponseErrorListenerImpl : ResponseErrorListener {

    override fun handleResponseError(context: Context, t: Throwable) {
        Timber.tag("Catch-Error").w(t.message)
        //这里不光只能打印错误, 还可以根据不同的错误做出不同的逻辑处理
        //这里只是对几个常用错误进行简单的处理, 展示这个类的用法, 在实际开发中请您自行对更多错误进行更严谨的处理
        var msg = "服务异常，请稍后再试"
        when (t) {
            is UnknownHostException -> msg = "网络不可用"
            is SocketTimeoutException -> msg = "请求网络超时"
            is HttpException -> msg = convertStatusCode(t)
            is HttpResultException -> {
              /*  when (t.errorInfo.errCode) {
                    //如果需要对某些code码进行特殊处理，在这里
                    4005555 -> {

                    }

                    else -> {
                        msg = t.errorInfo.errMessage ?: "服务异常，请稍后再试"
                    }
                }*/

                msg = t.errorInfo.errMessage ?: "服务异常，请稍后再试"
            }
            is JsonParseException, is ParseException, is JSONException, is JsonIOException -> msg = "数据解析错误"
            else -> msg = when (BuildConfig.DEBUG) {
                true -> "异常：${t.javaClass.simpleName ?: "服务异常，请稍后再试"}\n${t.localizedMessage ?: ""}"
                false -> "服务异常，请稍后再试"
            }
        }
        if (!msg.strIsEmpty())
            ArmsUtils.snackbarText(msg)
    }

    private fun convertStatusCode(httpException: HttpException): String {
        return when {
            httpException.code() == 500 -> "500 服务器发生错误"
            httpException.code() == 404 -> "404 请求地址不存在"
            httpException.code() == 403 -> "403 请求被服务器拒绝"
            httpException.code() == 307 -> "307 请求被重定向到其他页面"
            else -> httpException.message()
        }
    }
}
