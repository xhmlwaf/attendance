package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.InstructorClockReqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorClockStatRsqDTO;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;

public interface InstructorClockBiz {

  Result<InstructorClockStatRsqDTO> statAllCount(Long instructorId);

  Result instructorClock(InstructorClockReqDTO req);

  Result<List<String>> statByYearAndMonth(Long instructorId, Integer year, Integer month);
}
