package com.yunhuakeji.attendance.service.baseservice;

import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;

import java.util.List;

public interface BuildingInfoService {

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(BuildingInfo record);

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
    int updateByPrimaryKeySelective(BuildingInfo record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    BuildingInfo selectByPrimaryKey(String id);

    /**
     * 根据ID列表查询
     * @param ids
     * @return
     */
    List<BuildingInfo> selectByPrimaryKeyList(List<String> ids);

    List<BuildingInfo> listAll();

    /**
    * 分页查询
    * @param record
    * @param pageNo
    * @param pageSize
    * @param orderByClause
    * @return
    */
    Page<BuildingInfo> selectByRecordForPage(BuildingInfo record, int pageNo, int pageSize, String orderByClause);

    /**
    * 列表查询
    * @param record
    * @return
    */
	List<BuildingInfo> selectByRecordForList(BuildingInfo record);

    /**
    * 根据参数分页查询
    * @param type
    * @param universityId
    * @param state
    * @param pageNo
    * @param pageSize
    * @param orderByClause
    * @return
    */
    Page<BuildingInfo> selectByParamsForPage(Byte type, Long universityId, String state, int pageNo, int pageSize, String orderByClause);

    /**
    * 根据参数列表查询
    * @param type
    * @param universityId
    * @param state
    * @return
    */
    List<BuildingInfo> selectByParamsForList(Byte type, Long universityId, String state);

}