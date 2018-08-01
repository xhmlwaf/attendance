package com.yunhuakeji.attendance.config;

import com.yunhuakeji.attendance.enums.DataBaseType;

public class DynamicDataSourceContextHolder {

  private static final ThreadLocal<DataBaseType> contextHolder = new ThreadLocal<DataBaseType>();

  public static void setDatabaseType(DataBaseType databaseType) {
    contextHolder.set(databaseType);
  }

  public static DataBaseType getDatabaseType() {
    return (DataBaseType) contextHolder.get();
  }

  public static void clearDatabaseType() {
    contextHolder.remove();
  }

}
