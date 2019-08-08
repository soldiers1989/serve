package com.easyhome.serve.mvp.model.entity;


import java.io.IOException;


public class HttpResultException extends IOException {
    private ErrorInfo errorInfo;

    public HttpResultException(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public HttpResultException(String detailMessage) {
        super(detailMessage);
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }
}
