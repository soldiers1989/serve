package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class ProjectActionAdapter(data: List<Pair<Int, String>>) :
    BaseQuickAdapter<Pair<Int, String>, BaseViewHolder>
        (R.layout.project_action_layout, data) {

    override fun convert(helper: BaseViewHolder, item: Pair<Int, String>) {
        helper.setBackgroundRes(R.id.proIcon, item.first)
        helper.setText(R.id.itemName, item.second)
    }


}