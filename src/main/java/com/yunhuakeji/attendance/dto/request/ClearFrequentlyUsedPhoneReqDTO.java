package com.yunhuakeji.attendance.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

/**
 * 清除常用手机
 */
public class ClearFrequentlyUsedPhoneReqDTO {

  @ApiModelProperty(name = "学生ID列表", required = true)
  @NotNull(message = "学生ID列表不能为空")
  @Size(min = 1, max = 1000, message = "学生ID列表长度1-1000")
  private List<Long> studentIds;

  public List<Long> getStudentIds() {
    return studentIds;
  }

  public void setStudentIds(List<Long> studentIds) {
    this.studentIds = studentIds;
  }
}
