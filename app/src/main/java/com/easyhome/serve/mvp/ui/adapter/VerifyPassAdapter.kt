package com.easyhome.serve.mvp.ui.adapter

import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class VerifyPassAdapter(data: List<VerifyPassAdapter.Bean>) :
    BaseQuickAdapter<VerifyPassAdapter.Bean, BaseViewHolder>
        (R.layout.verify_pass_layout, data) {

    override fun convert(helper: BaseViewHolder, item: VerifyPassAdapter.Bean) {
        helper.setText(R.id.itemTV, item.title)
        helper.setText(R.id.moreTV, item.content)
        helper.getView<RecyclerView>(R.id.photoRV).adapter = PhotoAdapter(item.ls)
        if (helper.layoutPosition == 0) {
            helper.setVisible(R.id.fg, false)
        }
    }


    data class Bean(
        var title: String,
        var content: String = "",
        var ls: List<String> = arrayListOf()
    )

}