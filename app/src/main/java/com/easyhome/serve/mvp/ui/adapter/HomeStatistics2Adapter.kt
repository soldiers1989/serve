package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class HomeStatistics2Adapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.home_top_statistics_layout_2, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
    }


}