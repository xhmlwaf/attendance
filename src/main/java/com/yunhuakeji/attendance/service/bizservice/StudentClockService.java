package com.yunhuakeji.attendance.service.bizservice;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.dao.bizdao.model.BuildingClockStatDO;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockStatByStatusDO;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockStatByStatusGenderDO;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockHistory;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentClockService {

    List<StudentClock> list(Long studentId, Long clockDate);

    List<StudentClock> listByMonth(Long studentId, Integer year, Integer month);

    void batchInsert(List<StudentClock> studentClockList);

    int count(Long studentId, Byte clockStatus);

    /**
     * 统计一批学生在某天的打卡状态
     *
     * @param studentIds :
     * @param clockDate  :
     * @return : java.util.List<com.yunhuakeji.attendance.dao.bizdao.model.StudentClock>
     */
    List<StudentClock> list(List<Long> studentIds, Long clockDate);

    /**
     * 根据instructorId,buildingIds,clockDate统计
     *
     * @param queryMap :
     * @return : java.util.List<com.yunhuakeji.attendance.dao.bizdao.model.ClockStatByStatusDO>
     */
    List<ClockStatByStatusDO> statByStatus(Map<String, Object> queryMap);

    /**
     * 据instructorId,buildingIds,clockDate,CLOCK_STATUS查询学生ID
     *
     * @param queryMap :
     * @return : java.util.List<java.lang.Long>
     */
    PageInfo<Long> getStudentIds(Map<String, Object> queryMap, int pageNo, int pageSize);

    /**
     * 根据打卡状态和性别统计
     *
     * @param queryMap :
     * @return : java.util.List<com.yunhuakeji.attendance.dao.bizdao.model.ClockStatByStatusGenderDO>
     */
    List<ClockStatByStatusGenderDO> statByStatusGender(Map<String, Object> queryMap);


    List<BuildingClockStatDO> statByBuilding(long statDate);

    StudentClock getById(long id);

    void updateClock(StudentClock studentClock, StudentClockHistory history);
}
