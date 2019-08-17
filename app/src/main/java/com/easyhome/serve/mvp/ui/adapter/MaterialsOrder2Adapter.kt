package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class MaterialsOrder2Adapter(data: List<Int>) :
    BaseQuickAdapter<Int, BaseViewHolder>
        (R.layout.materials_order_layout_2, data) {

    override fun convert(helper: BaseViewHolder, item: Int) {
        helper.setBackgroundRes(R.id.brandIcon, item)
    }


}