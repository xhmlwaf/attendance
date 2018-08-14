package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.UserBaseInfoDTO;

public interface LoginBiz {

  Result<UserBaseInfoDTO> login(String username, String password);

}
