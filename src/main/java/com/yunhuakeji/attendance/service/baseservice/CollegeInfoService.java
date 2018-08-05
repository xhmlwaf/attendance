package com.yunhuakeji.attendance.service.baseservice;

import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.model.CollegeInfo;

import java.util.List;

public interface CollegeInfoService {

    /**
     * 插入记录
     *
     * @param record
     * @return
     */
    int insert(CollegeInfo record);


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
    int updateByPrimaryKeySelective(CollegeInfo record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    CollegeInfo selectByPrimaryKey(String id);

    /**
     * 根据ID列表查询
     *
     * @param ids
     * @return
     */
    List<CollegeInfo> selectByPrimaryKeyList(List<String> ids);


    /**
     * 查询所有数据
     * @return
     */
    List<CollegeInfo> listAll();



    /**
     * 分页查询
     *
     * @param record
     * @param pageNo
     * @param pageSize
     * @param orderByClause
     * @return
     */
    Page<CollegeInfo> selectByRecordForPage(CollegeInfo record, int pageNo, int pageSize, String orderByClause);

    /**
     * 列表查询
     *
     * @param record
     * @return
     */
    List<CollegeInfo> selectByRecordForList(CollegeInfo record);

    /**
     * 根据参数分页查询
     *
     * @param parentOrgId
     * @param type
     * @param universityId
     * @param state
     * @param pageNo
     * @param pageSize
     * @param orderByClause
     * @return
     */
    Page<CollegeInfo> selectByParamsForPage(Long parentOrgId, Short type, Long universityId, String state, int pageNo, int pageSize, String orderByClause);

    /**
     * 根据参数列表查询
     *
     * @param parentOrgId
     * @param type
     * @param universityId
     * @param state
     * @return
     */
    List<CollegeInfo> selectByParamsForList(Long parentOrgId, Short type, Long universityId, String state);

}