package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R
import com.easyhome.serve.mvp.model.entity.MPair

class ANodeAdapter(data: List<MPair<Boolean,String>>) :
    BaseQuickAdapter<MPair<Boolean,String>, BaseViewHolder>
        (R.layout.a_node_layout, data) {

    override fun convert(helper: BaseViewHolder, item: MPair<Boolean,String>) {
        helper.addOnClickListener(R.id.select)
        helper.setImageResource(R.id.select,if(item.first) R.mipmap.selected_icon else R.mipmap.unselected_icon)
        helper.setText(R.id.itemName,item.second)
    }


}