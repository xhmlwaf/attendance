package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.PasswordUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.ScreenConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.SysConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.TermSaveReqDTO;
import com.yunhuakeji.attendance.dto.response.SysConfigRspDTO;
import com.yunhuakeji.attendance.dto.response.TermRspDTO;
import java.util.List;

public interface SystemConfigBiz {

  Result updateSysConfig(SysConfigReqDTO reqDTO);

  Result<SysConfigRspDTO> getSysConfig();

  Result updateScreenConfig(ScreenConfigReqDTO reqDTO);

  Result<List<Integer>> listDaysByYearAndMonth(Integer year, Integer month);

  Result<String> getScreenConfig();

  Result termSave(TermSaveReqDTO reqDTO);

  Result<List<TermRspDTO>> listTerm();

  Result<List<Integer>> listClockDayFromCurr();

  Result updatePwd(PasswordUpdateReqDTO reqDTO);
}
