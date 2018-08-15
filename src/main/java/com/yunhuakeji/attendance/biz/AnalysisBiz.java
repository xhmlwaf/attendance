package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionClockByDayRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionClockByWeekRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionStatByDayOfWeekRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionStatByDayRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionStatByWeekRsqDTO;

import java.util.Date;

public interface AnalysisBiz {

  Result<AnalysisExceptionStatByDayRsqDTO> getAnalysisExceptionStatByDay(Long orgId, Date date);

  PagedResult<AnalysisExceptionClockByDayRsqDTO> getAnalysisExceptionClockByDay(String nameOrCode,
                                                                                Long orgId,
                                                                                Long majorId,
                                                                                Long instructor,
                                                                                Byte clockStatus,
                                                                                Date date,
                                                                                String orderBy,
                                                                                String descOrAsc);

  Result<AnalysisExceptionStatByWeekRsqDTO> getAnalysisExceptionStatByWeek(
      Long orgId,
      int weekNumber
  );


  Result<AnalysisExceptionStatByDayOfWeekRsqDTO> getAnalysisExceptionStatListByWeek(
      Long orgId,
      int weekNum
  );


  PagedResult<AnalysisExceptionClockByWeekRsqDTO> getAnalysisExceptionClockByWeek(
      String nameOrCode,
      Long orgId,
      Long majorId,
      Long instructor,
      Byte clockStatus,
      int weekNum,
      String orderBy,
      String descOrAsc
  );
}
