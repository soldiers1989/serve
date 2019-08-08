package com.easyhome.serve.mvp.ui.service

import android.app.Activity
import android.app.IntentService
import android.content.Intent
import com.jess.arms.utils.ArmsUtils
import com.easyhome.serve.app.DownloadException
import com.easyhome.serve.app.ResponseErrorSubscriber
import okhttp3.ResponseBody
import org.jetbrains.anko.startService
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

/**
 *下载服务
 *@Author GF
 *CreationTime 2018/10/18 13:56
 */
class MyDownloadService : IntentService("JRDesignDownload") {

    private lateinit var mInputSteam: InputStream
    private lateinit var mOutputStream: OutputStream
    override fun onHandleIntent(intent: Intent) {
        val fileName = intent.getStringExtra("name")
        val downloadUrl = intent.getStringExtra("url")
        if (downloadUrl.isNullOrEmpty()) {
            throw DownloadException("连接地址不能为空")
        }
        val appComment = ArmsUtils.obtainAppComponentFromContext(applicationContext)
        appComment.repositoryManager().obtainRetrofitService(DownloadService::class.java)
                .download(downloadUrl)
                .subscribe(object : ResponseErrorSubscriber<ResponseBody>(appComment.rxErrorHandler()) {
                    override fun onNext(t: ResponseBody) {
                        //保存文件到sd卡
                        println("11----------------------------ooo")
                        try {
                            val filePath = intent.getStringExtra("path")
                            val dir = File(filePath)
                            if (!dir.exists()) {
                                dir.mkdirs()
                            }

                            val file = File(filePath, fileName)
                            val byteArray = ByteArray(2048)
                            mInputSteam = t.byteStream()
                            mOutputStream = FileOutputStream(file)
                            while (true) {
                                val read = mInputSteam.read(byteArray)
                                if (read == -1) break
                                mOutputStream.write(byteArray, 0, read)
                            }
                            mOutputStream.flush()
                        } catch (e: Exception) {
                            Timber.e(e)
                            throw DownloadException(e.toString())
                        }
                    }

                })
    }

    override fun onDestroy() {
        super.onDestroy()
        mInputSteam.close()
        mOutputStream.close()
    }

    companion object {

        fun startDownload(activity: Activity, name: String, url: String?, path: String) {
            if (url != null) {
                activity.startService<MyDownloadService>("name" to name, "url" to url, "path" to path)
            }
        }

        fun startDownload(activity: Activity, name: String, url: String?): String {
            activity.getExternalFilesDir("download")?.apply {
                val filePath = this.toString() + File.separator
                startDownload(activity, name, url, filePath)
                return filePath + name
            }
            return ""
        }
    }
}