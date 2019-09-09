package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class HomeStatistics1Adapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.home_top_statistics_layout_1, data) {

    override fun convert(helper: BaseViewHolder, item: String) {

        helper.setVisible(R.id.vLine, helper.layoutPosition != data.size - 1)
    }


}