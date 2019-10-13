package com.easyhome.serve.mvp.presenter.project

import android.app.Application
import com.easyhome.serve.app.ResponseErrorSubscriber
import com.easyhome.serve.app.utils.RxUtils

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.easyhome.serve.mvp.contract.project.AddDynamicContract
import okhttp3.RequestBody


@ActivityScope
class AddDynamicPresenter
@Inject
constructor(model: AddDynamicContract.Model, rootView: AddDynamicContract.View) :
    BasePresenter<AddDynamicContract.Model, AddDynamicContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

    fun addDynamic(args: RequestBody, success: (any: Any) -> Unit) {
        mModel.addDynamic(args).compose(RxUtils.applySchedulersToData(mRootView))
            .subscribe(object : ResponseErrorSubscriber<Any>(mErrorHandler) {
                override fun onNext(any: Any) {
                    success(any)
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
