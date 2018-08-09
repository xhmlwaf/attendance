package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.SystemConfigBiz;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.PasswordUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.ScreenConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.SysConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.TermSaveReqDTO;
import com.yunhuakeji.attendance.dto.response.SysConfigRspDTO;
import com.yunhuakeji.attendance.dto.response.TermRspDTO;
import org.springframework.stereotype.Service;

@Service
public class SystemConfigBizImpl implements SystemConfigBiz{



    @Override
    public Result updateSysConfig(SysConfigReqDTO reqDTO) {
        return null;
    }

    @Override
    public Result<SysConfigRspDTO> getSysConfig() {
        return null;
    }

    @Override
    public Result updateScreenConfig(ScreenConfigReqDTO reqDTO) {
        return null;
    }

    @Override
    public Result getScreenConfig(ScreenConfigReqDTO reqDTO) {
        return null;
    }

    @Override
    public Result updatePwd(PasswordUpdateReqDTO reqDTO) {
        return null;
    }

    @Override
    public Result termSave(TermSaveReqDTO reqDTO) {
        return null;
    }

    @Override
    public Result<TermRspDTO> listTerm() {
        return null;
    }
}
