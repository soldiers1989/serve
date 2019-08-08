package com.easyhome.serve.mvp.model.entity;


import com.easyhome.serve.api.Api;

import java.io.Serializable;

public class HttpResult<T> implements Serializable {

    /**
     * 业务code码
     */
    private int code;
    /**
     * 错误信息描述
     */
    private String message;
    /**
     * 返回数据的时间
     */
    private String timestamp;
    /**
     * 业务数据
     */
    private T data;
    /**
     * 版本信息
     */
    private VersionMsgBean versionMsg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public VersionMsgBean getVersionMsg() {
        return versionMsg;
    }

    public void setVersionMsg(VersionMsgBean versionMsg) {
        this.versionMsg = versionMsg;
    }

    public static class VersionMsgBean {
        /**
         * 更新地址
         */
        private String appAddress;
        /**
         * 更新信息描述
         */
        private String updateMsg;
        /**
         * 是否更新,0，无更新，1建议更新，2强制更新
         */
        private int updateState;

        public String getAppAddress() {
            return appAddress;
        }

        public void setAppAddress(String appAddress) {
            this.appAddress = appAddress;
        }

        public String getUpdateMsg() {
            return updateMsg;
        }

        public void setUpdateMsg(String updateMsg) {
            this.updateMsg = updateMsg;
        }

        public int getUpdateState() {
            return updateState;
        }

        public void setUpdateState(int updateState) {
            this.updateState = updateState;
        }
    }

    /**
     * 请求是否成功
     */
    public boolean isSuccess() {
        return code == Api.RequestSuccess;
    }

    /**
     * 外部请求中出现
     */
    public boolean isSucce(){
        return code == 0 && data.toString().contains("isp");

    }
}
