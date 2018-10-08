package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.dao.basedao.model.CollegeInfo;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryInfo;
import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockStatusDO;
import com.yunhuakeji.attendance.dto.response.StudentBaseInfoDTO;
import com.yunhuakeji.attendance.enums.ClockStatus;
import java.util.List;
import java.util.Map;
import org.springframework.util.CollectionUtils;

public class CommonBiz {

  public static boolean calcLxNum(List<StudentClockStatusDO> studentClockStatusDOList,
      List<Long> needQueryList) {
    if (!CollectionUtils.isEmpty(studentClockStatusDOList)) {
      needQueryList.clear();
      for (StudentClockStatusDO x : studentClockStatusDOList) {
        if (x.getClockStatus() != null && ClockStatus.STAYOUT.getType() == x.getClockStatus()) {
          needQueryList.add(x.getStudentId());
          x.setLxStayOut(x.getLxStayOut());
        } else if (x.getClockStatus() != null && ClockStatus.STAYOUT_LATE.getType() == x
            .getClockStatus()) {
          needQueryList.add(x.getStudentId());
          x.setLxStayOutLate(x.getLxStayOutLate());
        }
      }
    } else {
      return true;
    }
    return false;
  }

  public static void setMajorAndCollegeInfo(List<Long> instructorIds,
      Map<Long, MajorInfo> majorInfoMap,
      Map<Long, CollegeInfo> collegeInfoMap, StudentBaseInfoDTO dto, ClassInfo classInfo) {
    if (classInfo != null) {
      dto.setClassName(classInfo.getClassCode());
      dto.setInstructorId(classInfo.getInstructorId());
      instructorIds.add(classInfo.getInstructorId());
      dto.setMajorId(classInfo.getMajorId());
      MajorInfo majorInfo = majorInfoMap.get(classInfo.getMajorId());
      setMajorAndCollege(collegeInfoMap, dto, majorInfo);
    }
  }

  public static void setMajorAndCollege(Map<Long, CollegeInfo> collegeInfoMap,
      StudentBaseInfoDTO dto, MajorInfo majorInfo) {
    if (majorInfo != null) {
      dto.setMajorName(majorInfo.getName());
      dto.setCollegeId(majorInfo.getOrgId());
      CollegeInfo collegeInfo = collegeInfoMap.get(majorInfo.getOrgId());
      if (collegeInfo != null) {
        dto.setCollegeName(collegeInfo.getName());
      }
    }
  }

  public static void setDormitoryAndBuilding(Map<Long, BuildingInfo> buildingInfoMap,
      StudentBaseInfoDTO studentBaseInfoDTO, DormitoryInfo dormitoryInfo) {
    if (dormitoryInfo != null) {
      studentBaseInfoDTO.setBuildingId(dormitoryInfo.getBuildingId());
      studentBaseInfoDTO.setDormitoryName(dormitoryInfo.getName());
      BuildingInfo buildingInfo = buildingInfoMap.get(dormitoryInfo.getBuildingId());
      if (buildingInfo != null) {
        studentBaseInfoDTO.setBuildingName(buildingInfo.getName());
      }
    }
  }
}
