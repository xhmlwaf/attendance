package com.yunhuakeji.attendance.service.baseservice;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.model.InstructorInfo;
import com.yunhuakeji.attendance.dao.basedao.model.UserClass;

import java.util.List;

public interface UserClassService {

    /**
     * 插入记录
     *
     * @param record
     * @return
     */
    int insert(UserClass record);


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
    int updateByPrimaryKeySelective(UserClass record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    UserClass selectByPrimaryKey(String id);

    /**
     * 根据ID列表查询
     *
     * @param ids
     * @return
     */
    List<UserClass> selectByPrimaryKeyList(List<String> ids);

    /**
     * 分页查询
     *
     * @param record
     * @param pageNo
     * @param pageSize
     * @param orderByClause
     * @return
     */
    Page<UserClass> selectByRecordForPage(UserClass record, int pageNo, int pageSize, String orderByClause);

    /**
     * 列表查询
     *
     * @param record
     * @return
     */
    List<UserClass> selectByRecordForList(UserClass record);

    /**
     * 根据参数分页查询
     *
     * @param classId
     * @param state
     * @param pageNo
     * @param pageSize
     * @param orderByClause
     * @return
     */
    Page<UserClass> selectByParamsForPage(Long classId, String state, int pageNo, int pageSize, String orderByClause);

    /**
     * 根据参数列表查询
     *
     * @param classId
     * @param state
     * @return
     */
    List<UserClass> selectByParamsForList(Long classId, String state);

    /**
     * 根据用户ID查询列表
     *
     * @param userIds :
     * @return : java.util.List<com.yunhuakeji.attendance.dao.basedao.model.UserClass>
     */
    List<UserClass> listByUserIds(List<Long> userIds);

    PageInfo<InstructorInfo> listInstructorInfo(String nameOrCode, int pageNo, int pageSize);

}