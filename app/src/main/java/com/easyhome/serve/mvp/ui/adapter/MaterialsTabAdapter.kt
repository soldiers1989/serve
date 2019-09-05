package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R
import com.easyhome.serve.mvp.model.entity.MPair

class MaterialsTabAdapter(data: List<MPair<Boolean, String>>) :
    BaseQuickAdapter<MPair<Boolean, String>, BaseViewHolder>
        (R.layout.materials_tab_layout, data) {

    override fun convert(helper: BaseViewHolder, item: MPair<Boolean, String>) {
        helper.setText(R.id.title, item.second)
        helper.addOnClickListener(R.id.title)

        if (helper.layoutPosition == data.size - 1) {
            helper.setVisible(R.id.verticalLine, false)
        }

        helper.setVisible(R.id.hLine, item.first)

    }


}