/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */

package com.yunhuakeji.attendance.util;


/**
 * Description：位置计算工具类
 *
 * @author yaohui
 * @version [版本号]
 * @date [2016年1月29日]
 */
public class PositionUtil {

  private static final double C = 0.017453292519943295d; // PI/180.0
  private static final double EARTH_RADIUS = 6378137.0;

  /**
   * 根据两点的经纬度求两点间距离（地球半径设为6371300米）
   *
   * @param latA
   * @param lonA
   * @param latB
   * @param lonB
   * @return
   */
  public static double distanceOfTwoPostion(double latA, double lonA, double latB, double lonB) {
    latA = latA * C;
    latB = latB * C;
    double d = latB - latA;
    double d1 = (lonB - lonA) * C;
    double d2 = Math.pow(Math.sin(d / 2), 2) + Math.cos(latA) * Math.cos(latB) * Math.pow(Math.sin(d1 / 2), 2);
    return 2 * EARTH_RADIUS * Math.atan2(Math.sqrt(d2), Math.sqrt(1.0 - d2)); // 米
  }


  /**
   * 判断指定点是否在圆形区域内
   *
   * @param centerLat
   * @param centerLon
   * @param radius
   * @param pointLat
   * @param pointLon
   * @return
   */
  public static boolean isInCircle(double centerLat, double centerLon, double radius, double pointLat, double pointLon) {
    double distance = PositionUtil.distanceOfTwoPostion(pointLat, pointLon, centerLat, centerLon);
    return (distance <= radius) ? true : false;
  }


}