package com.yunhuakeji.attendance.dao.bizdao;

import com.yunhuakeji.attendance.dao.bizdao.model.Care;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorCareCountStat;

import com.yunhuakeji.attendance.dao.bizdao.model.StudentCareCountStatDO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface CareMapper extends Mapper<Care> {
    int insertBatchSelective(List<Care> records);

    List<InstructorCareCountStat> instructorCareCountStat(List<Long> instructorIds);

    List<Care> listByClassIdsAndStatus(Map<String, Object> queryMap);

    List<StudentCareCountStatDO> studentCareCountStat(Map<String,Object> queryMap);


}