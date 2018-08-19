package com.yunhuakeji.attendance.dao.bizdao;

import com.yunhuakeji.attendance.dao.bizdao.model.*;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface StudentClockMapper extends Mapper<StudentClock> {
    int insertBatchSelective(List<StudentClock> records);

    /**
     * 根据instructorId,buildingIds,clockDate统计 startClockDate endClockDate
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
    List<Long> getStudentIds(Map<String, Object> queryMap);

    /**
     * 根据打卡状态和性别统计
     *
     * @param queryMap :
     * @return : java.util.List<com.yunhuakeji.attendance.dao.bizdao.model.ClockStatByStatusGenderDO>
     */
    List<ClockStatByStatusGenderDO> statByStatusGender(Map<String, Object> queryMap);


    List<BuildingClockStatDO> statByBuilding(@Param("statDate") long statDate);

    /**
     * 范围list IDS ，clockDate ，clockStatus
     * @param queryMap
     * @return
     */
    List<Long> listStudentIdsByIdsAndStatusAndDate(Map<String,Object> queryMap);

    /**
     * studentIds,startClockDate,endClockDate,clockStatus
     * @param queryMap
     * @return
     */
    List<StudentClockStatusCountStatDO> listStudentClockStatusCountStat(Map<String,Object> queryMap);

    /**
     * 统计指定日期学生打卡状态，classIds,clockDate,clockStatus
     *
     * @param queryMap :
     * @return : java.util.List<com.yunhuakeji.attendance.dao.bizdao.model.StudentClockStatusDO>
     */
    List<StudentClockStatusDO> statStudentClockStatus(Map<String,Object> queryMap);

    /**
     * startClockDate,endClockDate,orgId
     *
     * @param queryMap :
     * @return : java.util.List<com.yunhuakeji.attendance.dao.bizdao.model.DateStatusCountStatDO>
     */
    List<DateStatusCountStatDO> dateStatusCountStat(Map<String,Object> queryMap);

    /**
     * startClockDate,endClockDate,classIds
     *
     * @param queryMap :
     * @return : java.util.List<com.yunhuakeji.attendance.dao.bizdao.model.StudentStatusCountDO>
     */
    List<StudentStatusCountDO> studentStatusCountStat(Map<String,Object> queryMap);

    /**
     * userIds,startClockDate,endClockDate
     *
     * @param queryMap :
     * @return : java.util.List<com.yunhuakeji.attendance.dao.bizdao.model.StudentStatusCountDO>
     */
    List<StudentStatusCountDO> studentStatusCountStatByStudentIds(Map<String,Object> queryMap);


}