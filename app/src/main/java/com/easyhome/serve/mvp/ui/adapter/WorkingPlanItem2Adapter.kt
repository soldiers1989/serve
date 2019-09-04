package com.easyhome.serve.mvp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class WorkingPlanItem2Adapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.working_plan_item_layout_2, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.setText(R.id.content, item)
    }


}