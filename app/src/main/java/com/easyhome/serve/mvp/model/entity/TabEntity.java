package com.easyhome.serve.mvp.model.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by 宗传
 * Time 2018/9/12  下午3:26
 * <p>
 * TabEntity
 **/
public class TabEntity implements CustomTabEntity {

    private String title;
    private int selectIcon;
    private int unSelectIcon;

    public TabEntity(String title, int selectIcon, int unSelectIcon) {
        this.selectIcon = selectIcon;
        this.title = title;
        this.unSelectIcon = unSelectIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectIcon;
    }
}
