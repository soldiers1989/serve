package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class ProjectServeAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.project_serve_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.setText(R.id.itemTitle, item)
        helper.setText(R.id.nameTV, "老张${helper.layoutPosition}")

    }


}