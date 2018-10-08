package com.yunhuakeji.attendance.comparator;

import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;
import java.io.Serializable;
import java.util.Comparator;

//根据年月日排序
public class ClockDaySettingCompatator01 implements Comparator<ClockDaySetting>, Serializable {

  @Override
  public int compare(ClockDaySetting o1, ClockDaySetting o2) {
    return o1.getYearMonth() * 100 + o1.getDay() - o2.getYearMonth() * 100 + o2.getDay();
  }
}
