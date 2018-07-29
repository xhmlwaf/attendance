package com.yunhuakeji.attendance.util.excel.entity;


import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelProperty
{
	String fieldName() default "";
	
	/**EXCEL 列号*/
	int fieldIndex() default -1;
	
	/**EXCEl CELL 类型*/
	int fieldType() default -1;


}
