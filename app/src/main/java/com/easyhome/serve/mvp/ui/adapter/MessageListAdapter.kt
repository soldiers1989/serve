package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R
import com.easyhome.serve.mvp.model.entity.MPair

class MessageListAdapter(data: List<MPair<String,Boolean>>) :
    BaseQuickAdapter<MPair<String,Boolean>, BaseViewHolder>
        (R.layout.message_list_layout, data) {

    override fun convert(helper: BaseViewHolder, item: MPair<String,Boolean>) {

          helper.setChecked(R.id.tabB,item.second)
    }


}