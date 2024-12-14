package com.hbw.x.util;

import lombok.Data;

import java.io.Serializable;

/**
 */
@Data
public class ApiResult implements Serializable{
    public ApiResult(){}

    public static ApiResult newInstance(  String code, String message, Object data){
        return new ApiResult(code,message, data);
    }
    public  ApiResult( String code, String message, Object data) {

        this.code = code;
        this.message = message;
        this.data = data;
    }
    private String code;
    private String message;
    private Object data;





}
