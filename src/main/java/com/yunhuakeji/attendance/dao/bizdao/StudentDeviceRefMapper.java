package com.yunhuakeji.attendance.dao.bizdao;

import com.yunhuakeji.attendance.dao.bizdao.model.StudentDeviceRef;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentDeviceRefMapper extends Mapper<StudentDeviceRef> {
    int insertBatchSelective(List<StudentDeviceRef> records);
}