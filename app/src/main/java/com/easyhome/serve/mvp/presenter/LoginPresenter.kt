package com.easyhome.serve.mvp.presenter

import android.app.Application

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import com.easyhome.serve.app.ResponseErrorSubscriber
import com.easyhome.serve.app.utils.RxUtils
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.easyhome.serve.mvp.contract.LoginContract
import com.easyhome.serve.mvp.model.entity.LoginInfo


@ActivityScope
class LoginPresenter
@Inject
constructor(model: LoginContract.Model, rootView: LoginContract.View) :
    BasePresenter<LoginContract.Model, LoginContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

    fun smsCode(phone: String, success: (any: Any) -> Unit) {
        mModel.smsCode(phone).compose(RxUtils.applySchedulersToData(mRootView))
            .subscribe(object : ResponseErrorSubscriber<Any>(mErrorHandler) {
                override fun onNext(any: Any) {
                    success(any)
                }
            })
    }
    fun test(username: String, size: String, success: (any: Any) -> Unit) {
        mModel.test(username,size).compose(RxUtils.applySchedulersToData(mRootView))
            .subscribe(object : ResponseErrorSubscriber<Any>(mErrorHandler) {
                override fun onNext(any: Any) {
                    success(any)
                }
            })
    }
    fun login(username: String, password: String, success: (any: LoginInfo) -> Unit) {
        mModel.login(username,password).compose(RxUtils.applySchedulersToData(mRootView))
            .subscribe(object : ResponseErrorSubscriber<LoginInfo>(mErrorHandler) {
                override fun onNext(any: LoginInfo) {
                    success(any)
                }
            })
    }




    override fun onDestroy() {
        super.onDestroy();
    }
}
