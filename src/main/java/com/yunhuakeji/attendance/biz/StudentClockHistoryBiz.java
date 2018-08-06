package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.StudentClockHistoryQueryRspDTO;

import java.util.Date;
import java.util.List;

public interface StudentClockHistoryBiz {

  Result<List<StudentClockHistoryQueryRspDTO>> listAll(Long studentId, Date date);
}
