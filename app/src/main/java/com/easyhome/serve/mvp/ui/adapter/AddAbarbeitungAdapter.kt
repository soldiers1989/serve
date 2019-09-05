package com.easyhome.serve.mvp.ui.adapter

import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class AddAbarbeitungAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.add_abarbeitung_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {


        if (helper.layoutPosition == 2) {

        }

        helper.setVisible(R.id.abarbeitungCL1, helper.layoutPosition != 2)
        helper.setVisible(R.id.abarbeitungCL2, helper.layoutPosition == 2)

        val str1 = "罚款<font color='#3669F8'>100</font>元"
        helper.setText(R.id.penalty, Html.fromHtml(str1))
        val str2 = "扣分<font color='#3669F8'>5</font>分"
        helper.setText(R.id.minus, Html.fromHtml(str2))
    }


}