package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.*;

import com.yunhuakeji.attendance.dao.bizdao.model.*;
import com.yunhuakeji.attendance.dto.response.CollegeBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.WeekInfoRspDTO;

import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.util.DateUtil;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertUtil {

    public static List<Long> getBuildingIds(List<BuildingInfo> buildingInfoList) {
        List buildingIds = new ArrayList();
        if (!CollectionUtils.isEmpty(buildingInfoList)) {
            for (BuildingInfo buildingInfo : buildingInfoList) {
                buildingIds.add(buildingInfo.getBuildingId());
            }
        }
        return buildingIds;
    }

    public static List<Long> getClassIds(List<ClassInfo> classInfoList) {
        List<Long> classIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(classInfoList)) {
            for (ClassInfo classInfo : classInfoList) {
                classIds.add(classInfo.getClassId());
            }
        }
        return classIds;
    }

    public static List<Long> getInstructorIds(List<ClassInfo> classInfoList) {
        List<Long> instructorIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(classInfoList)) {
            for (ClassInfo classInfo : classInfoList) {
                instructorIds.add(classInfo.getInstructorId());
            }
        }
        return instructorIds;
    }

    public static List<CollegeBaseInfoDTO> getCollegeBaseInfoDTO(List<CollegeInfo> collegeInfoList) {
        List<CollegeBaseInfoDTO> collegeBaseInfoDTOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(collegeInfoList)) {
            for (CollegeInfo collegeInfo : collegeInfoList) {
                CollegeBaseInfoDTO dto = new CollegeBaseInfoDTO();
                dto.setCollegeId(collegeInfo.getOrgId());
                dto.setCollegeName(collegeInfo.getName());
                collegeBaseInfoDTOList.add(dto);
            }
        }
        return collegeBaseInfoDTOList;
    }

    public static List<Long> getOrgIds(List<UserOrgRef> userOrgRefList) {
        List<Long> orgIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userOrgRefList)) {
            for (UserOrgRef userOrgRef : userOrgRefList) {
                orgIds.add(userOrgRef.getOrgId());
            }
        }
        return orgIds;
    }

    public static List<Long> getUserIds(List<Account> accountList) {
        List<Long> userIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(accountList)) {
            for (Account account : accountList) {
                userIds.add(account.getUserId());
            }
        }
        return userIds;
    }

    public static Map<Long, Long> getUserClassMap(List<UserClass> userClassList) {
        Map<Long, Long> userClassMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(userClassList)) {
            for (UserClass userClass : userClassList) {
                userClassMap.put(userClass.getUserId(), userClass.getClassId());
            }
        }
        return userClassMap;
    }

    public static Map<Long, User> getUserMap(List<User> userList) {
        Map<Long, User> userMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(userList)) {
            for (User u : userList) {
                userMap.put(u.getUserId(), u);
            }
        }
        return userMap;
    }

    public static Map<Long, List<Long>> getClassUserListMap(List<UserClass> userClassList) {
        Map<Long, List<Long>> classUserMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(userClassList)) {
            for (UserClass userClass : userClassList) {
                List<Long> userIds = classUserMap.get(userClass.getClassId());
                if (userIds == null) {
                    userIds = new ArrayList<>();
                }
                userIds.add(userClass.getUserId());
                classUserMap.put(userClass.getClassId(), userIds);
            }
        }
        return classUserMap;
    }

    /**
     * 算校周
     *
     * @param startDate :
     * @param endDate   :
     * @return : java.util.List<com.yunhuakeji.attendance.dto.response.WeekInfoRspDTO>
     */
    public static List<WeekInfoRspDTO> getByStartEndDate(Date startDate, Date endDate) {
        List<WeekInfoRspDTO> weekInfoRspDTOS = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        Date weekStart = startDate;
        int weekNumber = 1;
        while (true) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.MONDAY) {
                weekStart = calendar.getTime();
            }
            if (dayOfWeek == Calendar.SUNDAY || (dayOfWeek != Calendar.SUNDAY && calendar.getTime().getTime() == endDate.getTime())) {
                WeekInfoRspDTO weekInfoRspDTO = new WeekInfoRspDTO();
                weekInfoRspDTO.setStartDate(weekStart);
                weekInfoRspDTO.setEndDate(calendar.getTime());
                weekInfoRspDTO.setWeekNumber(weekNumber);
                weekNumber++;
                weekInfoRspDTOS.add(weekInfoRspDTO);
            }
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            if (calendar.getTime().getTime() > endDate.getTime()) {
                break;
            }
        }
        return weekInfoRspDTOS;
    }

    /**
     * 算校周
     *
     * @param startDate :
     * @param endDate   :
     * @return : java.util.List<com.yunhuakeji.attendance.dto.response.WeekInfoRspDTO>
     */
    public static WeekInfoRspDTO getWeek(Date startDate, Date endDate, int weekNumber) {
        List<WeekInfoRspDTO> weekInfoRspDTOList = getByStartEndDate(startDate, endDate);
        if (!CollectionUtils.isEmpty(weekInfoRspDTOList) && weekInfoRspDTOList.size() >= weekNumber) {
            return weekInfoRspDTOList.get(weekNumber - 1);
        } else {
            return null;
        }
    }

    public static Map<Long, Long> getUserDormitoryMap(List<DormitoryUser> dormitoryUserList) {
        Map<Long, Long> userDormitoryMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(dormitoryUserList)) {
            for (DormitoryUser dormitoryUser : dormitoryUserList) {
                userDormitoryMap.put(dormitoryUser.getUserId(), dormitoryUser.getDormitoryId());
            }
        }
        return userDormitoryMap;
    }

    public static Map<Long, DormitoryUser> getUserDormitoryRefMap(List<DormitoryUser> dormitoryUserList) {
        Map<Long, DormitoryUser> userDormitoryMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(dormitoryUserList)) {
            for (DormitoryUser dormitoryUser : dormitoryUserList) {
                userDormitoryMap.put(dormitoryUser.getUserId(), dormitoryUser);
            }
        }
        return userDormitoryMap;
    }

    public static List<Long> getUserIdsByCareList(List<Care> careList) {
        List<Long> userIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(careList)) {
            for (Care care : careList) {
                userIds.add(care.getStudentId());
            }
        }
        return userIds;
    }

    public static  boolean dateEqual(ClockDaySetting clockDaySetting, Date date) {
        if (DateUtil.getYearMonthByDate(date) == clockDaySetting.getYearMonth()
                && DateUtil.getDayByDate(date) == clockDaySetting.getDay()) {
            return true;
        } else {
            return false;
        }
    }

    public static List<Long> getUserIdsByStudentClockStatus(List<StudentClockStatusDO> studentClockStatusDOList){
        List<Long> userIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(studentClockStatusDOList)) {
            for (StudentClockStatusDO studentClockStatusDO : studentClockStatusDOList) {
                userIds.add(studentClockStatusDO.getStudentId());
            }
        }
        return userIds;
    }

    public static Map<Long,List<StudentStatusCountDO>> getStudentStatusCountMap(List<StudentStatusCountDO> studentStatusCountDOList){
        Map<Long,List<StudentStatusCountDO>> map = new HashMap<>();
        if(!CollectionUtils.isEmpty(studentStatusCountDOList)){
            for(StudentStatusCountDO s:studentStatusCountDOList){
                List<StudentStatusCountDO> studentStatusCountDOS = map.get(s.getStudentId());
                if(studentStatusCountDOS==null){
                    studentStatusCountDOS = new ArrayList<>();
                }
                studentStatusCountDOS.add(s);
                map.put(s.getStudentId(),studentStatusCountDOS);
            }
        }
        return map;
    }

    public static  Map<Long,Integer> getStudentCareCountMap(List<StudentCareCountStatDO> studentCareCountStatDOS){
        Map<Long,Integer> studentCareCountMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(studentCareCountMap)){
            for(StudentCareCountStatDO i:studentCareCountStatDOS){
                studentCareCountMap.put(i.getStudentId(),i.getStatCount());
            }
        }
        return studentCareCountMap;
    }

    public static long getCurrCheckDormitoryDay(ClockSetting clockSetting){
        long checkDormStartTime = clockSetting.getCheckDormStartTime();
        long checkDormEndTime = clockSetting.getCheckDormEndTime();
        Date nowDate = new Date();
        long currTime = DateUtil.getHHMMSSByDate(nowDate);

        if(currTime>=checkDormStartTime){
            return DateUtil.getYearMonthDayByDate(nowDate);
        } else if(currTime<checkDormEndTime){
            return DateUtil.getYearMonthDayByDate(DateUtil.add(nowDate,Calendar.DAY_OF_YEAR,-1));
        }else {
            throw new BusinessException(ErrorCode.NOT_IN_CHECK_TIME);
        }

    }


}
