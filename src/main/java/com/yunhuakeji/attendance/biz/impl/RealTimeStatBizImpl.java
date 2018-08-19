package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.ConvertUtil;
import com.yunhuakeji.attendance.biz.RealTimeStatBiz;
import com.yunhuakeji.attendance.cache.BuildingCacheService;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import com.yunhuakeji.attendance.dao.bizdao.model.BuildingClockStatDO;
import com.yunhuakeji.attendance.dao.bizdao.model.BuildingStudentStatDO;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;
import com.yunhuakeji.attendance.dto.response.ClockStatByBuildingRspDTO;
import com.yunhuakeji.attendance.dto.response.ClockStatByStudentRspDTO;
import com.yunhuakeji.attendance.service.baseservice.StudentInfoService;
import com.yunhuakeji.attendance.service.bizservice.ClockSettingService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RealTimeStatBizImpl implements RealTimeStatBiz {

    @Autowired
    private BuildingCacheService buildingCacheService;

    @Autowired
    private StudentClockService studentClockService;

    @Autowired
    private ClockSettingService clockSettingService;

    @Autowired
    private StudentInfoService studentInfoService;

    @Override
    public Result<List<ClockStatByBuildingRspDTO>> realTimeStatByBuilding() {

        List<BuildingInfo> buildingInfoList = buildingCacheService.list();
        List<ClockStatByBuildingRspDTO> clockStatByBuildingRspDTOList = new ArrayList<>();
        if (CollectionUtils.isEmpty(buildingInfoList)) {
            return Result.success(new ArrayList<>());
        }

        for (BuildingInfo buildingInfo : buildingInfoList) {
            ClockStatByBuildingRspDTO dto = new ClockStatByBuildingRspDTO();
            dto.setBuildingId(buildingInfo.getBuildingId());
            dto.setBuildingName(buildingInfo.getName());
            clockStatByBuildingRspDTOList.add(dto);
        }

        ClockSetting clockSetting = clockSettingService.getClockSetting();
        //打卡开始时间
        long clockStartTime = clockSetting.getClockStartTime();
        //查寝结束时间
        long checkDormEndTime = clockSetting.getCheckDormEndTime();
        long currTime = DateUtil.currHhmmssToLong();
        if (currTime >= clockStartTime && currTime <= checkDormEndTime) {
            long currDate = DateUtil.currYYYYMMddToLong();
            List<BuildingClockStatDO> buildingClockStatDOList = studentClockService.statByBuilding(currDate);
            List<Long> buildingIds = ConvertUtil.getBuildingIds(buildingInfoList);
            List<BuildingStudentStatDO> buildingStudentStatDOList = studentInfoService.statBuildingStudent(buildingIds);
            Map<Long, Integer> buildingClockMap = getBuildingClockStatMap(buildingClockStatDOList);
            Map<Long, Integer> buildingStudentMap = getBuildingStudentStatMap(buildingStudentStatDOList);
            for (ClockStatByBuildingRspDTO item : clockStatByBuildingRspDTOList) {
                Integer clock = buildingClockMap.get(item.getBuildingId());
                item.setClockCount(clock != null ? clock : 0);
                Integer student = buildingStudentMap.get(item.getBuildingId());
                item.setNotClockCount(student != null ? student - clock : 0);
            }

        }

        return Result.success(clockStatByBuildingRspDTOList);
    }

    @Override
    public PagedResult<ClockStatByStudentRspDTO> realTimeStatByStudent(Integer pageNo, Integer pageSize) {
        return null;
    }


    /**
     * key buildingId value打卡人数
     *
     * @param buildingClockStatDOList :
     * @return : java.util.Map<java.lang.Long,java.lang.Integer>
     */
    private Map<Long, Integer> getBuildingClockStatMap(List<BuildingClockStatDO> buildingClockStatDOList) {
        Map<Long, Integer> resultMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(buildingClockStatDOList)) {
            for (BuildingClockStatDO i : buildingClockStatDOList) {
                resultMap.put(i.getBuildingId(), i.getClockStatCount());
            }
        }
        return resultMap;
    }

    /**
     * key buildingId value应打卡人数
     *
     * @param buildingStudentStatDOList :
     * @return : java.util.Map<java.lang.Long,java.lang.Integer>
     */
    private Map<Long, Integer> getBuildingStudentStatMap(List<BuildingStudentStatDO> buildingStudentStatDOList) {
        Map<Long, Integer> resultMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(buildingStudentStatDOList)) {
            for (BuildingStudentStatDO i : buildingStudentStatDOList) {
                resultMap.put(i.getBuildingId(), i.getStudentTotalCount());
            }
        }
        return resultMap;
    }

}
