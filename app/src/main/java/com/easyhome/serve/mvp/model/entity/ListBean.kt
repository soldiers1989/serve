package com.easyhome.serve.mvp.model.entity

data class ListBean(
    var name: String,
    var isSelect: Boolean,
    var ls: List<ListBean>
)