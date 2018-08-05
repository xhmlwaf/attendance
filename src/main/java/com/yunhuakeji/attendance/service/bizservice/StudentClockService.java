package com.yunhuakeji.attendance.service.bizservice;

import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;

import java.util.List;

public interface StudentClockService {

    List<StudentClock> list(Long studentId, Long clockDate);

    List<StudentClock> listByMonth(Long studentId, Integer year, Integer month);

    void batchInsert(List<StudentClock> studentClockList);

    int count(Long studentId, Byte clockStatus);
}
