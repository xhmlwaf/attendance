package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.InstructorClockReqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorClockDetailRspDTO;
import com.yunhuakeji.attendance.dto.response.InstructorClockStatRsqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorStatRspDTO;
import java.util.List;

public interface InstructorClockBiz {

  Result<InstructorClockStatRsqDTO> statAllCount(Long instructorId);

  Result instructorClock(InstructorClockReqDTO req);

  Result<List<String>> statByYearAndMonth(Long instructorId, Integer year, Integer month);

  Result<Byte> getInstructorClockStatusByDay(Long instructorId);

  PagedResult<InstructorStatRspDTO> instructorStatPage(String nameOrCode,
      Long orgId, Integer pageNo,
      Integer pageSize,
      String orderBy,
      String descOrAsc, Long userId);


  PagedResult<InstructorClockDetailRspDTO> statAllClock(Long instructorId, Integer pageNo,
      Integer pageSize);


}
