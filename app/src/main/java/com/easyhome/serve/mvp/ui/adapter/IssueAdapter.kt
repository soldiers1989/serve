package com.easyhome.serve.mvp.ui.adapter

import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class IssueAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.issue_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.getView<RecyclerView>(R.id.issueRV).adapter = PInfoAdapter(arrayListOf("问题类型：一级问题", "问题描述：这样那样的", "负责人：张三"))
    }


}