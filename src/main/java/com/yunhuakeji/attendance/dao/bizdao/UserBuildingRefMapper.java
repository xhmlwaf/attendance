package com.yunhuakeji.attendance.dao.bizdao;

import com.yunhuakeji.attendance.dao.bizdao.model.UserBuildingRef;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserBuildingRefMapper extends Mapper<UserBuildingRef> {
    int insertBatchSelective(List<UserBuildingRef> records);
}