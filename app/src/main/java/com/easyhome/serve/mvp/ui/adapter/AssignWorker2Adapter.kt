package com.easyhome.serve.mvp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

//指派历史
class AssignWorker2Adapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.assign_worker_layout_2, data) {

    override fun convert(helper: BaseViewHolder, item: String) {

    }


}