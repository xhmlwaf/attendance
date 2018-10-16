package com.yunhuakeji.attendance.biz;

import static java.util.stream.Collectors.groupingBy;

import com.yunhuakeji.attendance.comparator.ClockDaySettingCompatator01;
import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.dao.basedao.model.CollegeInfo;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryInfo;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryUser;
import com.yunhuakeji.attendance.dao.basedao.model.StudentDormitoryBuildingDO;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.basedao.model.UserClass;
import com.yunhuakeji.attendance.dao.basedao.model.UserOrg;
import com.yunhuakeji.attendance.dao.bizdao.model.Account;
import com.yunhuakeji.attendance.dao.bizdao.model.Care;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockAddressSetting;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentCareCountStatDO;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockDTO;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockStatusDO;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentStatusCountDO;
import com.yunhuakeji.attendance.dao.bizdao.model.UserOrgRef;
import com.yunhuakeji.attendance.dto.request.AddressReqDTO;
import com.yunhuakeji.attendance.dto.response.CollegeBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.WeekInfoRspDTO;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.util.DateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

public class ConvertUtil {

  public static List<Long> getBuildingIds(List<BuildingInfo> buildingInfoList) {
    if (!CollectionUtils.isEmpty(buildingInfoList)) {
      return buildingInfoList.stream().map(e -> e.getBuildingId()).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public static List<Long> getClassIds(List<ClassInfo> classInfoList) {
    if (!CollectionUtils.isEmpty(classInfoList)) {
      return classInfoList.stream().map(e -> e.getClassId()).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public static List<Long> getInstructorIds(List<ClassInfo> classInfoList) {
    if (!CollectionUtils.isEmpty(classInfoList)) {
      return classInfoList.stream().map(e -> e.getInstructorId()).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public static List<CollegeBaseInfoDTO> getCollegeBaseInfoDTO(List<CollegeInfo> collegeInfoList) {
    if (!CollectionUtils.isEmpty(collegeInfoList)) {
      return collegeInfoList.stream().map(e -> {
        CollegeBaseInfoDTO dto = new CollegeBaseInfoDTO();
        dto.setCollegeId(e.getOrgId());
        dto.setCollegeName(e.getName());
        return dto;
      }).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public static List<Long> getOrgIds(List<UserOrgRef> userOrgRefList) {
    if (!CollectionUtils.isEmpty(userOrgRefList)) {
      return userOrgRefList.stream().map(e -> e.getOrgId()).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public static Map<Long, Long> getUserOrgMap(List<UserOrg> userOrgList) {
    if (!CollectionUtils.isEmpty(userOrgList)) {
      return userOrgList.stream()
          .collect(Collectors.toMap(UserOrg::getUserId, UserOrg::getOrgId, (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }

  public static List<Long> getUserIds(List<Account> accountList) {
    if (!CollectionUtils.isEmpty(accountList)) {
      return accountList.stream().map(e -> e.getUserId()).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public static Map<Long, Long> getUserClassMap(List<UserClass> userClassList) {
    if (!CollectionUtils.isEmpty(userClassList)) {
      return userClassList.stream()
          .collect(Collectors.toMap(UserClass::getUserId, UserClass::getClassId, (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }

  public static Map<Long, User> getUserMap(List<User> userList) {
    if (!CollectionUtils.isEmpty(userList)) {
      return userList.stream()
          .collect(Collectors.toMap(User::getUserId, Function.identity(), (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }

  public static Map<Long, List<Long>> getClassUserListMap(List<UserClass> userClassList) {
    if (!CollectionUtils.isEmpty(userClassList)) {
      return userClassList.stream().collect(groupingBy(UserClass::getClassId,
          Collectors.mapping(UserClass::getUserId, Collectors.toList())));
    }
    return Collections.EMPTY_MAP;
  }

  /**
   * 算校周
   *
   * @param startDate :
   * @param endDate :
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
      if (dayOfWeek == Calendar.SUNDAY || (dayOfWeek != Calendar.SUNDAY
          && calendar.getTime().getTime() == endDate.getTime())) {
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
   * @param endDate :
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
    if (!CollectionUtils.isEmpty(dormitoryUserList)) {
      return dormitoryUserList.stream().collect(
          Collectors.toMap(DormitoryUser::getUserId, DormitoryUser::getDormitoryId, (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }

  public static Map<Long, DormitoryUser> getUserDormitoryRefMap(
      List<DormitoryUser> dormitoryUserList) {
    if (!CollectionUtils.isEmpty(dormitoryUserList)) {
      return dormitoryUserList.stream()
          .collect(Collectors.toMap(DormitoryUser::getUserId, Function.identity(), (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }

  public static Map<Long, List<Long>> getDormitoryUserListMap(
      List<DormitoryUser> dormitoryUserList) {
    if (!CollectionUtils.isEmpty(dormitoryUserList)) {
      return dormitoryUserList.stream().collect(groupingBy(DormitoryUser::getDormitoryId,
          Collectors.mapping(DormitoryUser::getUserId, Collectors.toList())));
    }
    return Collections.EMPTY_MAP;
  }

  public static List<Long> getUserIdsFromDormitoryUserList(List<DormitoryUser> dormitoryUserList) {
    if (!CollectionUtils.isEmpty(dormitoryUserList)) {
      return dormitoryUserList.stream().map(e -> e.getUserId()).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public static List<Long> getUserIdsByCareList(List<Care> careList) {
    if (!CollectionUtils.isEmpty(careList)) {
      return careList.stream().map(e -> e.getStudentId()).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public static boolean dateEqual(ClockDaySetting clockDaySetting, Date date) {
    if (DateUtil.getYearMonthByDate(date) == clockDaySetting.getYearMonth()
        && DateUtil.getDayByDate(date) == clockDaySetting.getDay()) {
      return true;
    } else {
      return false;
    }
  }

  public static List<Long> getUserIdsByStudentClockStatus(
      List<StudentClockStatusDO> studentClockStatusDOList) {
    if (!CollectionUtils.isEmpty(studentClockStatusDOList)) {
      return studentClockStatusDOList.stream().map(e -> e.getStudentId())
          .collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public static Map<Long, List<StudentStatusCountDO>> getStudentStatusCountMap(
      List<StudentStatusCountDO> studentStatusCountDOList) {
    if (!CollectionUtils.isEmpty(studentStatusCountDOList)) {
      return studentStatusCountDOList.stream()
          .collect(groupingBy(StudentStatusCountDO::getStudentId));
    }
    return Collections.EMPTY_MAP;
  }

  public static Map<Long, Integer> getStudentCareCountMap(
      List<StudentCareCountStatDO> studentCareCountStatDOS) {
    if (!CollectionUtils.isEmpty(studentCareCountStatDOS)) {
      return studentCareCountStatDOS.stream().collect(Collectors
          .toMap(StudentCareCountStatDO::getStudentId, StudentCareCountStatDO::getStatCount,
              (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }

  public static long getCurrCheckDormitoryDay(ClockSetting clockSetting) {
    long checkDormStartTime = clockSetting.getCheckDormStartTime();
    long checkDormEndTime = clockSetting.getCheckDormEndTime();
    Date nowDate = new Date();
    long currTime = DateUtil.getHHMMSSByDate(nowDate);

    if (currTime >= checkDormStartTime) {
      return DateUtil.getYearMonthDayByDate(nowDate);
    } else if (currTime < checkDormEndTime) {
      return DateUtil.getYearMonthDayByDate(DateUtil.add(nowDate, Calendar.DAY_OF_YEAR, -1));
    } else {
      return DateUtil.getYearMonthDayByDate(nowDate);
    }

  }

  public static Long getRealTimeStatDay(ClockSetting clockSetting) {
    long startTime = clockSetting.getClockStartTime();
    long checkDormEndTime = clockSetting.getCheckDormEndTime();
    Date nowDate = new Date();
    long currTime = DateUtil.getHHMMSSByDate(nowDate);

    if (currTime >= startTime) {
      return DateUtil.getYearMonthDayByDate(nowDate);
    } else if (currTime < checkDormEndTime) {
      return DateUtil.getYearMonthDayByDate(DateUtil.add(nowDate, Calendar.DAY_OF_YEAR, -1));
    } else {
      return null;
    }
  }

  public static List<ClockAddressSetting> getClockAddressSettingList(
      List<AddressReqDTO> addressReqDTOS) {
    List<ClockAddressSetting> clockAddressSettingList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(addressReqDTOS)) {
      long idStart = DateUtil.uuid();
      for (AddressReqDTO addressReqDTO : addressReqDTOS) {
        ClockAddressSetting setting = new ClockAddressSetting();
        setting.setAddress(addressReqDTO.getName());
        setting.setLat(addressReqDTO.getPosLatitude());
        setting.setLon(addressReqDTO.getPosLongitude());
        setting.setRadius(addressReqDTO.getScope());
        setting.setId(idStart++);
        clockAddressSettingList.add(setting);
      }
    }
    return clockAddressSettingList;
  }

  public static List<Long> getStudentIds(List<StudentClockDTO> studentClockList) {
    if (!CollectionUtils.isEmpty(studentClockList)) {
      return studentClockList.stream().map(StudentClockDTO::getUserId).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public static Map<Long, StudentClock> getStudentClockMap(List<StudentClock> studentClockList) {
    if (!CollectionUtils.isEmpty(studentClockList)) {
      return studentClockList.stream()
          .collect(Collectors.toMap(StudentClock::getClockDate, Function.identity(), (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }

  public static Map<Long, StudentClock> getStudentIdClockMap(List<StudentClock> studentClockList) {
    if (!CollectionUtils.isEmpty(studentClockList)) {
      return studentClockList.stream()
          .collect(Collectors.toMap(StudentClock::getUserId, Function.identity(), (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }

  public static List<Long> getStudentIdsByStudetnDormitoryBuilding(
      List<StudentDormitoryBuildingDO> studentDormitoryBuildingDOList) {
    if (!CollectionUtils.isEmpty(studentDormitoryBuildingDOList)) {
      return studentDormitoryBuildingDOList.stream().map(e -> e.getStudentId())
          .collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public static Map<Long, DormitoryUser> getUserToDormitoryMap(
      List<DormitoryUser> dormitoryUserList) {
    if (!CollectionUtils.isEmpty(dormitoryUserList)) {
      return dormitoryUserList.stream()
          .collect(Collectors.toMap(DormitoryUser::getUserId, Function.identity(), (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }

  public static Set<Long> getBuildingIdsByDormitoryInfo(List<DormitoryInfo> dormitoryInfoList) {
    if (!CollectionUtils.isEmpty(dormitoryInfoList)) {
      return dormitoryInfoList.stream().map(DormitoryInfo::getBuildingId)
          .collect(Collectors.toSet());
    }
    return Collections.EMPTY_SET;
  }

  public static List<ClockDaySetting> getLxClockDay(Date date,
      List<ClockDaySetting> clockDaySettingList) {
    List<ClockDaySetting> lxList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(clockDaySettingList)) {
      clockDaySettingList.sort(new ClockDaySettingCompatator01());
      Collections.reverse(clockDaySettingList);
      for (int i = 0; i < clockDaySettingList.size(); i++) {
        if (ConvertUtil.dateEqual(clockDaySettingList.get(i),
            DateUtil.add(date, Calendar.DAY_OF_YEAR, -1 * i))) {
          lxList.add(clockDaySettingList.get(i));
        } else {
          break;
        }
      }
    }
    return lxList;
  }

  public static List<Long> getLastClassIds(List<Long> orgClassIds, List<Long> majorClassIds,
      List<Long> instructorClassIds) {
    List<Long> lastClassIds = null;
    if (!CollectionUtils.isEmpty(orgClassIds)) {
      lastClassIds = orgClassIds;
      if (!CollectionUtils.isEmpty(majorClassIds)) {
        lastClassIds.retainAll(majorClassIds);
      }
      if (!CollectionUtils.isEmpty(instructorClassIds)) {
        lastClassIds.retainAll(instructorClassIds);
      }
    } else if (!CollectionUtils.isEmpty(majorClassIds)) {
      lastClassIds = majorClassIds;
      if (!CollectionUtils.isEmpty(instructorClassIds)) {
        lastClassIds.retainAll(instructorClassIds);
      }
    } else if (!CollectionUtils.isEmpty(instructorClassIds)) {
      lastClassIds = instructorClassIds;
    }
    return lastClassIds;
  }

}
