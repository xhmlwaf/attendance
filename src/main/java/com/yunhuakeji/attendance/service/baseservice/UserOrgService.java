package com.yunhuakeji.attendance.service.baseservice;

import com.yunhuakeji.attendance.dao.basedao.model.UserOrg;

import java.util.List;

public interface UserOrgService {

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(UserOrg record);

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
    int updateByPrimaryKeySelective(UserOrg record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    UserOrg selectByPrimaryKey(String id);

    /**
     * 根据ID列表查询
     * @param ids
     * @return
     */
    List<UserOrg> selectByPrimaryKeyList(List<String> ids);


}