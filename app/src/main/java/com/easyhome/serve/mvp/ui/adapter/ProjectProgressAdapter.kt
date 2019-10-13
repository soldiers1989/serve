package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R
import com.easyhome.serve.mvp.model.entity.MPair

class ProjectProgressAdapter(data: List<MPair<Int, String>>) :
    BaseQuickAdapter<MPair<Int, String>, BaseViewHolder>
        (R.layout.project_progress_layout, data) {

    override fun convert(helper: BaseViewHolder, item: MPair<Int, String>) {

        helper.setText(R.id.proTitle, item.second)
        helper.setBackgroundRes(R.id.proIcon, item.first)
        if (helper.layoutPosition == data.size-1) {
            helper.setVisible(R.id.fg, false)
        }
    }


}