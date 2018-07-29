package com.yunhuakeji.attendance.constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "返回结果")
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "说明")
    private String message = "";
    @ApiModelProperty(value = "响应码")
    private String code;
    @ApiModelProperty(value = "响应数据")
    private T data;

    public static <E> Result<E> success(E data) {
        Result<E> result = new Result();
        result.setData(data);
        result.setCode("000000");
        return result;
    }

    public static <E> Result<E> fail(E data, String message, String code) {
        Result<E> result = new Result();
        result.setMessage(message);
        result.setCode(code);
        return result;
    }

    public static <E> Result<E> fail(String message, String code) {
        Result<E> result = new Result();
        result.setMessage(message);
        result.setCode(code);
        return result;
    }

    public static <E> Result<E> fail(ErrorCode errorCode) {
        Result<E> result = new Result();
        result.setMessage(errorCode.getDesc());
        result.setCode(errorCode.getCode());
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
