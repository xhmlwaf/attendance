package com.yunhuakeji.attendance.service.baseservice;

import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;

import java.util.List;

public interface MajorInfoService {

    /**
     * 插入记录
     *
     * @param record
     * @return
     */
    int insert(MajorInfo record);

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
    int updateByPrimaryKeySelective(MajorInfo record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    MajorInfo selectByPrimaryKey(Long id);

    /**
     * 根据ID列表查询
     *
     * @param ids
     * @return
     */
    List<MajorInfo> selectByPrimaryKeyList(List<String> ids);

    /**
     * 列出所有
     * @return
     */
    List<MajorInfo> listAll();

    /**
     * 分页查询
     *
     * @param record
     * @param pageNo
     * @param pageSize
     * @param orderByClause
     * @return
     */
    Page<MajorInfo> selectByRecordForPage(MajorInfo record, int pageNo, int pageSize, String orderByClause);

    /**
     * 列表查询
     *
     * @param record
     * @return
     */
    List<MajorInfo> selectByRecordForList(MajorInfo record);

    /**
     * 根据参数分页查询
     *
     * @param orgId
     * @param state
     * @param pageNo
     * @param pageSize
     * @param orderByClause
     * @return
     */
    Page<MajorInfo> selectByParamsForPage(Long orgId, String state, int pageNo, int pageSize, String orderByClause);

    /**
     * 根据参数列表查询
     *
     * @param orgId
     * @param state
     * @return
     */
    List<MajorInfo> selectByParamsForList(Long orgId, String state);

}