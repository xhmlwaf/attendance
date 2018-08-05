package com.yunhuakeji.attendance.service.baseservice;

import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryInfo;

import java.util.List;

public interface DormitoryInfoService {

    /**
     * 插入记录
     *
     * @param record
     * @return
     */
    int insert(DormitoryInfo record);

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 根据主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(DormitoryInfo record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    DormitoryInfo selectByPrimaryKey(String id);

    /**
     * 根据ID列表查询
     *
     * @param ids
     * @return
     */
    List<DormitoryInfo> selectByPrimaryKeyList(List<String> ids);

    List<DormitoryInfo> listAll();

    /**
     * 分页查询
     *
     * @param record
     * @param pageNo
     * @param pageSize
     * @param orderByClause
     * @return
     */
    Page<DormitoryInfo> selectByRecordForPage(DormitoryInfo record, int pageNo, int pageSize, String orderByClause);

    /**
     * 列表查询
     *
     * @param record
     * @return
     */
    List<DormitoryInfo> selectByRecordForList(DormitoryInfo record);

    /**
     * 根据参数分页查询
     *
     * @param buildingId
     * @param floorNumber
     * @param state
     * @param pageNo
     * @param pageSize
     * @param orderByClause
     * @return
     */
    Page<DormitoryInfo> selectByParamsForPage(Long buildingId, Short floorNumber, String state, int pageNo, int pageSize, String orderByClause);

    /**
     * 根据参数列表查询
     *
     * @param buildingId
     * @param floorNumber
     * @param state
     * @return
     */
    List<DormitoryInfo> selectByParamsForList(Long buildingId, Short floorNumber, String state);

}