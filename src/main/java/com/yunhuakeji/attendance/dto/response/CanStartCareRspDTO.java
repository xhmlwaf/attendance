package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 可发起关怀信息
 */
public class CanStartCareRspDTO extends StudentBaseInfoDTO {

  @ApiModelProperty(value = "连续晚归次数")
  private int successiveStayOutLate;

  @ApiModelProperty(value = "连续未归次数")
  private int successiveStayOut;

  @ApiModelProperty(value = "累计被关怀次数")
  private int totalCared;

}
