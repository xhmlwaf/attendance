package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.aspect.RequestLog;
import com.yunhuakeji.attendance.biz.BusinessUtil;
import com.yunhuakeji.attendance.biz.SelectDataQueryBiz;
import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.cache.MajorCacheService;
import com.yunhuakeji.attendance.cache.OrgCacheService;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.*;
import com.yunhuakeji.attendance.dao.bizdao.model.Account;
import com.yunhuakeji.attendance.dao.bizdao.model.UserOrgRef;
import com.yunhuakeji.attendance.dto.response.*;
import com.yunhuakeji.attendance.enums.RoleType;
import com.yunhuakeji.attendance.service.baseservice.BuildingInfoService;
import com.yunhuakeji.attendance.service.baseservice.ClassInfoService;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.AccountService;
import com.yunhuakeji.attendance.service.bizservice.UserOrgRefService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class SelectDataQueryBizImpl implements SelectDataQueryBiz {

    private static final Logger logger = LoggerFactory.getLogger(SelectDataQueryBizImpl.class);


    @Autowired
    private BuildingInfoService buildingInfoService;

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
    public Result<List<CollegeBaseInfoDTO>> listAllSecondaryCollege() {
        List<CollegeInfo> collegeInfoList = orgCacheService.list();
        List<CollegeBaseInfoDTO> collegeBaseInfoDTOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(collegeInfoList)) {
            for (CollegeInfo collegeInfo : collegeInfoList) {
                CollegeBaseInfoDTO dto = new CollegeBaseInfoDTO();
                dto.setCollegeId(collegeInfo.getOrgId());
                dto.setCollegeName(collegeInfo.getName());
                collegeBaseInfoDTOList.add(dto);
            }
        }
        return Result.success(collegeBaseInfoDTOList);
    }

    @Override
    public Result<List<WeekInfoRspDTO>> listAllWeekInfo() {


        return null;
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
            instructorIds = BusinessUtil.getInstructorIds(classInfoList);
        } else if (orgId != null && majorId == null) {
            majorInfoList = majorCacheService.listAll();
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
            majorInfoList = majorCacheService.listAll();
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
        if (CollectionUtils.isEmpty(instructorIds)) {
            List<ClassInfo> classInfos = classInfoService.selectByPrimaryKeyList(classIds);
            instructorIds = BusinessUtil.getInstructorIds(classInfos);
        }

        if(!CollectionUtils.isEmpty(instructorIds)){
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
            logger.error("账号不存在.");
            return Result.success(new ArrayList<>());
        }
        if (RoleType.StudentsAffairsAdmin.getType() == account.getRoleType().byteValue()) {
            List<CollegeInfo> collegeInfoList = orgCacheService.list();
            return Result.success(BusinessUtil.getCollegeBaseInfoDTO(collegeInfoList));
        } else if (RoleType.SecondaryCollegeAdmin.getType() == account.getRoleType().byteValue()) {
            List<Long> userIds = new ArrayList<>();
            userIds.add(userId);
            List<UserOrgRef> userOrgRefList = userOrgRefService.listByUserIds(userIds);
            List<Long> orgIds = BusinessUtil.getOrgIds(userOrgRefList);
            List<CollegeInfo> collegeInfoList = orgCacheService.list();
            if (!CollectionUtils.isEmpty(orgIds) && !CollectionUtils.isEmpty(collegeInfoList)) {
                Iterator<CollegeInfo> collegeInfoIterable = collegeInfoList.iterator();
                if(collegeInfoIterable.hasNext()){
                    CollegeInfo collegeInfo = collegeInfoIterable.next();
                    if(!orgIds.contains(collegeInfo.getOrgId())){
                        collegeInfoIterable.remove();
                    }
                }
                return Result.success(BusinessUtil.getCollegeBaseInfoDTO(collegeInfoList));
            }
        }

        return Result.success(new ArrayList<>());
    }

    private BuildingQueryRspDTO convertToDormitoryBuildingQueryRspDTO(BuildingInfo buildingInfo) {
        BuildingQueryRspDTO dto = new BuildingQueryRspDTO();
        dto.setBuildingId(buildingInfo.getBuildingId());
        dto.setBuildingName(buildingInfo.getName());
        dto.setFloorNumber(buildingInfo.getTotalFloor());
        return dto;
    }
}
