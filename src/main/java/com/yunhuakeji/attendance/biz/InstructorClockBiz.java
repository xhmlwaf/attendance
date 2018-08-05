package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.InstructorClockReqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorClockStatRsqDTO;

public interface InstructorClockBiz {

    Result<InstructorClockStatRsqDTO> statByInstructor(Long instructorId);

    Result instructorClock(InstructorClockReqDTO req);
}
