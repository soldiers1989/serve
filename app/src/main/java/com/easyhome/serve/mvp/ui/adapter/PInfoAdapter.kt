package com.easyhome.serve.mvp.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class PInfoAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.p_info_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
    }


}