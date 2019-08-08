@file:Suppress("DEPRECATION")

package com.easyhome.serve.mvp.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class ProjectDynamicTabAdapter(data: List<DynamicTabInfo>) :
    BaseQuickAdapter<ProjectDynamicTabAdapter.DynamicTabInfo, BaseViewHolder>
        (R.layout.item_dynamic_head_button_layout, data) {

    override fun convert(helper: BaseViewHolder, item: DynamicTabInfo) {
        helper.setBackgroundRes(R.id.ivDynamicHeadButton, item.icon)
            .setText(R.id.tvDynamicHeadButton, item.title)
    }

    data class DynamicTabInfo(
        var icon: Int,
        var title: String
    )
}