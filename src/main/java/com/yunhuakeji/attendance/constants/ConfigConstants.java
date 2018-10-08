package com.yunhuakeji.attendance.constants;

/**
 * 常量
 */
public class ConfigConstants {

  public static final Byte CHECK_DEVICE_YES = 1; //需要检验常用设备
  public static final Byte CHECK_DEVICE_NO = 2; //不需要检验常用设备


  public static final byte INSTRUCTOR_NOT_CLOCK = 1;//1未打卡
  public static final byte INSTRUCTOR_CLOCK = 2;// 2已打卡

  public static final byte ASC = 1; //升序
  public static final byte DESC = 2;//降序

  public static final long TOKEN_TTL = 60 * 2; //token，分钟

  public static final String TOKEN = "token";

  //管理员固定admin，非固定用户，为管理员分配的虚拟用户ID
  public static final long ADMIN_USER_ID = Integer.MAX_VALUE;

  public static final String ADMIN_NAME = "管理员";

  public static final String DEFAULT_PASSWORD = "123456";

  public static final String ADMIN_ORG_NAME = "系统管理员";
  public static final String DEFAULT_SYSTEM_OPERATOR_NAME = "系统";



}
