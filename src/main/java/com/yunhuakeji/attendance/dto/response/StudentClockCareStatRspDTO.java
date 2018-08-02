package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

public class StudentClockCareStatRspDTO extends StudentBaseInfoDTO {

  @ApiParam(name = "累计晚归次数")
  private int totalStayOutLate;

  @ApiParam(name = "累计未归次数")
  private int totalStayOut;

  @ApiParam(name = "累计被关怀次数")
  private int totalCared;
}
