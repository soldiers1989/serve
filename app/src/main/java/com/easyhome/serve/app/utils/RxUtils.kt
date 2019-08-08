package com.easyhome.serve.app.utils

import com.jess.arms.mvp.IView
import com.jess.arms.utils.RxLifecycleUtils
import com.easyhome.serve.mvp.model.entity.HttpResult
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 放置便于使用 RxJava 的一些工具方法
 */
object RxUtils {

    /**
     * 针对于接口返回的HttpResult不能确定data是否不为null，用这个
     */
    fun <T> applySchedulers(view: IView): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .doOnSubscribe { view.showLoading() } //显示进度条
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally { view.hideLoading() } //隐藏进度条
                    .compose(RxLifecycleUtils.bindToLifecycle(view))
        }
    }

    /**
     * 针对于接口返回的HttpResult不能确定data是否不为null，用这个
     */
    fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 如果能明确HttpResult的data不会为null，用这个
     */
    fun <T> applySchedulersToData(view: IView): ObservableTransformer<HttpResult<T>, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .doOnSubscribe { view.showLoading() } //显示进度条
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally { view.hideLoading() } //隐藏进度条
                    .compose(RxLifecycleUtils.bindToLifecycle(view))
                    .map(RxHttpResult())
        }
    }

    /**
     * 如果能明确HttpResult的data不会为null，用这个
     */
    fun <T> applySchedulersToDataBackground(view: IView): ObservableTransformer<HttpResult<T>, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(RxLifecycleUtils.bindToLifecycle(view))
                    .map(RxHttpResult())
        }
    }

    /**
     * 如果不关心data 只要接口是否调用成功用这个(应用场景：删除数据、修改数据、新增数据)
     */
    fun <T> applySchedulersToBoolean(view: IView): ObservableTransformer<HttpResult<T>, Boolean> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .doOnSubscribe { view.showLoading() } //显示进度条
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally { view.hideLoading() } //隐藏进度条
                    .compose(RxLifecycleUtils.bindToLifecycle(view))
                    .map(RxHttpResultToBoolean())
        }
    }

    /**
     * 运行后台的请求服务
     */
    fun <T> applySchedulersBackground(): ObservableTransformer<HttpResult<T>, Boolean> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(RxHttpResultToBoolean())
        }
    }
}
