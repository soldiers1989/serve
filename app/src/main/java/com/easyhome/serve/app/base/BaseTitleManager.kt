package com.easyhome.serve.app.base

interface BaseTitleManager {

    /**
     * 是否需要使用带有TitleBar的父容器
     */
    fun isUseParentLayout(): Boolean

    /**
     * 是否需要显示TitleBar右侧按钮
     */
    fun isUseTitleRight(): Boolean
}
