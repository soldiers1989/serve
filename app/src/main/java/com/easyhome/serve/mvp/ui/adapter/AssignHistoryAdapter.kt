package com.easyhome.serve.mvp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

//指派历史
class AssignHistoryAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.assign_history_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.getView<RecyclerView>(R.id.infoRV).adapter = PInfoAdapter(arrayListOf("", "", ""))
    }


}