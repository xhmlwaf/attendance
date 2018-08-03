package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

public class InstructorStatRspDTO extends InstructorBaseInfoDTO {

  @ApiParam(name = "负责学生数")
  private int responsibleStudent;

  @ApiParam(name = "打卡次数")
  private int clockCount;

  @ApiParam(name = "处理关怀数")
  private int dealCareCount;

  @ApiParam(name = "累计未归数")
  private int totalLayOutCount;

  @ApiParam(name = "累计晚归数")
  private int totalLayOutLateCount;
}
