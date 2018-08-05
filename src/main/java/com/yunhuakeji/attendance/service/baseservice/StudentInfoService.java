package com.yunhuakeji.attendance.service.baseservice;

import com.yunhuakeji.attendance.dao.basedao.model.StudentInfo;

import java.util.List;

public interface StudentInfoService {

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(StudentInfo record);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(StudentInfo record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    StudentInfo selectByPrimaryKey(String id);

    /**
     * 根据ID列表查询
     * @param ids
     * @return
     */
    List<StudentInfo> selectByPrimaryKeyList(List<String> ids);


}