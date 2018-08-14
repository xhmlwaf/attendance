package com.yunhuakeji.attendance.service.bizservice;

import com.yunhuakeji.attendance.dao.bizdao.model.TermConfig;

public interface TermConfigService {

  TermConfig getCurrTermConfig();

  void insert(TermConfig termConfig);
}
