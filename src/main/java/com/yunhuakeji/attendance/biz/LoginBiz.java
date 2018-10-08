package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.AdminLoginReqDTO;
import com.yunhuakeji.attendance.dto.response.AdminLoginRspDTO;

public interface LoginBiz {

  Result<AdminLoginRspDTO> login(AdminLoginReqDTO reqDTO);

}
