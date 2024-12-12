package com.hbw.x.util;

import java.io.Serializable;

/**
 */
public class ApiResult implements Serializable{
    public ApiResult(){}

    public static ApiResult newInstance(String flag, String errorCode, String errorInfo, Object data){
        return new ApiResult(flag, errorCode, errorInfo, data);
    }

    private String flag;
    private String errorCode;
    private String errorInfo;
    private Object data;

    public ApiResult(String flag, String errorCode, String errorInfo, Object data) {
        this.flag = flag;
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "flag='" + flag + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorInfo='" + errorInfo + '\'' +
                ", data=" + data +
                '}';
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
