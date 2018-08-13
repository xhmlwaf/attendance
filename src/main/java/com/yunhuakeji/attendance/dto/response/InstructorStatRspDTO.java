package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class InstructorStatRspDTO extends InstructorBaseInfoDTO {

  @ApiModelProperty(value = "负责学生数")
  private int responsibleStudent;

  @ApiModelProperty(value = "打卡次数")
  private int clockCount;

  @ApiModelProperty(value = "处理关怀数")
  private int dealCareCount;

  @ApiModelProperty(value = "累计未归学生")
  private int totalLayOutCount;

  @ApiModelProperty(value = "累计晚归学生")
  private int totalLayOutLateCount;
}
