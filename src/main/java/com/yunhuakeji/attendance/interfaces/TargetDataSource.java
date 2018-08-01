package com.yunhuakeji.attendance.interfaces;

import com.yunhuakeji.attendance.enums.DataBaseType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在方法上使用，用于指定使用哪个数据源
 *
 * @author 单红宇(365384722)
 * @create 2016年1月23日
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
  DataBaseType type() default DataBaseType.DEFAULT;
}