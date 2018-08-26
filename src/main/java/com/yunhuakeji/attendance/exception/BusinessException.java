package com.yunhuakeji.attendance.exception;

import com.yunhuakeji.attendance.constants.ErrorCode;

public class BusinessException extends RuntimeException  {

    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode){
        super(errorCode.getDesc());
        this.errorCode = errorCode;
    }

    public BusinessException(String errMsg){
        super(errMsg);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
