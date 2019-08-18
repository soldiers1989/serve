package com.easyhome.serve.mvp.ui.adapter

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class CitySelectorAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.city_selector_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {

        if (helper.layoutPosition == 0) {
            helper.setBackgroundColor(R.id.item, Color.parseColor("#ffffff"))
        }
        helper.setText(R.id.item, item)
    }


}