package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.CareUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.DeleteCareReqDTO;
import com.yunhuakeji.attendance.dto.request.StartCareReqDTO;
import com.yunhuakeji.attendance.dto.response.CanStartCareRspDTO;
import com.yunhuakeji.attendance.dto.response.CareTaskBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.StudentCareRspDTO;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface CareBiz {

    PagedResult<CareTaskBaseInfoDTO> listByInstructor(Long instructorId, Byte careStatus, Integer pageNo, Integer pageSize);

    Result updateCare(CareUpdateReqDTO reqDTO);


    PagedResult<CareTaskBaseInfoDTO> listByStudent(Long studentId, Integer pageNo, Integer pageSize);

    Result startCare(StartCareReqDTO startCareReqDTO);

    Result deleteCare(DeleteCareReqDTO deleteCareReqDTO);

    PagedResult<StudentCareRspDTO> studentCarePage(Byte careStatus,
                                                   String nameOrCode,
                                                   Long orgId,
                                                   Long majorId,
                                                   Long instructorId,
                                                   Integer pageNo,
                                                   Integer pageSize);

    PagedResult<CanStartCareRspDTO> canStartCarePage(String nameOrCode,
                                                     Long orgId,
                                                     Long majorId,
                                                     Long instructorId,
                                                     Integer pageNo,
                                                     Integer pageSize,
                                                     String orderBy,
                                                     String descOrAsc
    );

}
