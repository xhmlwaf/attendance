package com.yunhuakeji.attendance.config;

import java.util.ArrayList;
import java.util.List;

public class DataBaseSelectConfig {

  private static List<String> baseDataMapperNameList = new ArrayList<>();

  static {
    baseDataMapperNameList.add("student");
  }

  public static boolean baseDataSource(String mapperName) {
    return baseDataMapperNameList.contains(mapperName);
  }
}
