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

import com.easyhome.serve.mvp.contract.project.ChangeTimeContract
import com.easyhome.serve.mvp.model.javabean.MeasureHome
import okhttp3.RequestBody


@ActivityScope
class ChangeTimePresenter
@Inject
constructor(model: ChangeTimeContract.Model, rootView: ChangeTimeContract.View) :
    BasePresenter<ChangeTimeContract.Model, ChangeTimeContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

    fun measure(args: RequestBody, success: (any: List<MeasureHome>) -> Unit) {
        mModel.measure(args).compose(RxUtils.applySchedulersToData(mRootView))
            .subscribe(object : ResponseErrorSubscriber<List<MeasureHome>>(mErrorHandler) {
                override fun onNext(any: List<MeasureHome>) {
                    success(any)
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
