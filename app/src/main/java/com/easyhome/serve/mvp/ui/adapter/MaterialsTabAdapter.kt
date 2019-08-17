package com.easyhome.serve.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class MaterialsTabAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.materials_tab_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.setText(R.id.title, item)
        helper.addOnClickListener(R.id.title)

        if(helper.layoutPosition==data.size-1){
            helper.setVisible(R.id.verticalLine,false)
        }
    }


}