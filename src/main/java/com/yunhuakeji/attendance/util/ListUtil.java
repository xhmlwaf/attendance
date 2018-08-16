package com.yunhuakeji.attendance.util;

import com.github.pagehelper.PageInfo;

import org.apache.poi.ss.formula.functions.T;

import java.util.Collections;
import java.util.List;

public class ListUtil {

  /**
   * * @currPageNo 页面传入的页号，从一开始 * @pageSize 每页记录数
   */
  public static PageInfo getPagingResultMap(List list, Integer currPageNo, Integer pageSize) {

    PageInfo pageInfo = new PageInfo();
    if (list.isEmpty()) {
      pageInfo.setList(Collections.emptyList());
      pageInfo.setPageNum(0);
      pageInfo.setPageSize(pageSize);
      pageInfo.setTotal(0);
      pageInfo.setPages(0);
      return pageInfo;
    }

    int totalRowNum = list.size();
    int totalPageNum = (totalRowNum - 1) / pageSize + 1;

    int realPageNo = currPageNo;
    if (currPageNo > totalPageNum) {
      realPageNo = totalPageNum;
    } else if (currPageNo < 1) {
      realPageNo = 1;
    }

    int fromIdx = (realPageNo - 1) * pageSize;
    int toIdx = realPageNo * pageSize + 1;

    if (realPageNo == totalPageNum && totalPageNum * pageSize > totalRowNum) {
      toIdx = totalRowNum + 1;
    }

    List<T> result = list.subList(fromIdx, toIdx);

    pageInfo.setList(result);
    pageInfo.setPageNum(realPageNo);
    pageInfo.setPageSize(pageSize);
    pageInfo.setTotal(totalRowNum);
    pageInfo.setPages(totalPageNum);
    return pageInfo;
  }

}
