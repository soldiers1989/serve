package com.easyhome.serve.mvp.model.entity

import com.google.gson.annotations.SerializedName


data class RefundModel(
        @SerializedName("basicsCode") var basicsCode: String, // 2
        @SerializedName("basicsName") var basicsName: String, // 爱谁谁
        @SerializedName("basics") var basics: String, // 选中
        @SerializedName("basicsGroup") var basicsGroup: String// REFUND
        /* @SerializedName("id") var id: String?, //
         @SerializedName("createTime") var createTime: String?, //
         @SerializedName("createUserName") var createUserName: String?, //
         @SerializedName("createUserId") var createUserId: String?, //
         @SerializedName("delState") var delState: String? // */

)