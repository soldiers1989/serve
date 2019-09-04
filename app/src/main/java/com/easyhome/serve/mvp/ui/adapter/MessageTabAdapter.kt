package com.easyhome.serve.mvp.ui.adapter

import android.graphics.Color
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

        helper.setText(R.id.tabB, item)

        if (helper.layoutPosition % 2 == 1) {
            helper.setImageResource(R.id.mark, R.drawable.tab_s_icon)
            helper.setVisible(R.id.number, true)
            helper.setTextColor(R.id.tabB, Color.parseColor("#FF3669F8"))
        }
    }


    data class Bean(val tab: String, val num: Int, val sle: Boolean)

}