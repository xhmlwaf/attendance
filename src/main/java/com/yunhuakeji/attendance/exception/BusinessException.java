package com.yunhuakeji.attendance.exception;

import com.yunhuakeji.attendance.constants.ErrorCode;

public class BusinessException extends Exception {

    private String code;
    private String msg;

    public BusinessException(String message, String code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.msg = errorCode.getDesc();
    }
}
