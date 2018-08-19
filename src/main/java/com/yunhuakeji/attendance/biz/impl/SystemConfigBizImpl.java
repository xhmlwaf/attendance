package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.SystemConfigBiz;
import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.bizdao.model.*;
import com.yunhuakeji.attendance.dto.request.PasswordUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.ScreenConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.SysConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.TermSaveReqDTO;
import com.yunhuakeji.attendance.dto.response.SysConfigRspDTO;
import com.yunhuakeji.attendance.dto.response.TermRspDTO;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.service.bizservice.*;
import com.yunhuakeji.attendance.util.DateUtil;
import com.yunhuakeji.attendance.util.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SystemConfigBizImpl implements SystemConfigBiz {

    @Autowired
    private ClockSettingService clockSettingService;

    @Autowired
    private ClockDaySettingService clockDaySettingService;

    @Autowired
    private ClockAddressSettingService clockAddressSettingService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TermConfigService termConfigService;

    @Override
    public Result updateSysConfig(SysConfigReqDTO reqDTO) {
        ClockSetting clockSetting = new ClockSetting();
        //clockSetting.setCheckDormEndTime(reqDTO.getCheckClockEndTime());
        //TODO
        return null;
    }

    @Override
    public Result<SysConfigRspDTO> getSysConfig() {

        ClockSetting clockSetting = getClockSetting();
        SysConfigRspDTO dto = new SysConfigRspDTO();
        dto.setClockStartTime(DateUtil.hhmmssToTimeStr(clockSetting.getClockStartTime()));
        dto.setClockEndTime(DateUtil.hhmmssToTimeStr(clockSetting.getClockEndTime()));
        dto.setCheckClockStartTime(DateUtil.hhmmssToTimeStr(clockSetting.getCheckDormStartTime()));
        dto.setCheckClockEndTime(DateUtil.hhmmssToTimeStr(clockSetting.getCheckDormEndTime()));
        dto.setCheckDevice(clockSetting.getDeviceCheck().byteValue());
        dto.setYear(DateUtil.getCurrYear());
        dto.setMonth(DateUtil.getCurrMonth());
        List<ClockDaySetting> clockDaySettingList = clockDaySettingService.list(dto.getYear(), dto.getMonth());
        List<Integer> dayList = getDayList(clockDaySettingList);
        dto.setDayList(dayList);

        List<ClockAddressSetting> clockAddressSettingList = clockAddressSettingService.llstAll();
        dto.setClockAddressSettingList(clockAddressSettingList);
        return Result.success(dto);
    }

    @Override
    public Result updateScreenConfig(ScreenConfigReqDTO reqDTO) {
        ClockSetting clockSetting = getClockSetting();
        clockSetting.setCarouselText(reqDTO.getCarouselText());
        clockSettingService.update(clockSetting);
        return Result.success();
    }

    @Override
    public Result<List<Integer>> listDaysByYearAndMonth(Integer year, Integer month) {
        List<ClockDaySetting> clockDaySettingList = clockDaySettingService.list(year, month);
        List<Integer> dayList = getDayList(clockDaySettingList);
        return Result.success(dayList);
    }

    @Override
    public Result<String> getScreenConfig() {
        ClockSetting clockSetting = getClockSetting();
        return Result.success(clockSetting.getCarouselText());
    }

    @Override
    public Result termSave(TermSaveReqDTO reqDTO) {
        // 校验时间重复
        List<TermConfig> termConfigList = termConfigService.listAll();
        boolean isRepeat = false;
        if (!CollectionUtils.isEmpty(termConfigList)) {
            for (TermConfig termConfig : termConfigList) {
                if (reqDTO.getStartDate().getTime() >= termConfig.getStartDate().getTime() &&
                        reqDTO.getStartDate().getTime() <= termConfig.getEndDate().getTime()) {
                    isRepeat = true;
                }
                if (reqDTO.getEndDate().getTime() >= termConfig.getStartDate().getTime() &&
                        reqDTO.getEndDate().getTime() <= termConfig.getEndDate().getTime()) {
                    isRepeat = true;
                }
            }
        }
        if (isRepeat) {
            throw new BusinessException(ErrorCode.ADD_TERM_TIME_REPEATED);
        }

        TermConfig termConfig = new TermConfig();
        termConfig.setId(DateUtil.uuid());
        termConfig.setTermNumber(reqDTO.getTermNumber());
        termConfig.setStartDate(reqDTO.getStartDate());
        termConfig.setEndDate(reqDTO.getEndDate());
        termConfigService.insert(termConfig);
        return Result.success();
    }

    @Override
    public Result<List<TermRspDTO>> listTerm() {
        List<TermConfig> termConfigList = termConfigService.listAll();
        List<TermRspDTO> termRspDTOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(termConfigList)) {
            for (TermConfig termConfig : termConfigList) {
                TermRspDTO dto = new TermRspDTO();
                dto.setTermNumber(termConfig.getTermNumber());
                dto.setStartDate(termConfig.getStartDate());
                dto.setEndDate(termConfig.getEndDate());
                termRspDTOList.add(dto);
            }
        }
        return Result.success(termRspDTOList);
    }

    private List<Integer> getDayList(List<ClockDaySetting> clockDaySettingList) {
        List<Integer> dayList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(clockDaySettingList)) {
            for (ClockDaySetting clockDaySetting : clockDaySettingList) {
                dayList.add(clockDaySetting.getDay().intValue());
            }
        }
        return dayList;
    }

    private ClockSetting getClockSetting() {
        //获取统计日期，根据配置的查寝时间来算
        List<ClockSetting> clockSettingList = clockSettingService.listClockSetting();
        if (CollectionUtils.isEmpty(clockSettingList)) {
            throw new BusinessException(ErrorCode.CHECK_TIME_NOT_CONFIG);
        }
        return clockSettingList.get(0);
    }
}
