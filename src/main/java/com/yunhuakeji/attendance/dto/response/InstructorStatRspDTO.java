package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class InstructorStatRspDTO extends InstructorBaseInfoDTO {

  @ApiModelProperty(name = "负责学生数")
  private int responsibleStudent;

  @ApiModelProperty(name = "打卡次数")
  private int clockCount;

  @ApiModelProperty(name = "处理关怀数")
  private int dealCareCount;

  @ApiModelProperty(name = "累计未归学生")
  private int totalLayOutCount;

  @ApiModelProperty(name = "累计晚归学生")
  private int totalLayOutLateCount;
}
