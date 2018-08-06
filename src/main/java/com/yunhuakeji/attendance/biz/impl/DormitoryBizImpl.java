package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.DormitoryBiz;
import com.yunhuakeji.attendance.cache.BuildingCacheService;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryInfo;
import com.yunhuakeji.attendance.dao.bizdao.model.UserBuildingRef;
import com.yunhuakeji.attendance.dto.response.BuildingQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryQueryRspDTO;
import com.yunhuakeji.attendance.enums.AppType;
import com.yunhuakeji.attendance.service.baseservice.BuildingInfoService;
import com.yunhuakeji.attendance.service.baseservice.DormitoryInfoService;

import com.yunhuakeji.attendance.service.bizservice.UserBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DormitoryBizImpl implements DormitoryBiz {

    @Autowired
    private BuildingInfoService buildingInfoService;

    @Autowired
    private DormitoryInfoService dormitoryInfoService;

    @Autowired
    private UserBuildingService userBuildingService;

    @Autowired
    private BuildingCacheService buildingCacheService;

    @Override
    public Result<List<BuildingQueryRspDTO>> listAllBuilding() {
        List<BuildingInfo> buildingInfoList = buildingInfoService.listAll();
        List<BuildingQueryRspDTO> rspDTOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(buildingInfoList)) {
            for (BuildingInfo buildingInfo : buildingInfoList) {
                rspDTOList.add(convertToDormitoryBuildingQueryRspDTO(buildingInfo));
            }
        }
        return Result.success(rspDTOList);
    }

    @Override
    public Result<List<DormitoryQueryRspDTO>> listDormitory(Long buildingId, Integer floorNumber) {

        List<DormitoryQueryRspDTO> resultList = new ArrayList<>();
        List<DormitoryInfo> dormitoryInfoList = dormitoryInfoService.list(buildingId, floorNumber);
        if (!CollectionUtils.isEmpty(dormitoryInfoList)) {
            for (DormitoryInfo dormitoryInfo : dormitoryInfoList) {
                resultList.add(convertToDormitoryQueryRspDTO(dormitoryInfo));
            }
        }
        return Result.success(resultList);
    }

    @Override
    public Result<List<BuildingQueryRspDTO>> listForApp(Long userId, Byte appType) {

        if (AppType.FDY.getType() == appType) {
            List<DormitoryInfo> dormitoryInfoList = dormitoryInfoService.listDormitoryByInstructorId(userId);
            Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getMap();
            List<BuildingQueryRspDTO> rspDTOList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(dormitoryInfoList)) {
                for (DormitoryInfo dormitoryInfo : dormitoryInfoList) {
                    BuildingQueryRspDTO dto = new BuildingQueryRspDTO();
                    dto.setBuildingId(dormitoryInfo.getBuildingId());
                    if (buildingInfoMap.get(dormitoryInfo.getBuildingId()) != null) {
                        dto.setBuildingName(buildingInfoMap.get(dormitoryInfo.getBuildingId()).getName());
                    }
                }
            }
            return Result.success(rspDTOList);
        } else if (AppType.SSY.getType() == appType) {
            List<UserBuildingRef> userBuildingRefList = userBuildingService.listByUserId(userId);
            Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getMap();
            List<BuildingQueryRspDTO> rspDTOList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(userBuildingRefList)) {
                for (UserBuildingRef userBuildingRef : userBuildingRefList) {
                    BuildingQueryRspDTO dto = new BuildingQueryRspDTO();
                    dto.setBuildingId(userBuildingRef.getBuildingId());
                    if (buildingInfoMap.get(userBuildingRef.getBuildingId()) != null) {
                        dto.setBuildingName(buildingInfoMap.get(userBuildingRef.getBuildingId()).getName());
                    }
                }
            }
            return Result.success(rspDTOList);

        } else if (AppType.XSC.getType() == appType) {
            List<BuildingInfo> buildingInfoList = buildingInfoService.listAll();
            List<BuildingQueryRspDTO> rspDTOList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(buildingInfoList)) {
                for (BuildingInfo buildingInfo : buildingInfoList) {
                    rspDTOList.add(convertToDormitoryBuildingQueryRspDTO(buildingInfo));
                }
            }
            return Result.success(rspDTOList);
        }
        return null;
    }

    private BuildingQueryRspDTO convertToDormitoryBuildingQueryRspDTO(BuildingInfo buildingInfo) {
        BuildingQueryRspDTO dto = new BuildingQueryRspDTO();
        dto.setBuildingId(buildingInfo.getBuildingId());
        dto.setBuildingName(buildingInfo.getName());
        return dto;
    }


    private DormitoryQueryRspDTO convertToDormitoryQueryRspDTO(DormitoryInfo dormitoryInfo) {
        DormitoryQueryRspDTO dto = new DormitoryQueryRspDTO();
        dto.setDormitoryId(dormitoryInfo.getDormitoryId());
        dto.setDormitoryName(dormitoryInfo.getName());
        return dto;
    }

}
