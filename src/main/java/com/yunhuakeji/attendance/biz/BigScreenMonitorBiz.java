package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.BigScreenMonitorStatRspDTO;

import javax.servlet.http.HttpServletResponse;

public interface BigScreenMonitorBiz {

  /**
   * 大屏幕统计
   *
   * @return : com.yunhuakeji.attendance.constants.Result<com.yunhuakeji.attendance.dto.response.BigScreenMonitorStatRspDTO>
   */
  Result<BigScreenMonitorStatRspDTO> getBigScreenMonitorStat();

  /**
   * 大屏幕文案
   *
   * @return : com.yunhuakeji.attendance.constants.Result<java.lang.String>
   */
  Result<String> getCopyWriting();

  Result<String> getQrcodeImg();
}
