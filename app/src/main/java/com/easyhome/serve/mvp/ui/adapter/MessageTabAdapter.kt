package com.easyhome.serve.mvp.ui.adapter

import android.graphics.Color
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R
import com.easyhome.serve.mvp.model.entity.MPair

class MessageTabAdapter(data: List<MPair<Boolean, String>>) :
    BaseQuickAdapter<MPair<Boolean, String>, BaseViewHolder>
        (R.layout.message_tab_layout, data) {

    override fun convert(helper: BaseViewHolder, item: MPair<Boolean, String>) {

        helper.setVisible(R.id.verticalLine, helper.layoutPosition != data.size - 1)

        helper.setText(R.id.tabB, item.second)
        helper.setVisible(R.id.number, helper.layoutPosition == 0)
        if (item.first) {
            helper.setImageResource(R.id.mark, R.drawable.tab_s_icon)

            helper.setTextColor(R.id.tabB, Color.parseColor("#FF3669F8"))
        } else {
            helper.setImageResource(R.id.mark, R.drawable.trashy)
            helper.setTextColor(R.id.tabB, Color.parseColor("#000000"))
        }
    }


    data class Bean(val tab: String, val num: Int, val sle: Boolean)

}