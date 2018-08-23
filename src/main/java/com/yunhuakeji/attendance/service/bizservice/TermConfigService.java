package com.yunhuakeji.attendance.service.bizservice;

import com.yunhuakeji.attendance.dao.bizdao.model.TermConfig;

import java.util.List;

public interface TermConfigService {

  TermConfig getCurrTermConfig();

  void insert(TermConfig termConfig);

  List<TermConfig> listAll();

}
