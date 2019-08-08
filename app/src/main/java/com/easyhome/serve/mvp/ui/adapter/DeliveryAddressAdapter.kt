package com.easyhome.serve.mvp.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.easyhome.serve.R

class DeliveryAddressAdapter(data: List<DeliveryAddressAdapter.Info>) :
    BaseQuickAdapter<DeliveryAddressAdapter.Info, BaseViewHolder>
        (R.layout.item_delivery_address_layout, data) {

    override fun convert(helper: BaseViewHolder, item: Info) {

        helper.setText(R.id.consigneeTV, item.name)
        helper.setText(R.id.phoneTV, item.phone)
        helper.setText(R.id.addressTV, item.address)
        helper.setChecked(R.id.addressSelectTV, item.default)
        if (helper.adapterPosition == data.size - 1) {
            helper.setVisible(R.id.fgV, false)
        }
    }

    data class Info(
        var name: String,
        var phone: String,
        var address: String,
        var default: Boolean = false
    )
}