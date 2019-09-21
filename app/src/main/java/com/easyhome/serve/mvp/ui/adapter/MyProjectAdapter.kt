package com.easyhome.serve.mvp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class MyProjectAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.my_project_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.addOnClickListener(R.id.nameTV)
        helper.setText(R.id.locationTV, Html.fromHtml("客户编号：<font color='#777777'>1234</font>"))
        helper.getView<RecyclerView>(R.id.tagRV).adapter = Tag3Adapter(arrayListOf("", "", "","",""))
    }


}