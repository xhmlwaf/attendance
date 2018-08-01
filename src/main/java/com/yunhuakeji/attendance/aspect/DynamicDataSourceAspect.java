package com.yunhuakeji.attendance.aspect;

import com.yunhuakeji.attendance.config.DataBaseSelectConfig;
import com.yunhuakeji.attendance.config.DynamicDataSourceContextHolder;
import com.yunhuakeji.attendance.enums.DataBaseType;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切换数据源Advice
 */
@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

  private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

  @Pointcut("execution(* com.yunhuakeji.attendance.*.dao.*.*(..))")
  public void mapperExecute() {
  }

  @Before("mapperExecute()")
  public void changeDataSource(JoinPoint point) throws Throwable {
    String fileName = point.getTarget().getClass().getName();
    if (DataBaseSelectConfig.baseDataSource(getMapperName(getMapperName(fileName)))) {
      DynamicDataSourceContextHolder.setDatabaseType(DataBaseType.BASEDATA);
    } else {
      DynamicDataSourceContextHolder.setDatabaseType(DataBaseType.DEFAULT);
    }
  }

  private String getMapperName(String fileName) {

    return null;
  }

  @After("mapperExecute()")
  public void restoreDataSource(JoinPoint point) {
    DynamicDataSourceContextHolder.clearDatabaseType();
  }

}
