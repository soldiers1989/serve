package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class MessageTabAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.message_tab_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {

        if (helper.layoutPosition == data.size - 1) {
            helper.setVisible(R.id.verticalLine, false)
        }

        helper.setText(R.id.tabB,item)
    }


    data class Bean(val tab:String,val num:Int,val sle:Boolean)

}