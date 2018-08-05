package com.yunhuakeji.attendance.dao.bizdao;

import com.yunhuakeji.attendance.dao.bizdao.model.UserOrgRef;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserOrgRefMapper extends Mapper<UserOrgRef> {
    int insertBatchSelective(List<UserOrgRef> records);
}