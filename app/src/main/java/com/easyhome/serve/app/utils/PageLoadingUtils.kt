package com.easyhome.serve.app.utils

import android.app.Activity
import android.app.Dialog
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.easyhome.serve.R
import kotlinx.android.synthetic.main.layout_loading.view.*
import java.lang.ref.SoftReference

object PageLoadingUtils {

    private var mDialog: Dialog? = null
    private var activitySRF: SoftReference<Activity>? = null

    private var times = 0

    private fun showProgressDialog(activity: Activity, canCancel: Boolean = false, msg: String = "", listener: (() -> Unit)?) {
        activitySRF = SoftReference(activity)
        if (null == activitySRF || null == activitySRF!!.get() || activitySRF!!.get()!!.isFinishing) {
            return
        }
        val ctx = activitySRF!!.get()!!
        dismissDialog()
        if (mDialog == null) {
            val view: View = LayoutInflater.from(ctx).inflate(R.layout.layout_loading, null)
            if (!TextUtils.isEmpty(msg)) {
                view.progress_msg.text = msg
            }
            mDialog = Dialog(ctx, R.style.loading_dialog)// 创建自定义样式
            mDialog!!.setContentView(view, LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT))// 设置布局
            mDialog!!.setCancelable(canCancel)
            mDialog!!.setCanceledOnTouchOutside(canCancel)
            mDialog!!.setOnCancelListener {
                if (listener != null) {
                    listener()
                }
            }
            if (!ctx.isFinishing) {
                mDialog!!.show()
            }
        }
    }

    /**
     * 隐藏弹窗
     */
    private fun dismissDialog() {
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

    /**
     * 公用的加载loading
     */
    fun showLoading(activity: Activity) {
        if (mDialog != null) {
            if (mDialog!!.isShowing) {
                times += 1
            }
        } else {
            showProgressDialog(activity, false, "加载中...", null)
        }
    }

    fun dismissLoading() {
        if (times > 0) {
            times -= 1
        } else {
            dismissDialog()
        }
    }
}