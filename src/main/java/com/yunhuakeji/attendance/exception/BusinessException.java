package com.yunhuakeji.attendance.exception;

public class BusinessException extends Exception {

    private int code;
    private String msg;

    public BusinessException(String message, int code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }
}
