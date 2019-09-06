package com.easyhome.serve.mvp.ui.adapter

import android.graphics.Color
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R
import com.easyhome.serve.mvp.model.entity.MPair

class MessageTabAdapter(data: List<MPair<Boolean,String>>) :
    BaseQuickAdapter<MPair<Boolean,String>, BaseViewHolder>
        (R.layout.message_tab_layout, data) {

    override fun convert(helper: BaseViewHolder, item: MPair<Boolean,String>) {

            helper.setVisible(R.id.verticalLine, helper.layoutPosition != data.size - 1)

        helper.setText(R.id.tabB, item.second)

        if (item.first) {
            helper.setImageResource(R.id.mark, R.drawable.tab_s_icon)
            helper.setVisible(R.id.number, true)
            helper.setTextColor(R.id.tabB, Color.parseColor("#FF3669F8"))
        }
    }


    data class Bean(val tab: String, val num: Int, val sle: Boolean)

}