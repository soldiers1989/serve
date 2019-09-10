package com.easyhome.serve.mvp.ui.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class DynamicAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.dynamic_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {

       when(helper.layoutPosition){

           0-> {
               helper.setText(R.id.resultTV, "动态被驳回")
               helper.setTextColor(R.id.resultTV,Color.parseColor("#999999"))
               helper.setVisible(R.id.resultIV, true)
           }

           1->{
               helper.setText(R.id.resultTV, "审核")
               helper.setTextColor(R.id.resultTV,Color.parseColor("#555555"))
               helper.setBackgroundRes(R.id.resultTV,R.drawable.labels_bac_d)
               helper.setVisible(R.id.resultIV, false)
           }
           2->{
               helper.setText(R.id.resultTV, "审核通过")
               helper.setTextColor(R.id.resultTV,Color.parseColor("#555555"))
               helper.setVisible(R.id.resultIV, false)
           }

       }


      helper.getView<RecyclerView>(R.id.photoRV).adapter = PhotoAdapter(arrayListOf("", "", ""))
        helper.getView<RecyclerView>(R.id.leavewordRV).adapter = PInfoAdapter(arrayListOf("", "", ""))
    }


}