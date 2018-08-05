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

    public static int getYearMonthDay(int yearMonth, int day) {
        return yearMonth * 100 + day;
    }

    public static int getYearMonthDay(int year, int month, int day) {
        return year * 10000 + month * 100 + day;
    }

    public static Integer getYearMonthDayForCurrDay() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return getYearMonthDay(year, month, day);
    }
}
