package com.yunhuakeji.attendance.dao.basedao;

import com.yunhuakeji.attendance.dao.basedao.model.DormitoryInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DormitoryInfoMapper extends Mapper<DormitoryInfo> {

    List<Long> listDormitoryByInstructorId(@Param("instructorId") Long instructorId);
}