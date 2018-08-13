package com.yunhuakeji.attendance.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.List;

public class ExcelUtil {

  private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

  /**
   * 导出Excel
   *
   * @param sheetName sheet名称
   * @param title     标题
   * @param values    内容
   * @return
   */
  public static HSSFWorkbook getHSSFWorkbook(String sheetName, List<String> title, List<List<?>> values) {

    // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
    HSSFWorkbook wb = new HSSFWorkbook();


    // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
    HSSFSheet sheet = wb.createSheet(sheetName);

    // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
    HSSFRow row = sheet.createRow(0);

    // 第四步，创建单元格，并设置值表头 设置表头居中
    HSSFCellStyle style = wb.createCellStyle();
    //style.setAlignment(HorizontalAlignment.CENTER);//设置居中样式

    //声明列对象
    HSSFCell cell = null;

    //创建标题
    for (int i = 0; i < title.size(); i++) {
      cell = row.createCell(i);
      cell.setCellValue(title.get(i));
      cell.setCellStyle(style);
    }

    //创建内容
    for (int i = 0; i < values.size(); i++) {
      row = sheet.createRow(i + 1);
      for (int j = 0; j < values.get(i).size(); j++) {
        //将内容按顺序赋给对应的列对象
        Object o = values.get(i).get(j);
        if (o != null) {
          row.createCell(j).setCellValue(values.get(i).get(j).toString());
        } else {
          row.createCell(j).setCellValue("");
        }

      }
    }
    return wb;
  }


  /**
   * 中文文件名转换，避免下载文件名乱码
   *
   * @param fileName  :
   * @param userAgent :
   * @return : String
   */
  public static String configChineseFileName(String fileName, String userAgent) {
    try {
      if (StringUtils.isNotBlank(userAgent)) {
        userAgent = userAgent.toUpperCase();
        if (userAgent.indexOf("MSIE") > 0 || userAgent.indexOf("TRIDENT/") > 0
            || userAgent.indexOf("EDGE") > 0) {
          fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
        } else {// 其它浏览器
          fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }
      } else {
        fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return fileName;
  }
}
