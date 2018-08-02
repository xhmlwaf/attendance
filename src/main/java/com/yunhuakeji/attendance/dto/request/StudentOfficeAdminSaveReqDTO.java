package com.yunhuakeji.attendance.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiParam;

public class StudentOfficeAdminSaveReqDTO {

  @ApiParam(name = "教职工ID列表", required = true)
  @NotNull(message = "教职工ID列表不能为空")
  @Size(min = 1, max = 1000, message = "教职工ID列表长度1-1000")
  private List<Long> staffIdList;

  public List<Long> getStaffIdList() {
    return staffIdList;
  }

  public void setStaffIdList(List<Long> staffIdList) {
    this.staffIdList = staffIdList;
  }
}
