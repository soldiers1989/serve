package com.easyhome.serve.mvp.ui.adapter

import android.text.Html
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class AbarbeitungAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.abarbeitung_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {

        helper.setText(R.id.nameTV, Html.fromHtml("发起人：<font color='#777777'>张三</font>"))
        helper.setText(R.id.time, Html.fromHtml("发起时间：<font color='#777777'>2019-05-01</font>"))
        helper.setText(R.id.content, "类型：")
        helper.setText(R.id.typeContent, "-类型11\n-类型2\n-类型3")

    }


}