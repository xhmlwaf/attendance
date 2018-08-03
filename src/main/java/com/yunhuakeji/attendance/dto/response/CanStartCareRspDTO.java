package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 可发起关怀信息
 */
public class CanStartCareRspDTO extends StudentBaseInfoDTO {

  @ApiParam(name = "连续晚归次数")
  private int successiveStayOutLate;

  @ApiParam(name = "连续未归次数")
  private int successiveStayOut;

  @ApiParam(name = "累计被关怀次数")
  private int totalCared;

}
