package com.easyhome.serve.app.utils

import android.app.Activity
import android.app.AlertDialog
import java.lang.ref.SoftReference

/**
 *
 * 系统原生风格弹窗
 **/
object AlertDialogUtils {

    private var alertDialog: AlertDialog? = null
    private var activitySRF: SoftReference<Activity>? = null

    fun showDialog(activity: Activity, title: String?, msg: String?, cancel: (() -> Unit)?, enter: () -> Unit?) {
        activitySRF = SoftReference(activity)
        if (null == activitySRF || null == activitySRF!!.get() || activitySRF!!.get()!!.isFinishing) {
            return
        }
        val ctx = activitySRF?.get()
        dismissDialog()

        if (ctx != null && alertDialog == null) {
            alertDialog = AlertDialog.Builder(ctx)
                    .setTitle(title)
                    .setMessage(msg)
                    .setNegativeButton("取消") { _, _ ->
                        if (cancel != null) {
                            cancel()
                        } else {
                            dismissDialog()
                        }
                    }
                    .setPositiveButton("确定") { _, _ ->
                        enter()
                    }
                    .create()
            if (!ctx.isFinishing) {
                alertDialog!!.show()
            }
        }
    }

    fun dismissDialog() {
        try {
            if (alertDialog != null) {
                if (alertDialog!!.isShowing) {
                    alertDialog!!.dismiss()
                }
                alertDialog = null
            }
        } catch (e: Exception) {
        }
    }

}