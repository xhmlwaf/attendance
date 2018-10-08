package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.ConvertUtil;
import com.yunhuakeji.attendance.biz.SelectDataQueryBiz;
import com.yunhuakeji.attendance.cache.BuildingCacheService;
import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.cache.MajorCacheService;
import com.yunhuakeji.attendance.cache.OrgCacheService;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.*;
import com.yunhuakeji.attendance.dao.bizdao.model.Account;
import com.yunhuakeji.attendance.dao.bizdao.model.TermConfig;
import com.yunhuakeji.attendance.dao.bizdao.model.UserOrgRef;
import com.yunhuakeji.attendance.dto.response.*;
import com.yunhuakeji.attendance.enums.OrgType;
import com.yunhuakeji.attendance.enums.RoleType;
import com.yunhuakeji.attendance.service.baseservice.BuildingInfoService;
import com.yunhuakeji.attendance.service.baseservice.ClassInfoService;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.AccountService;
import com.yunhuakeji.attendance.service.bizservice.TermConfigService;
import com.yunhuakeji.attendance.service.bizservice.UserOrgRefService;
import com.yunhuakeji.attendance.util.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class SelectDataQueryBizImpl implements SelectDataQueryBiz {

    private static final Logger logger = LoggerFactory.getLogger(SelectDataQueryBizImpl.class);

    @Autowired
    private OrgCacheService orgCacheService;

    @Autowired
    private MajorCacheService majorCacheService;

    @Autowired
    private ClassCacheService classCacheService;

    @Autowired
    private ClassInfoService classInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserOrgRefService userOrgRefService;

    @Autowired
    private TermConfigService termConfigService;

    @Autowired
    private BuildingCacheService buildingCacheService;

    @Override
    public Result<List<BuildingQueryRspDTO>> listAllBuilding() {
        List<BuildingInfo> buildingInfoList = buildingCacheService.list();
        List<BuildingQueryRspDTO> rspDTOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(buildingInfoList)) {
            for (BuildingInfo buildingInfo : buildingInfoList) {
                rspDTOList.add(convertToDormitoryBuildingQueryRspDTO(buildingInfo));
            }
        }
        return Result.success(rspDTOList);
    }

    @Override
    public Result<List<CollegeBaseInfoDTO>> listAllSecondaryCollege() {
        List<CollegeInfo> collegeInfoList = orgCacheService.list();
        List<CollegeBaseInfoDTO> collegeBaseInfoDTOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(collegeInfoList)) {
            for (CollegeInfo collegeInfo : collegeInfoList) {
                if (collegeInfo.getType() == OrgType.JXL.getType()) {
                    CollegeBaseInfoDTO dto = new CollegeBaseInfoDTO();
                    dto.setCollegeId(collegeInfo.getOrgId());
                    dto.setCollegeName(collegeInfo.getName());
                    collegeBaseInfoDTOList.add(dto);
                }
            }
        }
        return Result.success(collegeBaseInfoDTOList);
    }

    @Override
    public Result<List<WeekInfoRspDTO>> listAllWeekInfo() {
        TermConfig termConfig = termConfigService.getLastTermConfig();
        if (termConfig == null) {
            logger.warn("不在学期内");
            return Result.success(new ArrayList<>());
        }
        Date startDate = termConfig.getStartDate();
        Date endDate = termConfig.getEndDate();
        logger.info("startDate:{}", DateUtil.dateToStr(startDate, DateUtil.DATESTYLE_YYYY_MM_DD));
        logger.info("endDate:{}", DateUtil.dateToStr(endDate, DateUtil.DATESTYLE_YYYY_MM_DD));
        return Result.success(ConvertUtil.getByStartEndDate(startDate, endDate));
    }

    @Override
    public Result<List<MajorQueryRspDTO>> listAllMajorInfo(Long orgId) {

        List<MajorInfo> majorInfoList = majorCacheService.list();
        List<MajorQueryRspDTO> majorQueryRspDTOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(majorInfoList)) {
            for (MajorInfo majorInfo : majorInfoList) {
                if (orgId != null) {
                    if (majorInfo.getOrgId().equals(orgId)) {
                        majorQueryRspDTOList.add(getByMajorInfo(majorInfo));
                    }
                } else {
                    majorQueryRspDTOList.add(getByMajorInfo(majorInfo));
                }
            }
        }

        return Result.success(majorQueryRspDTOList);
    }

    private MajorQueryRspDTO getByMajorInfo(MajorInfo majorInfo) {
        MajorQueryRspDTO dto = new MajorQueryRspDTO();
        dto.setMajorId(majorInfo.getMajorId());
        dto.setMajorName(majorInfo.getName());
        return dto;
    }

    @Override
    public Result<List<InstructorQueryRspDTO>> listInstructor(Long orgId, Long majorId) {
        List<Long> majorIds = new ArrayList<>();
        List<Long> classIds = new ArrayList<>();
        List<Long> instructorIds = null;
        List<MajorInfo> majorInfoList = null;
        List<ClassInfo> classInfoList = classCacheService.list();
        List<InstructorQueryRspDTO> instructorQueryRspDTOList = new ArrayList<>();
        //如果参数都为空，查询所有的班级
        if (orgId == null && majorId == null) {
            instructorIds = ConvertUtil.getInstructorIds(classInfoList);
        } else if (orgId != null && majorId == null) {
            majorInfoList = majorCacheService.list();
            if (!CollectionUtils.isEmpty(majorInfoList)) {
                for (MajorInfo majorInfo : majorInfoList) {
                    if (majorInfo.getOrgId().equals(orgId)) {
                        majorIds.add(majorInfo.getMajorId());
                    }
                }
            }
            if (CollectionUtils.isEmpty(majorIds)) {
                return Result.success(new ArrayList<>());
            }
        } else if (orgId != null && majorId != null) {
            majorInfoList = majorCacheService.list();
            if (!CollectionUtils.isEmpty(majorInfoList)) {
                for (MajorInfo majorInfo : majorInfoList) {
                    if (majorInfo.getOrgId().equals(orgId) && majorInfo.getMajorId().equals(majorId)) {
                        majorIds.add(majorInfo.getMajorId());
                    }
                }
            }
            if (CollectionUtils.isEmpty(majorIds)) {
                return Result.success(new ArrayList<>());
            }
        } else if (orgId == null && majorId != null) {
            majorIds.add(majorId);
        }

        if (!CollectionUtils.isEmpty(majorIds) && !CollectionUtils.isEmpty(classInfoList)) {
            for (ClassInfo classInfo : classInfoList) {
                if (majorIds.contains(classInfo.getMajorId())) {
                    classIds.add(classInfo.getClassId());
                }
            }
        }
        if (!CollectionUtils.isEmpty(classIds)) {
            List<ClassInfo> classInfos = classInfoService.selectByPrimaryKeyList(classIds);
            instructorIds = ConvertUtil.getInstructorIds(classInfos);
        }

        if (!CollectionUtils.isEmpty(instructorIds)) {
            List<User> instructorList = userService.selectByPrimaryKeyList(instructorIds);
            if (!CollectionUtils.isEmpty(instructorList)) {
                for (User user : instructorList) {
                    InstructorQueryRspDTO dto = new InstructorQueryRspDTO();
                    dto.setName(user.getUserName());
                    dto.setUserId(user.getUserId());
                    instructorQueryRspDTOList.add(dto);
                }
            }
        }

        return Result.success(instructorQueryRspDTOList);
    }

    @Override
    public Result<List<CollegeBaseInfoDTO>> listByUserId(Long userId) {
        Account account = accountService.getAccountByUserId(userId);
        if (account == null) {
            logger.warn("该用户没有角色权限.userId:{}", userId);
            return Result.success(new ArrayList<>());
        }
        if (RoleType.StudentsAffairsAdmin.getType() == account.getRoleType().byteValue()) {
            List<CollegeInfo> collegeInfoList = orgCacheService.list();
            return Result.success(ConvertUtil.getCollegeBaseInfoDTO(collegeInfoList));
        } else if (RoleType.SecondaryCollegeAdmin.getType() == account.getRoleType().byteValue()) {
            List<UserOrgRef> userOrgRefList = userOrgRefService.listByUserId(userId);
            List<Long> orgIds = ConvertUtil.getOrgIds(userOrgRefList);
            List<CollegeInfo> collegeInfoList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(orgIds)) {
                Map<Long, CollegeInfo> collegeInfoMap = orgCacheService.getCollegeInfoMap();
                for (Long orgId : orgIds) {
                    CollegeInfo collegeInfo = collegeInfoMap.get(orgId);
                    if (collegeInfo != null) {
                        collegeInfoList.add(collegeInfo);
                    }
                }
                return Result.success(ConvertUtil.getCollegeBaseInfoDTO(collegeInfoList));
            }
        }
        return Result.success(new ArrayList<>());
    }

    @Override
    public Result<UserBaseInfoDTO> getUserBasicInfo(Long userId) {
        User user = userService.selectByPrimaryKey(userId);
        if (user == null) {
            return Result.success();
        }
        UserBaseInfoDTO userBaseInfoDTO = new UserBaseInfoDTO();
        userBaseInfoDTO.setUserId(user.getUserId());
        userBaseInfoDTO.setName(user.getUserName());
        userBaseInfoDTO.setCode(user.getCode());
        userBaseInfoDTO.setProfilePhoto(user.getHeadPortraitPath());
        return Result.success(userBaseInfoDTO);
    }

    @Override
    public Result<Integer> getCurrWeekNum() {
        TermConfig termConfig = termConfigService.getLastTermConfig();
        if (termConfig == null) {
            logger.warn("不在学期内");
            return Result.success(null);
        }
        Date startDate = termConfig.getStartDate();
        Date endDate = termConfig.getEndDate();

        List<WeekInfoRspDTO> weekInfoRspDTOS = ConvertUtil.getByStartEndDate(startDate, endDate);
        if (!CollectionUtils.isEmpty(weekInfoRspDTOS)) {
            long currDate = DateUtil.getYearMonthDayByDate(DateUtil.add(new Date(), Calendar.DAY_OF_YEAR, 1));
            for (WeekInfoRspDTO dto : weekInfoRspDTOS) {
                if (currDate >= DateUtil.getYearMonthDayByDate(dto.getStartDate()) && currDate <= DateUtil.getYearMonthDayByDate(dto.getEndDate())) {
                    return Result.success(dto.getWeekNumber());
                }
            }
        }
        return Result.success(null);
    }


    private BuildingQueryRspDTO convertToDormitoryBuildingQueryRspDTO(BuildingInfo buildingInfo) {
        BuildingQueryRspDTO dto = new BuildingQueryRspDTO();
        dto.setBuildingId(buildingInfo.getBuildingId());
        dto.setBuildingName(buildingInfo.getName());
        dto.setFloorNumber(buildingInfo.getTotalFloor());
        return dto;
    }
}
