package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.StudentClockAddReqDTO;
import com.yunhuakeji.attendance.dto.request.StudentClockBatchUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.StudentClockUpdateReqDTO;
import com.yunhuakeji.attendance.dto.response.StudentClockQueryRsqDTO;
import com.yunhuakeji.attendance.dto.response.StudentClockStatRspDTO;
import com.yunhuakeji.attendance.dto.response.TimeClockStatusDTO;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public interface StudentClockBiz {

    Result clock(StudentClockAddReqDTO req);

    Result<StudentClockStatRspDTO> statClockByStudent(Long studentId);

    Result<List<StudentClockQueryRsqDTO>> listByYearMonth(Long studentId, Integer year, Integer month);

    Result update(StudentClockUpdateReqDTO reqDTO);

    Result<Byte> getStudentClockStatusByDay(Long studentId);

    Result<List<TimeClockStatusDTO>> listByWeekNumber(Long studentId, int weekNumber);

    Result<Boolean> checkPosition(BigDecimal posLongitude, BigDecimal posLatitude);

    Result<Boolean> checkDevice(Long studentId, String deviceId);

    Result updateBatch(@Valid @RequestBody StudentClockBatchUpdateReqDTO reqDTO);
}
