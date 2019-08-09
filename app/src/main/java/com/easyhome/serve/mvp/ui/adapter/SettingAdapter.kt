package com.easyhome.serve.mvp.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class SettingAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.item_setting_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.setText(R.id.itemTV,item)
    }


}