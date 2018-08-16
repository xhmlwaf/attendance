package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.AnalysisBiz;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionClockByDayRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionClockByWeekRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionStatByDayOfWeekRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionStatByDayRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionStatByWeekRsqDTO;

import java.util.Date;

public class AnalysisBizImpl implements AnalysisBiz {

  @Override
  public Result<AnalysisExceptionStatByDayRsqDTO> getAnalysisExceptionStatByDay(Long orgId, Date date) {
    return null;
  }

  @Override
  public PagedResult<AnalysisExceptionClockByDayRsqDTO> getAnalysisExceptionClockByDay(String nameOrCode, Long orgId, Long majorId, Long instructor, Byte clockStatus, Date date, String orderBy, String descOrAsc) {
    return null;
  }

  @Override
  public Result<AnalysisExceptionStatByWeekRsqDTO> getAnalysisExceptionStatByWeek(Long orgId, int weekNumber) {
    return null;
  }

  @Override
  public Result<AnalysisExceptionStatByDayOfWeekRsqDTO> getAnalysisExceptionStatListByWeek(Long orgId, int weekNum) {
    return null;
  }

  @Override
  public PagedResult<AnalysisExceptionClockByWeekRsqDTO> getAnalysisExceptionClockByWeek(String nameOrCode, Long orgId, Long majorId, Long instructor, Byte clockStatus, int weekNum, String orderBy, String descOrAsc) {
    return null;
  }
}
