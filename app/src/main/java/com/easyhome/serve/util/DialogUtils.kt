package com.easyhome.serve.util

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.jess.arms.utils.PermissionUtil
import com.easyhome.serve.R
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.service.UpDataService
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.dialog_updata_layout.view.*
import me.jessyan.progressmanager.ProgressListener
import me.jessyan.progressmanager.ProgressManager
import me.jessyan.progressmanager.body.ProgressInfo
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.toast
import java.lang.ref.SoftReference

/**
 * Created by EVE
 * Time 2018/8/30  上午9:21
 *
 * 系统原生风格弹窗
 **/
object DialogUtils {

    private var mDialog: Dialog? = null
    private var activitySRF: SoftReference<Activity>? = null

    /**
     *版本升级弹窗
     */
    fun showUpDataDialog(activity: Activity, version: String, url: String, coerce: Int = View.VISIBLE) {
        var title = "发现新版本"
        activitySRF = SoftReference(activity)
        if (null == activitySRF || null == activitySRF!!.get() || activitySRF!!.get()!!.isFinishing) {
            return
        }
        val ctx = activitySRF?.get()
        dismissDialog()
        if (ctx != null && mDialog == null) {
            val view: View = LayoutInflater.from(ctx).inflate(R.layout.dialog_updata_layout, FrameLayout(ctx))
            mDialog = Dialog(ctx, R.style.loading_dialog)
            mDialog!!.setContentView(view, FrameLayout.LayoutParams(matchParent, matchParent))
            mDialog!!.window?.setGravity(Gravity.CENTER)

            mDialog!!.setCancelable(false)
            mDialog!!.setCanceledOnTouchOutside(false)
            view.tvUpDataVersion.text = version
            view.tvUpDataTitle.text = title
            view.tvUpDataNo.visibility = coerce
            view.tvUpDataNo.singleClick(false) {
                dismissDialog()
            }
            var downloadDialog: ProgressDialog? = null
            view.tvUpDataYes.singleClick(false) {
                PermissionUtil.externalStorage(object : PermissionUtil.RequestPermission {
                    override fun onRequestPermissionSuccess() {
                        UpDataService.startDownload(ctx, url)
                        ProgressManager.getInstance().addResponseListener(url, object : ProgressListener {
                            override fun onProgress(progressInfo: ProgressInfo?) {
                                if (downloadDialog == null) {
                                    downloadDialog = ProgressDialog(ctx)
                                    downloadDialog!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                                    downloadDialog!!.setCancelable(false)
                                    downloadDialog!!.setCanceledOnTouchOutside(false)
                                    downloadDialog!!.show()
                                } else {
                                    progressInfo?.percent?.apply {
                                        downloadDialog!!.progress = this
                                        if (progressInfo.isFinish) {
                                            downloadDialog!!.hide()
                                        }
                                    }
                                }
                            }

                            override fun onError(id: Long, e: java.lang.Exception?) {
                                activity.toast("下载失败请重试")
                                println("下载失败----------------------------ooo")
                            }

                        })
                        dismissDialog()
                    }

                    override fun onRequestPermissionFailure(permissions: MutableList<String>?) {
                        activity.toast("请求被拒绝")
                }

                    override fun onRequestPermissionFailureWithAskNeverAgain(permissions: MutableList<String>?) {
                        activity.toast("请求被拒绝, 如需要继续请进入设置页面打开该权限")
                    }
                }, RxPermissions(ctx))
            }

            if (!ctx.isFinishing) {
                mDialog!!.show()
            }
        }
    }

     fun dismissDialog() {
        try {
            if (mDialog != null) {
                if (mDialog!!.isShowing) {
                    mDialog!!.dismiss()
                }
                mDialog = null
            }
        } catch (e: Exception) {
        }
    }
}