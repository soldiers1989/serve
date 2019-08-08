package com.easyhome.serve.app

import com.easyhome.serve.BuildConfig
import com.easyhome.serve.app.base.IView
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import java.net.ConnectException

/**
 * Created by liuzongchuan
 * Time 2018/8/26  上午1:24
 *
 *
 * 全局网络请求异常时 进行toast提示
 */
abstract class ResponseErrorSubscriber<T>(private var rxErrorHandler: RxErrorHandler, private var iView: IView? = null)
    : ErrorHandleSubscriber<T>(rxErrorHandler) {

    override fun onError(t: Throwable) {
        if (BuildConfig.DEBUG) {
            t.printStackTrace()
        }
        when (t.cause) {
            is ConnectException -> {
                iView?.showNetError()
            }
        }
        rxErrorHandler.handlerFactory.handleError(t)
    }
}
