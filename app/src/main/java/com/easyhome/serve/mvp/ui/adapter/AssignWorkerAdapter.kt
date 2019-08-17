package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

//指派--服务信息
class AssignWorkerAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.assign_worker_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
    }


}