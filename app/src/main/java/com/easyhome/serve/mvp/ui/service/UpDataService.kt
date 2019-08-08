package com.easyhome.serve.mvp.ui.service

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider
import com.jess.arms.utils.ArmsUtils
import com.easyhome.serve.BuildConfig
import com.easyhome.serve.app.DownloadException
import com.easyhome.serve.app.ResponseErrorSubscriber
import com.easyhome.serve.app.extension.strIsEmpty
import okhttp3.ResponseBody
import org.jetbrains.anko.startService
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream


class UpDataService : IntentService("UpDataService") {
    private var mInputSteam: InputStream? = null
    private var mOutputStream: OutputStream? = null

    override fun onHandleIntent(intent: Intent) {
        val fileName = "designer${System.currentTimeMillis()}.apk"
        val downLoadUrl = intent.getStringExtra("url")
        if (downLoadUrl.strIsEmpty()) {
            throw DownloadException("下载地址不能为空")
        }
        val appComment = ArmsUtils.obtainAppComponentFromContext(applicationContext)
        appComment.repositoryManager().obtainRetrofitService(DownloadService::class.java)
                .download(downLoadUrl)
                .subscribe(object : ResponseErrorSubscriber<ResponseBody>(appComment.rxErrorHandler()) {
                    override fun onNext(t: ResponseBody) {
                        println("111----------------------------ooo")
                        try {
                            val filePath = getExternalFilesDir("up_data").toString() + File.separator
                            val dir = File(filePath)
                            if (!dir.exists()) {
                                dir.mkdirs()
                            }
                            val file = File(filePath, fileName)
                            val byteArray = ByteArray(2048)
                            //val fileSize = t.contentLength()
                            mInputSteam = t.byteStream()
                            mOutputStream = FileOutputStream(file)
                            while (true) {
                                val read = mInputSteam?.read(byteArray) ?: -1
                                if (read == -1) break
                                mOutputStream?.write(byteArray, 0, read)
                            }
                            mOutputStream?.flush()
                            installApk(file)
                        } catch (e: Exception) {
                            Timber.e(e)
                            println("${e.toString()}---------------------------ooo")
                            throw DownloadException(e.toString())
                        }
                    }
                })
    }

    /**
     * 安装 apk 文件
     *
     * @param apkFile
     */
    fun installApk(apkFile: File) {
        val installApkIntent = Intent()
        installApkIntent.action = Intent.ACTION_VIEW
        installApkIntent.addCategory(Intent.CATEGORY_DEFAULT)
        installApkIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            installApkIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            installApkIntent.setDataAndType(FileProvider.getUriForFile(applicationContext, "${BuildConfig.APPLICATION_ID}.file_provider", apkFile), "application/vnd.android.package-archive")

        } else {
            installApkIntent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive")
        }
        if (packageManager.queryIntentActivities(installApkIntent, 0).size > 0) {
            startActivity(installApkIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mInputSteam?.close()
        mOutputStream?.close()
    }


    companion object {
        fun startDownload(activity: Context, url: String) {
            activity.startService<UpDataService>("url" to url)
        }
    }
}
