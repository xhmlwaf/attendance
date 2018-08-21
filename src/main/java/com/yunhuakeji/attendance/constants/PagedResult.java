package com.yunhuakeji.attendance.constants;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "带分页的返回结果")
public class PagedResult<T> extends Result<Page<T>> {

  public static <E> PagedResult<E> success(Page<E> data) {
    PagedResult<E> response = new PagedResult<>();
    response.setCode(ErrorCode.SUCCESS.getCode());
    response.setMessage(ErrorCode.SUCCESS.getDesc());
    response.setData(data);
    return response;
  }

  public static <E> PagedResult<E> error(String message, String code) {
    PagedResult<E> response = new PagedResult<>();
    response.setMessage(message);
    response.setCode(code);
    return response;
  }

  public static <E> PagedResult<E> success(int pageNo, int pageSize) {
    PagedResult<E> response = new PagedResult<>();
    response.setCode(ErrorCode.SUCCESS.getCode());
    response.setMessage(ErrorCode.SUCCESS.getDesc());
    Page data = new Page<>();
    data.setPageNo(pageNo);
    data.setPageSize(pageSize);
    response.setData(data);
    return response;
  }

}
