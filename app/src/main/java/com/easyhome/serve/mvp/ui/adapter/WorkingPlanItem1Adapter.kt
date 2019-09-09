package com.easyhome.serve.mvp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class WorkingPlanItem1Adapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.working_plan_item_layout_1, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        val str1="${item}<font color='#777777'>2019年8月20日 - 2019年8月20日</font>";
        helper.setText(R.id.content,Html.fromHtml(str1))
    }


}