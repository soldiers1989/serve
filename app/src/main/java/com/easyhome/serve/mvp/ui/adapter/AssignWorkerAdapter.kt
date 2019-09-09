package com.easyhome.serve.mvp.ui.adapter

import android.text.Html
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

//指派--服务信息
class AssignWorkerAdapter(data: List<Pair<String, Boolean>>) :
    BaseQuickAdapter<Pair<String, Boolean>, BaseViewHolder>
        (R.layout.assign_worker_layout, data) {

    override fun convert(helper: BaseViewHolder, item: Pair<String, Boolean>) {
       val strs= item.first.split("：")
        helper.setText(R.id.title, Html.fromHtml("${strs[0]}：<font color='#777777'>${strs[1]}</font>"))
        helper.setVisible(R.id.select,item.second)
    }


}