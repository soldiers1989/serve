package com.easyhome.serve.mvp.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class KeyValueModel {

    @Id
    @NotNull
    private String key;
    @NotNull
    private String value;

    @Generated(hash = 1094825472)
    public KeyValueModel(@NotNull String key, @NotNull String value) {
        this.key = key;
        this.value = value;
    }

    @Generated(hash = 247997907)
    public KeyValueModel() {
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
