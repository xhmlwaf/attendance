package com.yunhuakeji.attendance.dao.basedao;

import com.yunhuakeji.attendance.dao.basedao.model.StatStudentByGender;
import com.yunhuakeji.attendance.dao.basedao.model.User;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

    List<User> getStudentForListByClassIdsAndBuildingId(@Param("classIds") List<Long> classIds, @Param("buildingId") Long buildingId, @Param("nameOrCode") String nameOrCode);

    List<User> getStudentForListByNameOrCode(@Param("nameOrCode") String nameOrCode);
}