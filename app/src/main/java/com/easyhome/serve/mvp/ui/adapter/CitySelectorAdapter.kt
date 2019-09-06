package com.easyhome.serve.mvp.ui.adapter

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R
import com.easyhome.serve.mvp.model.entity.MPair

class CitySelectorAdapter(data: List<MPair<Boolean,String>>) :
    BaseQuickAdapter<MPair<Boolean,String>, BaseViewHolder>
        (R.layout.city_selector_layout, data) {

    override fun convert(helper: BaseViewHolder, item: MPair<Boolean,String>) {

        if (item.first) {
            helper.setBackgroundColor(R.id.item, Color.parseColor("#ffffff"))
        }else{
            helper.setBackgroundColor(R.id.item, Color.parseColor("#F8F8F8"))
        }
        helper.setText(R.id.item, item.second)
    }


}