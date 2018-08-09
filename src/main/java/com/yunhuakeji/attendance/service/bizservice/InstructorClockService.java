package com.yunhuakeji.attendance.service.bizservice;

import com.yunhuakeji.attendance.dao.bizdao.model.InstructorClock;

import java.util.List;

public interface InstructorClockService {

  int statByInstructor(long instructorId);

  List<InstructorClock> list(long instructorId, long clockDate);

  List<InstructorClock> list(long instructorId, int year, int month);

  int save(InstructorClock instructorClock);
}