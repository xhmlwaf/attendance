package com.yunhuakeji.attendance.aspect;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ResponseBody
  @ExceptionHandler(BusinessException.class)
  public Result processException(BusinessException e) {
    logger.error(e.getMessage(), e);
    return Result.fail(e.getErrorCode());
  }

}
