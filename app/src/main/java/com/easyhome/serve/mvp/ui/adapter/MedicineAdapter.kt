@file:Suppress("DEPRECATION")

package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R
import com.easyhome.serve.app.extension.loadImage

class MedicineAdapter(data: List<Info>) :
    BaseQuickAdapter<MedicineAdapter.Info, BaseViewHolder>
        (R.layout.home_medicinel_layout, data) {

    override fun convert(helper: BaseViewHolder, item: Info) {
        helper.getView<ImageView>(R.id.medicineIV).loadImage(item.icon)
        helper.setText(R.id.medicineTV, item.title)
        helper.setText(R.id.pricTV, "￥${item.price.toString()}元")
    }

    data class Info(
        var icon: String,
        var title: String,
        var price: Float
    )
}