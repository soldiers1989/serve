package com.easyhome.serve.app.imageloader

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jess.arms.http.imageloader.BaseImageLoaderStrategy
import com.jess.arms.http.imageloader.glide.GlideAppliesOptions
import com.jess.arms.http.imageloader.glide.GlideArms
import com.jess.arms.http.imageloader.glide.GlideRequests
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by 宗传
 * Time 2018/9/5  上午11:01
 *
 * JRGlideImageLoaderStrategy
 **/
class JRGlideImageLoaderStrategy : BaseImageLoaderStrategy<JRImageConfig>, GlideAppliesOptions {

    override fun loadImage(ctx: Context?, config: JRImageConfig?) {
        if (ctx == null) throw NullPointerException("Context is required")
        if (config == null) throw NullPointerException("ImageConfigImpl is required")
//        if (TextUtils.isEmpty(config.url)) throw NullPointerException("Url is required")
        if (config.imageView == null) throw NullPointerException("Imageview is required")

        val requests: GlideRequests = GlideArms.with(ctx)

        //如果context是activity则自动使用Activity的生命周期

        val glideRequest = requests.load(config.url)

        when (config.cacheStrategy) {
        //缓存策略
            0 -> glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL)
            1 -> glideRequest.diskCacheStrategy(DiskCacheStrategy.NONE)
            2 -> glideRequest.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            3 -> glideRequest.diskCacheStrategy(DiskCacheStrategy.DATA)
            4 -> glideRequest.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            else -> glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL)
        }

        if (config.isCrossFade) {
            glideRequest.transition(DrawableTransitionOptions.withCrossFade())
        }

        if (config.isCenterCrop) {
            glideRequest.centerCrop()
        }

        if (config.isCircle) {
            glideRequest.circleCrop()
        }

        if (config.imageRadius != 0) {
            glideRequest.transform(RoundedCorners(config.imageRadius))
        }

        if (config.blurValue != 0) {
            glideRequest.transform(BlurTransformation(config.blurValue))
        }

        if (config.resizeX != 0 && config.resizeY != 0) {
            glideRequest.override(config.resizeX, config.resizeY)
        }

        //设置占位符
        if (config.placeholder != 0) {
            glideRequest.placeholder(config.placeholder)
        }

        //设置错误的图片
        if (config.errorPic != 0) {
            glideRequest.error(config.errorPic)
        }

        //设置请求 url 为空图片
        if (config.fallback != 0) {
            glideRequest.fallback(config.fallback)
        }

        glideRequest.into(config.imageView)
    }

    override fun clear(ctx: Context?, config: JRImageConfig?) {
        if (ctx == null) throw NullPointerException("Context is required")
        if (config == null) throw NullPointerException("ImageConfigImpl is required")
        config.imageViews?.let {
            for (imageView in it) {
                GlideArms.get(ctx).requestManagerRetriever.get(ctx).clear(imageView)
            }
        }

        if (config.isClearDiskCache) {//清除本地缓存
            Observable.just(0)
                    .observeOn(Schedulers.io())
                    .subscribe { Glide.get(ctx).clearDiskCache() }
        }

        if (config.isClearMemory) {//清除内存缓存
            Observable.just(0)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { Glide.get(ctx).clearMemory() }
        }

    }

    override fun applyGlideOptions(context: Context, builder: GlideBuilder) {
        Timber.w("applyGlideOptions")
    }
}