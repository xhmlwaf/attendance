package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.StudentBaseInfoDTO;

public interface StudentBiz {

  Result<StudentBaseInfoDTO> getStudentBaseInfo(Long id);
}
