package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class SettingOptionAdapter(data: List<Bean>) :
    BaseQuickAdapter<SettingOptionAdapter.Bean, BaseViewHolder>
        (R.layout.setting_option_layout, data) {

    override fun convert(helper: BaseViewHolder, item: Bean) {

        helper.setText(R.id.title, item.title)
        helper.setText(R.id.contentIV, item.content)
    }


    data class Bean(val title: String, val content: String)

}