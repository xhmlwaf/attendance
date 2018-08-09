package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.PasswordUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.ScreenConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.SysConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.TermSaveReqDTO;
import com.yunhuakeji.attendance.dto.response.SysConfigRspDTO;
import com.yunhuakeji.attendance.dto.response.TermRspDTO;

public interface SystemConfigBiz {

    Result updateSysConfig(SysConfigReqDTO reqDTO);

    Result<SysConfigRspDTO> getSysConfig();

    Result updateScreenConfig(ScreenConfigReqDTO reqDTO);

    Result getScreenConfig( ScreenConfigReqDTO reqDTO);

    Result updatePwd( PasswordUpdateReqDTO reqDTO);

    Result termSave( TermSaveReqDTO reqDTO);

    Result<TermRspDTO> listTerm();
}
