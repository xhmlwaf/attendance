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

    /**
     * 根据辅导员ID统计应打卡学生数量
     *
     * @param instructorId : 
     * @return : int
     */
    int countClockStudentByInstructorId(Long instructorId);

    /**
     * 统计所有应打卡学生数量
     *
     * @return : int
     */
    int countAllClockStudent();

    /**
     * 根据宿舍ID列表统计应打卡学生数量
     *
     * @param buildingIds : 
     * @return : int
     */
    int countClockStudentByBuildingIds(List<Long> buildingIds);


}