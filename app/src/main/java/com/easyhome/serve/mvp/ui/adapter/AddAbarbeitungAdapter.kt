package com.easyhome.serve.mvp.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class AddAbarbeitungAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.add_abarbeitung_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
    }


}