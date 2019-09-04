package com.easyhome.serve.mvp.ui.adapter

import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R
import com.easyhome.serve.mvp.contract.search.CityPicker1Contract
import com.easyhome.serve.mvp.model.entity.MPair

class SelectItemAdapter(data: List<MPair<Boolean, String>>) :
    BaseQuickAdapter<MPair<Boolean, String>, BaseViewHolder>
        (R.layout.select_item_layout, data) {

    override fun convert(helper: BaseViewHolder, item: MPair<Boolean, String>) {
        helper.setText(R.id.content, item.second)
        if (item.first) {
            helper.setBackgroundRes(R.id.content, R.drawable.button_bg_5)
            helper.setTextColor(R.id.content, Color.parseColor("#3669F8"))
        } else {
            helper.setBackgroundRes(R.id.content, R.drawable.bac_e5e5e5)
            helper.setTextColor(R.id.content, Color.parseColor("#777777"))
        }
    }


}