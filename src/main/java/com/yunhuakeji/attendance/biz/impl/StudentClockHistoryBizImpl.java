package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.StudentClockHistoryBiz;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockHistory;
import com.yunhuakeji.attendance.dto.response.StudentClockHistoryQueryRspDTO;
import com.yunhuakeji.attendance.service.bizservice.StudentClockHistoryService;
import com.yunhuakeji.attendance.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentClockHistoryBizImpl implements StudentClockHistoryBiz {

  @Autowired
  private StudentClockHistoryService studentClockHistoryService;

  @Override
  public Result<List<StudentClockHistoryQueryRspDTO>> listAll(Long studentId, Date date) {
    List<StudentClockHistory> studentClockHistoryList = studentClockHistoryService.list(studentId, DateUtil.getYearMonthDayByDate(date));
    List<StudentClockHistoryQueryRspDTO> list = new ArrayList<>();
    if (!CollectionUtils.isEmpty(studentClockHistoryList)) {
      for (StudentClockHistory sch : studentClockHistoryList) {
        list.add(convert(sch));
      }
    }
    return Result.success(list);
  }

  private StudentClockHistoryQueryRspDTO convert(StudentClockHistory sch) {
    StudentClockHistoryQueryRspDTO dto = new StudentClockHistoryQueryRspDTO();
    dto.setAppName(sch.getAppName());
    dto.setClockStatus(sch.getClockStatus());
    dto.setOperateTime(sch.getOperateTime());
    dto.setOperatorId(sch.getOperatorId());
    dto.setOperatorName(sch.getOperatorName());
    return dto;
  }
}
