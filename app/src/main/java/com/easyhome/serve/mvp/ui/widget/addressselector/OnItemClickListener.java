package com.easyhome.serve.mvp.ui.widget.addressselector;

import com.chad.library.adapter.base.BaseQuickAdapter;


public interface OnItemClickListener {
    /**
     * @param city        返回地址列表对应点击的对象
     * @param tabPosition 对应tab的位置
     */
    void itemClick(BaseQuickAdapter adapter, String city, String code, int tabPosition, int position);
}
