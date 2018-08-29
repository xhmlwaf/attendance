package com.yunhuakeji.attendance.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 操作excel工具类
 */
public class ExcelHelper {

  /**
   * 通过流得到Workbook对象
   *
   * @param inputStream :
   * @return : org.apache.poi.ss.usermodel.Workbook
   */
  public static Workbook getWorkbook(InputStream inputStream) throws IOException, InvalidFormatException {
    return WorkbookFactory.create(inputStream);
  }

  /**
   * 通过文件得到Workbook对象
   *
   * @param file :
   * @return : org.apache.poi.ss.usermodel.Workbook
   */
  public static Workbook getWorkbook(File file) throws IOException, InvalidFormatException {
    return getWorkbook(new FileInputStream(file));
  }

  /**
   * 获取Sheets的个数
   *
   * @param workbook :
   * @return : int
   */
  public static int getSheetsNumber(Workbook workbook) {
    return workbook.getNumberOfSheets();
  }

  public static Sheet getSheetsNumber(Workbook workbook, int i) {
    return workbook.getSheetAt(i);
  }

  public static Sheet getSheetsNumber(Workbook workbook, String name) {
    return workbook.getSheet(name);
  }


}
