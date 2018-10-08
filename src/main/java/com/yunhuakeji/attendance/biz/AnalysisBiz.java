package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.AnalysisDayExceptionDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionClockByDayRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionClockByWeekRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionStatByDayRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionStatByWeekRsqDTO;
import java.util.Date;
import java.util.List;

public interface AnalysisBiz {

  Result<AnalysisExceptionStatByDayRsqDTO> getAnalysisExceptionStatByDay(Long orgId, Date date,
      Long userId);

  PagedResult<AnalysisExceptionClockByDayRsqDTO> getAnalysisExceptionClockByDay(String nameOrCode,
      Long orgId,
      Long majorId,
      Long instructor,
      Byte clockStatus,
      Date date,
      String orderBy,
      String descOrAsc, Integer pageNo, Integer pageSize
      , Long userId);

  Result<AnalysisExceptionStatByWeekRsqDTO> getAnalysisExceptionStatByWeek(
      Long orgId,
      int weekNumber
      , Long userId
  );


  Result<List<AnalysisDayExceptionDTO>> getAnalysisExceptionStatListByWeek(
      Long orgId,
      int weekNum
      , Long userId
  );


  PagedResult<AnalysisExceptionClockByWeekRsqDTO> getAnalysisExceptionClockByWeek(
      String nameOrCode,
      Long orgId,
      Long majorId,
      Long instructor,
      int weekNum,
      String orderBy,
      String descOrAsc, Integer pageNo, Integer pageSize
      , Long userId
  );
}
