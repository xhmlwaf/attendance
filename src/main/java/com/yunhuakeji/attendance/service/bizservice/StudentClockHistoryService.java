package com.yunhuakeji.attendance.service.bizservice;

import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockHistory;

import java.util.List;

public interface StudentClockHistoryService {

  List<StudentClockHistory> list(long studentId, long statDate);

  void batchInsert(List<StudentClockHistory> studentClockHistoryList);
}
