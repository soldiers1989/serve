package com.easyhome.serve.mvp.model.entity

data class Cancel(
        val basicsCode: String, // 3
        val basicsName: String, // 我没钱了
        val basicsGroup: String,// CANCEL_DESIGN
        var checked: String // CANCEL_DESIGN
)