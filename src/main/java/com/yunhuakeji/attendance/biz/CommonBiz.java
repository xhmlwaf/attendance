package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockStatusDO;
import com.yunhuakeji.attendance.enums.ClockStatus;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class CommonBiz {

  public static boolean calcLxNum(List<StudentClockStatusDO> studentClockStatusDOList, List<Long> needQueryList) {
    if (!CollectionUtils.isEmpty(studentClockStatusDOList)) {
      needQueryList.clear();
      for (StudentClockStatusDO x : studentClockStatusDOList) {
        if (x.getClockStatus() != null && ClockStatus.STAYOUT.getType() == x.getClockStatus()) {
          needQueryList.add(x.getStudentId());
          x.setLxStayOut(x.getLxStayOut());
        } else if (x.getClockStatus() != null && ClockStatus.STAYOUT_LATE.getType() == x.getClockStatus()) {
          needQueryList.add(x.getStudentId());
          x.setLxStayOutLate(x.getLxStayOutLate());
        }
      }
    } else {
      return true;
    }
    return false;
  }
}
