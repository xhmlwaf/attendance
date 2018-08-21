package com.yunhuakeji.attendance.util;

import com.github.pagehelper.PageInfo;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 内存分页工具类
 */
public class ListUtil {

  /**
   * * @currPageNo 页面传入的页号，从一开始 * @pageSize 每页记录数
   */
  public static PageInfo getPagingResultMap(List list, Integer pageNo, Integer pageSize) {

    PageInfo pageInfo = new PageInfo();
    if (list.isEmpty()) {
      pageInfo.setList(Collections.emptyList());
      pageInfo.setPageNum(0);
      pageInfo.setPageSize(pageSize);
      pageInfo.setTotal(0);
      pageInfo.setPages(0);
      return pageInfo;
    }


    List<T> result = page(list, pageNo, pageSize);

    pageInfo.setList(result);
    pageInfo.setPageNum(pageNo);
    pageInfo.setPageSize(pageSize);
    pageInfo.setTotal(list.size());
    pageInfo.setPages(list.size() % pageSize == 0 ? list.size() % pageSize : list.size() % pageSize + 1);
    return pageInfo;
  }

  /**
   * 根据传入的List和页码返回分页后的List
   *
   * @param original 全量的List数据
   * @param pageNo   页码
   * @param pageSize 每页数据
   * @param <T>
   * @return 返回分页后的对应页码页面的List
   */
  public static <T> List<T> page(List<T> original, int pageNo, int pageSize) {
    return page(original, pageNo, pageSize);
  }


  /**
   * 根据传入的数组和页码返回分页后的数组
   *
   * @param original 全量数据的数组
   * @param pageNum  页码
   * @param pageSize 每页数据条数
   * @param <T>
   * @return 返回分页后的对应页码页面的数据
   */
  public static <T> T[] page(T[] original, int pageNum, int pageSize) {
    if (null == original || original.length == 0)
      return (T[]) Array.newInstance(original.getClass().getComponentType(), 0);
    if (pageNum <= 0) pageNum = 1;
    int from = (pageNum - 1) * pageSize;
    int to = pageNum * pageSize;
    if (to > original.length) to = original.length;
    if (from >= original.length || to <= from)
      return (T[]) Array.newInstance(original.getClass().getComponentType(), 0);
    return Arrays.copyOfRange(original, from, to);
  }


  public static void main(String[] args) {

  }

}
