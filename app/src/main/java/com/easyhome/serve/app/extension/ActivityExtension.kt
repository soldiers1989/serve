package com.easyhome.serve.app.extension

import android.os.Environment
import android.os.Environment.DIRECTORY_DOWNLOADS
import com.jess.arms.base.BaseActivity
import com.jess.arms.base.BaseFragment
import com.jess.arms.mvp.IView
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.PermissionUtil
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.entity.LocalMedia
import com.easyhome.serve.R
import com.easyhome.serve.app.DownloadException
import com.easyhome.serve.app.ResponseErrorSubscriber
import com.easyhome.serve.app.utils.RxUtils
import com.easyhome.serve.mvp.ui.activity.H5Activity
import com.easyhome.serve.mvp.ui.service.DownloadService
import com.tbruyelle.rxpermissions2.RxPermissions
import okhttp3.ResponseBody
import retrofit2.Response
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.startActivity

/**
 * Created by 宗传
 * Time 2018/8/31  下午4:57
 *
 * Activity扩展类
 **/

/**
 * 根据Url打开一个WebView
 */
fun BaseActivity<*>.openH5ForUrl(pageUrl: String, title: String = "") {
    this.startActivity<H5Activity>("pageUrl" to pageUrl, "title" to title)
}

/**
 * 根据Url打开一个WebView
 */
fun BaseFragment<*>.openH5ForUrl(pageUrl: String, title: String = "") {
    this.startActivity<H5Activity>("pageUrl" to pageUrl, "title" to title)
}

/**
 * 根据Url打开一个PdfView
 */
/*fun BaseActivity<*>.openPdfForUrl(url: String, view: IView, stage: String? = "-1", orderNo: String? = "", projectNo: String? = "") {
    if (url.strIsEmpty()) {
        view.showMessage("pdf路径异常")
        return
    }
    if (url.contains("http")) {//网络视频
        val fileName = url.getUrlFileName()
        if (fileName.isNotEmpty()) {
            getExternalFilesDir("pdf")?.apply {
                val filePath = this.toString() + File.separator
                if (File(filePath + fileName).exists()) {//本地已缓存
                    startActivity<PdfViewActivity>("url" to filePath + fileName, "stage" to stage, "orderNo" to orderNo,
                            "projectNo" to projectNo)
                } else {//下载视频
                    downloadFile(url, fileName, filePath, view) {
                        startActivity<PdfViewActivity>("url" to filePath + fileName, "stage" to stage, "orderNo" to orderNo,
                                "projectNo" to projectNo)
                    }
                }
            }
        }
    } else {//本地视频
        startActivity<PdfViewActivity>("url" to url, "stage" to stage, "orderNo" to orderNo,
                "projectNo" to projectNo)
    }
}*/

/**
 * 根据Url打开一个PdfView
 */
/*fun BaseFragment<*>.openPdfForUrl(url: String, view: IView) {
    if (url.contains("http")) {//网络视频
        val fileName = url.getUrlFileName()
        if (fileName.isNotEmpty()) {
            if (context != null)
                context!!.getExternalFilesDir("pdf")?.apply {
                    val filePath = this.toString() + File.separator
                    if (File(filePath + fileName).exists()) {//本地已缓存
                        context!!.startActivity<PdfViewActivity>("url" to filePath + fileName)
                    } else {//下载视频
                        downloadFile(url, fileName, filePath, view) {
                            context!!.startActivity<PdfViewActivity>("url" to filePath + fileName)
                        }
                    }
                }
        } else {//本地视频
            context!!.startActivity<PdfViewActivity>("url" to url)
        }
    }
}*/

/**
 * 根据Url打开一个PdfView
 */
fun BaseFragment<*>.openFileForUrl(url: String, view: IView) {
    if (url.contains("http")) {//网络视频
        val fileName = url.getUrlFileName()
        if (fileName.isNotEmpty()) {
            if (context != null)
                Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS)?.apply {
                    val filePath = this.toString() + File.separator
                    downloadFile(url, fileName, filePath, view) { path ->
                        view.showMessage("文件已下载至:$path")
                    }
                }
        }
    }
}

/**
 * 打开画廊
 * @param position  第几页  可以不传
 * @param urlList   url集合
 */
fun BaseActivity<*>.openGallery(position: Int = 0, urlList: List<String>) {
    val list = urlList.indices.mapTo(ArrayList()) {
        val url = urlList[it]
        LocalMedia().apply {
            path = url
            compressPath = url
            cutPath = url
        }
    }
    PictureSelector.create(this).themeStyle(R.style.picture_default_style).openExternalPreview(position, list)
}

/**
 * 打开画廊
 *
 * @param urlList   url集合
 * @param position  第几页  可以不传
 */
fun BaseFragment<*>.openGallery(urlList: List<String>, position: Int = 0) {
    val list = urlList.indices.mapTo(ArrayList()) {
        val url = urlList[it]
        LocalMedia().apply {
            path = url
            compressPath = url
            cutPath = url
        }
    }
    PictureSelector.create(this).themeStyle(R.style.picture_default_style).openExternalPreview(position, list)
}

/**
 * 文件下载
 */
fun BaseActivity<*>.downloadFile(url: String, fileName: String, filePath: String, view: IView, success: () -> Unit) {
    var mInputSteam: InputStream?
    var mOutputStream: OutputStream?
    PermissionUtil.externalStorage(object : PermissionUtil.RequestPermission {
        override fun onRequestPermissionSuccess() {
            val appComment = ArmsUtils.obtainAppComponentFromContext(applicationContext)
            appComment.repositoryManager().obtainRetrofitService(DownloadService::class.java)
                    .download(url)
                    .compose(RxUtils.applySchedulers(view))
                    .subscribe(object : ResponseErrorSubscriber<ResponseBody>(appComment.rxErrorHandler()) {
                        override fun onNext(t: ResponseBody) {
                            //保存文件到sd卡
                            try {
                                val dir = File(filePath)
                                if (!dir.exists()) {
                                    dir.mkdirs()
                                }
                                val file = File(filePath, fileName)
                                val byteArray = kotlin.ByteArray(2048)
                                mInputSteam = t.byteStream()
                                mOutputStream = FileOutputStream(file)
                                while (true) {
                                    val read = mInputSteam!!.read(byteArray)
                                    if (read == -1) break
                                    mOutputStream!!.write(byteArray, 0, read)
                                }
                                success()
                                mOutputStream!!.flush()
                                mInputSteam!!.close()
                                mOutputStream!!.close()
                            } catch (e: Exception) {
                                Timber.e(e)
                                throw DownloadException(e.toString())
                            }
                        }
                    })
        }

        override fun onRequestPermissionFailure(permissions: MutableList<String>?) {
            view.showMessage("请求被拒绝")
        }

        override fun onRequestPermissionFailureWithAskNeverAgain(permissions: MutableList<String>?) {
            view.showMessage("请求被拒绝, 如需要继续请进入设置页面打开该权限")
        }

    }, RxPermissions(this))

}

/**
 * 文件下载
 */
fun BaseFragment<*>.downloadFile(url: String, fileName: String, filePath: String, view: IView, success: (path: String) -> Unit) {
    var mInputSteam: InputStream?
    var mOutputStream: OutputStream?
    activity?.apply {
        PermissionUtil.externalStorage(object : PermissionUtil.RequestPermission {
            override fun onRequestPermissionSuccess() {
                val appComment = ArmsUtils.obtainAppComponentFromContext(applicationContext)
                appComment.repositoryManager().obtainRetrofitService(DownloadService::class.java)
                        .downloadHead(url)
                        .compose(RxUtils.applySchedulers(view))
                        .subscribe(object : ResponseErrorSubscriber<Response<ResponseBody>>(appComment.rxErrorHandler()) {
                            override fun onNext(t: Response<ResponseBody>) {
                                //保存文件到sd卡
                                val headerStr = t.headers().get("content-disposition")
                                var name = fileName
                                if (headerStr?.contains("attachment;filename=") == true) {
                                    name = headerStr.replace("attachment;filename=", "")
                                }
                                t.body()?.apply {
                                    try {
                                        val dir = File(filePath)
                                        if (!dir.exists()) {
                                            dir.mkdirs()
                                        }
                                        val file = File(filePath, name)
                                        val byteArray = kotlin.ByteArray(2048)
                                        mInputSteam = byteStream()
                                        mOutputStream = FileOutputStream(file)
                                        while (true) {
                                            val read = mInputSteam!!.read(byteArray)
                                            if (read == -1) break
                                            mOutputStream!!.write(byteArray, 0, read)
                                        }
                                        success(file.path)
                                        mOutputStream!!.flush()
                                        mInputSteam!!.close()
                                        mOutputStream!!.close()
                                    } catch (e: Exception) {
                                        Timber.e(e)
                                        throw DownloadException(e.toString())
                                    }
                                }
                            }
                        })
            }

            override fun onRequestPermissionFailure(permissions: MutableList<String>?) {
                view.showMessage("请求被拒绝")
            }

            override fun onRequestPermissionFailureWithAskNeverAgain(permissions: MutableList<String>?) {
                view.showMessage("请求被拒绝, 如需要继续请进入设置页面打开该权限")
            }
        }, RxPermissions(this))
    }

}

/**
 * 视频播放
 * @param videoUrl 视频地址(本地或者网络)
 */
fun BaseActivity<*>.openVideo(videoUrl: String, view: IView) {
    if (videoUrl.contains("http")) {//网络视频
        val fileName = videoUrl.getUrlFileName()
        if (fileName.isNotEmpty()) {
            getExternalFilesDir("video")?.apply {
                val filePath = this.toString() + File.separator
                if (File(filePath + fileName).exists()) {//本地已缓存
                    PictureSelector.create(this@openVideo).externalPictureVideo(filePath + fileName)
                } else {//下载视频
                    downloadFile(videoUrl, fileName, filePath, view) {
                        PictureSelector.create(this@openVideo).externalPictureVideo(filePath + fileName)
                    }
                }
            }
        } else {//本地视频
            PictureSelector.create(this).externalPictureVideo(videoUrl)
        }
    }
}

/**
 * 视频播放
 * @param videoUrl 视频地址(本地或者网络)
 */
fun BaseFragment<*>.openVideo(videoUrl: String, view: IView) {
    if (videoUrl.contains("http")) {//网络视频
        val fileName = videoUrl.getUrlFileName()
        if (fileName.isNotEmpty()) {
            context?.apply {
                getExternalFilesDir("video")?.apply {
                    val filePath = this.toString() + File.separator
                    if (File(filePath + fileName).exists()) {//本地已缓存
                        PictureSelector.create(this@openVideo).externalPictureVideo(filePath + fileName)
                    } else {//下载视频
                        downloadFile(videoUrl, fileName, filePath, view) {
                            PictureSelector.create(this@openVideo).externalPictureVideo(filePath + fileName)
                        }
                    }
                }
            }
        } else {//本地视频
            PictureSelector.create(this).externalPictureVideo(videoUrl)
        }
    }
}