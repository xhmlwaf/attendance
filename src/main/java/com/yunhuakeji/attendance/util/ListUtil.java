package com.yunhuakeji.attendance.util;

import com.github.pagehelper.PageInfo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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


    List result = page(list, pageNo, pageSize);

    pageInfo.setList(result);
    pageInfo.setPageNum(pageNo);
    pageInfo.setPageSize(pageSize);
    pageInfo.setTotal(list.size());
    pageInfo.setPages(list.size() % pageSize == 0 ? list.size() / pageSize : list.size() / pageSize + 1);
    return pageInfo;
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


  /**
   * 根据传入的List和页码返回分页后的List
   *
   * @param original 全量的List数据
   * @param pageNum  页码
   * @param pageSize 每页数据条数
   * @param <T>
   * @return 返回分页后的对应页码页面的List
   */
  public static <T> List<T> page(List<T> original, int pageNum, int pageSize) {
    List list = new ArrayList<T>();
    Collections.addAll(list, page(original.toArray(), pageNum, pageSize));
    return list;
  }


  public static void main(String[] args) {

  }

  public static List quChong(List list) {
    HashSet h = new HashSet(list);
    list.clear();
    list.addAll(h);
    return list;
  }


  /**
   * 数组拆分
   *
   * @param targe :
   * @param size  :
   * @return : java.util.List<java.util.List>
   */
  public static <T> List<List<T>> createList(List<T> targe, int size) {
    List<List<T>> listArr = new ArrayList<List<T>>();
    //获取被拆分的数组个数
    int arrSize = targe.size() % size == 0 ? targe.size() / size : targe.size() / size + 1;
    for (int i = 0; i < arrSize; i++) {
      List<T> sub = new ArrayList();
      //把指定索引数据放入到list中
      for (int j = i * size; j <= size * (i + 1) - 1; j++) {
        if (j <= targe.size() - 1) {
          sub.add(targe.get(j));
        }
      }
      listArr.add(sub);
    }
    return listArr;
  }


}
