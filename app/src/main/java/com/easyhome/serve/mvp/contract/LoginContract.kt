package com.easyhome.serve.mvp.contract

import com.jess.arms.mvp.IModel
import com.easyhome.serve.app.base.IView
import com.easyhome.serve.mvp.model.entity.HttpResult
import com.easyhome.serve.mvp.model.entity.LoginInfo
import io.reactivex.Observable


interface LoginContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun smsCode(phone: String): Observable<HttpResult<Any>>
        fun test(username: String, size: String): Observable<HttpResult<Any>>
        fun login(username: String, password: String): Observable<HttpResult<LoginInfo>>
    }

}
