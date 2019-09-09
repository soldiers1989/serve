package com.easyhome.serve.mvp.ui.adapter

import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class PInfoAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.p_info_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {

       val strs= item.split("：")
        if(strs.size>1) {
            helper.setText(R.id.itemContent, Html.fromHtml("${strs[0]}：<font color='#777777'>${strs[1]}</font>"))
        }else {
            helper.setText(R.id.itemContent, if (!item.isNullOrEmpty()) item else "客户编号：11111")
        }
    }


}