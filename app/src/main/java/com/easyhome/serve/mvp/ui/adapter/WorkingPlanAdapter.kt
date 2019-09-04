package com.easyhome.serve.mvp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class WorkingPlanAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.working_plan_layout, data) {

    override fun convert(helper: BaseViewHolder, item: String) {


        helper.getView<RecyclerView>(R.id.itemRV).adapter = WorkingPlanItem1Adapter(arrayListOf("计划施工时间：", "开始施工时间："))

        helper.setGone(R.id.operationCL, helper.layoutPosition == 2)
        helper.getView<RecyclerView>(R.id.operationRV).adapter =
                WorkingPlanItem2Adapter(arrayListOf("确认完成", "提醒测量", "提醒复尺", "提醒安装"))
    }


}