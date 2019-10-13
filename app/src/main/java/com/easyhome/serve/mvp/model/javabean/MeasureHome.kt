package com.easyhome.serve.mvp.model.javabean

import com.google.gson.annotations.SerializedName

data class MeasureHome(
    val condition: List<Condition>,
    val page: Page,
    val data: List<Data>
) {
    data class Condition(
        val fieldName: String, // proID_8511
        val values: String, // 123456789
        val queryMode: String // 1
    )



    data class Page(
        val allPage: Any, // null
        val count: Any, // null
        val pageCount: Any, // null
        val nowPage: Any // null
    )







    data class Data(
        @SerializedName("proID_8511")
        val proID8511: String, // 123456789
        @SerializedName("meaTime_8511")
        val meaTime8511: String, // 2019-09-25 22:17:09
        @SerializedName("status_8511")
        val status8511: Int, // 0
        @SerializedName("cancel_8511")
        val cancel8511: String, // 2019-09-25 22:17:09
        @SerializedName("meaFile_8511")
        val meaFile8511: Int, // 0
        @SerializedName("fileUpTime_8511")
        val fileUpTime8511: String, // 2019-09-25 22:17:09
        @SerializedName("remark_8511")
        val remark8511: Any, // null
        @SerializedName("sureTime_8511")
        val sureTime8511: Any, // null
        @SerializedName("isDel_8511")
        val isDel8511: Int, // 0
        @SerializedName("logTime_8511")
        val logTime8511: String, // 2019-09-25 22:17:09
        @SerializedName("roleID_8511")
        val roleID8511: String, // 设计师
        @SerializedName("staffID_8511")
        val staffID8511: String, // 王
        @SerializedName("id_8511")
        val id8511: Int // 3
    )


}