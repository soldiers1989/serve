package com.easyhome.serve.mvp.presenter.user

import android.app.Application

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.easyhome.serve.mvp.contract.user.DeliveryAddressContract


@ActivityScope
class DeliveryAddressPresenter
@Inject
constructor(model: DeliveryAddressContract.Model, rootView: DeliveryAddressContract.View) :
    BasePresenter<DeliveryAddressContract.Model, DeliveryAddressContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager


    override fun onDestroy() {
        super.onDestroy();
    }
}
