package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class EvaluateAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.evaluate_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {

        if (helper.layoutPosition % 2 == 0) {
            helper.setText(R.id.type, "仅自己：")
        } else {
            helper.setText(R.id.type, "公开：")
        }
    }


}