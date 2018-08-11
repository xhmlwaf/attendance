package com.yunhuakeji.attendance.service.baseservice;

import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;

import java.util.List;

public interface ClassInfoService {

    /**
     * 插入记录
     *
     * @param record
     * @return
     */
    int insert(ClassInfo record);

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
    int updateByPrimaryKeySelective(ClassInfo record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    ClassInfo selectByPrimaryKey(String id);

    /**
     * 根据ID列表查询
     *
     * @param ids
     * @return
     */
    List<ClassInfo> selectByPrimaryKeyList(List<String> ids);

    List<ClassInfo> listAll();

    /**
     * 分页查询
     *
     * @param record
     * @param pageNo
     * @param pageSize
     * @param orderByClause
     * @return
     */
    Page<ClassInfo> selectByRecordForPage(ClassInfo record, int pageNo, int pageSize, String orderByClause);

    /**
     * 列表查询
     *
     * @param record
     * @return
     */
    List<ClassInfo> selectByRecordForList(ClassInfo record);

    /**
     * 根据参数分页查询
     *
     * @param instructorId
     * @param majorId
     * @param state
     * @param pageNo
     * @param pageSize
     * @param orderByClause
     * @return
     */
    Page<ClassInfo> selectByParamsForPage(Long instructorId, Long majorId, String state, int pageNo, int pageSize, String orderByClause);

    /**
     * 根据参数列表查询
     *
     * @param instructorId
     * @param majorId
     * @param state
     * @return
     */
    List<ClassInfo> selectByParamsForList(Long instructorId, Long majorId, String state);

    List<ClassInfo> select(Long instructorId, List<Long> majorIds);

}