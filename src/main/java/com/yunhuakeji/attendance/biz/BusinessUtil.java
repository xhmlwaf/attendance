package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;

import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.dao.basedao.model.CollegeInfo;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.basedao.model.UserClass;
import com.yunhuakeji.attendance.dao.bizdao.model.Account;
import com.yunhuakeji.attendance.dao.bizdao.model.UserOrgRef;
import com.yunhuakeji.attendance.dto.response.CollegeBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.WeekInfoRspDTO;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessUtil {

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


}
