package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.CareUpdateReqDTO;
import com.yunhuakeji.attendance.dto.response.CareTaskBaseInfoDTO;

public interface CareBiz {

  PagedResult<CareTaskBaseInfoDTO> listByInstructor(Long instructorId, Byte careStatus, Integer pageNo, Integer pageSize);

  Result updateCare(CareUpdateReqDTO reqDTO);


  PagedResult<CareTaskBaseInfoDTO> listByStudent(Long studentId, Integer pageNo, Integer pageSize);

}
