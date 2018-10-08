package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.dto.response.StudentClockCareStatRspDTO;

public interface DataRecheckBiz {

  PagedResult<StudentClockCareStatRspDTO> studentClockStatQueryPage(Long orgId,
      Long majorId,
      Long instructorId,
      Long buildingId,
      String nameOrCode,
      Integer pageNo,
      Integer pageSize,
      Long userId);
}
