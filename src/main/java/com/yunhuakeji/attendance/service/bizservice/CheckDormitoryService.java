package com.yunhuakeji.attendance.service.bizservice;

import com.yunhuakeji.attendance.dao.bizdao.model.CheckDormitory;

import java.util.List;

public interface CheckDormitoryService {

  List<CheckDormitory> list(List<Long> dormitoryIds, long statDate);
}
