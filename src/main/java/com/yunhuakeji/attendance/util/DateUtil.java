package com.yunhuakeji.attendance.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

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

  public static final String DATESTYLE_HH_MM_SS = "HH:mm:ss";

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
    return year * 10000 + month * 100;
  }

  public static int getYearMonthEndDay(int year, int month) {
    return year * 10000 + month * 100 + 32;
  }

  public static long ymdTolong(int yearMonth, int day) {
    return yearMonth * 100 + day;
  }

  public static long ymdToint(int year, int month, int day) {
    return year * 10000 + month * 100 + day;
  }

  public static long currYYYYMMddToLong() {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH) + 1;
    int day = c.get(Calendar.DAY_OF_MONTH);
    return ymdToint(year, month, day);
  }

  public static long getYearMonthDayByDate(Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH) + 1;
    int day = c.get(Calendar.DAY_OF_MONTH);
    return ymdToint(year, month, day);
  }

  public static int getYearMonthByDate(Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH) + 1;
    return year * 100 + month;
  }

  public static int getDayByDate(Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int day = c.get(Calendar.DAY_OF_MONTH);
    return day;
  }


  public static long currHhmmssToLong() {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE) + 1;
    int second = c.get(Calendar.SECOND);
    return hhmmssToLong(hour, minute, second);
  }

  public static int currYearMonth() {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH) + 1;
    return year * 100 + month;
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
    return hour * 10000 + minute * 100 + second;
  }

  public static Date nowDateAdd(int day) {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.DAY_OF_YEAR, day);
    return c.getTime();
  }

  // 123445
  public static String hhmmssToTimeStr(long time) {
    long hour = time / 10000;
    long minute = time / 100 % 100;
    long second = time % 100;
    return getDD(hour) + ":" + getDD(minute) + ":" + getDD(second);
  }

  public static String getDD(long i) {
    return i < 10 ? "0" + i : i + "";
  }


  public static void main(String[] args) {

    System.out.println(uuid());
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

  public static int getCurrYearMonth() {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    int month = c.get(Calendar.MONTH) + 1;
    int year = c.get(Calendar.YEAR);
    return year * 100 + month;
  }

  public static int getCurrDay() {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    return c.get(Calendar.DAY_OF_MONTH);
  }

  public static int getCurrSecond() {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    return c.get(Calendar.SECOND);
  }


  public static int getYearByDate(Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    return c.get(Calendar.YEAR);
  }

  public static int getMonthByDate(Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    return c.get(Calendar.MONTH) + 1;
  }


  public static Date add(Date date, int field, int amount) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(field, amount);
    return c.getTime();
  }

  public static long getHHMMSSByDate(Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);
    int second = c.get(Calendar.SECOND);
    return hour * 10000 + minute * 100 + second;
  }

  public static long getHHMMSSByDateStr(String dateStr) {
    Date date = strToDate(dateStr, DATESTYLE_HH_MM_SS);
    return getHHMMSSByDate(date);
  }

  public static long uuid() {
    long m = System.currentTimeMillis();
    Random random = new Random();
    int r = random.nextInt(899) + 100;
    return m * 1000 + r;
  }

  public static Date getDateStartTime(Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    c.set(Calendar.MILLISECOND, 0);
    return c.getTime();
  }

  public static Date getDateEndTime(Date date) {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DAY_OF_YEAR, 1);
    c.setTime(date);
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    c.set(Calendar.MILLISECOND, 0);
    return c.getTime();
  }

}
