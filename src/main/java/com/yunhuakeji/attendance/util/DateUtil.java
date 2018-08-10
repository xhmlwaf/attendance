package com.yunhuakeji.attendance.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

  /**
   * yyyy-MM-dd
   */
  public static final String DATESTYLE_YYYY_MM_DD = "yyyy-MM-dd";
  /**
   * yyyy-MM-dd HH:mm:ss
   */
  public static final String DATESTYLE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
  /**
   * HHmmss
   */
  public static final String DATESTYLE_HHMMSS = "HHmmss";

  public static final String DATESTYLE_YYYYMMDD_HH_MM_SS = "yyyy.MM.dd HH:mm:ss";


  /**
   * 日期转字符串
   *
   * @param date
   * @param format
   * @return
   */
  public static String dateToStr(Date date, String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(date);
  }

  /**
   * 字符串转日期
   *
   * @param dateStr
   * @param format
   * @return
   */
  public static Date strToDate(String dateStr, String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    try {
      return sdf.parse(dateStr);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static int getYearMonth(int year, int month) {
    return year * 100 + month;
  }

  public static int getYearMonthStartDay(int year, int month) {
    return year * 1000 + month * 100;
  }

  public static int getYearMonthEndDay(int year, int month) {
    return year * 1000 + month * 100 + 32;
  }

  public static int ymdToint(int yearMonth, int day) {
    return yearMonth * 100 + day;
  }

  public static int ymdToint(int year, int month, int day) {
    return year * 10000 + month * 100 + day;
  }

  public static Integer currYmdToInt() {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH) + 1;
    int day = c.get(Calendar.DAY_OF_MONTH);
    return ymdToint(year, month, day);
  }

  public static Integer getYearMonthDayByDate(Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH) + 1;
    int day = c.get(Calendar.DAY_OF_MONTH);
    return ymdToint(year, month, day);
  }

  public static long currHhmmssToLong() {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE) + 1;
    int second = c.get(Calendar.SECOND);
    return hhmmssToLong(hour, minute, second);
  }

  /**
   * 时分秒转long
   *
   * @param hour   :
   * @param minute :
   * @param second :
   * @return : long
   */
  public static long hhmmssToLong(int hour, int minute, int second) {
    return hour * 10000 * minute * 100 * second;
  }

  public static Date nowDateAdd(int day) {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.DAY_OF_YEAR, day);
    return c.getTime();
  }

  // 123445
  public static String hhmmssToTimeStr(long time) {
    return time / 10000 + ":" + time / 100 % 100 + ":" + time % 100;
  }

  public static void main(String[] args) {
    System.out.println(hhmmssToTimeStr(123445));
  }

  public static int getCurrYear() {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    return c.get(Calendar.YEAR);
  }

  public static int getCurrMonth() {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    return c.get(Calendar.MONTH) + 1;
  }


}
