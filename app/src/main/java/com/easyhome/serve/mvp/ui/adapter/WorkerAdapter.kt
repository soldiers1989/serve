package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R
import com.easyhome.serve.mvp.model.entity.MPair

class WorkerAdapter(data: List<MPair<String, String>>) :
    BaseQuickAdapter<MPair<String, String>, BaseViewHolder>
        (R.layout.worker_layout, data) {

    override fun convert(helper: BaseViewHolder, item: MPair<String, String>) {
        helper.setText(R.id.workType,item.first)
        helper.setText(R.id.worker,item.second)
    }


}