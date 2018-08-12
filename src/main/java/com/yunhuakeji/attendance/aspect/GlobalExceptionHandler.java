package com.yunhuakeji.attendance.aspect;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.exception.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler(BusinessException.class)
  public Result processException(BusinessException e) {
    return Result.fail(e.getErrorCode());
  }

}
