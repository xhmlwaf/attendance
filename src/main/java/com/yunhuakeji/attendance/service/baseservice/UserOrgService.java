package com.yunhuakeji.attendance.service.baseservice;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.dao.basedao.model.UserOrg;

import java.util.List;

public interface UserOrgService {

  /**
   * 插入记录
   *
   * @param record
   * @return
   */
  int insert(UserOrg record);

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
  int updateByPrimaryKeySelective(UserOrg record);


  List<UserOrg> selectByUserIds(List<Long> userIds);

  List<UserOrg> selectByUserId(Long userId);

  List<UserOrg> selectByOrgId(Long orgId);


  PageInfo<UserOrg> selectByOrgIdForPage(Long orgId, Integer pageNo, Integer pageSize);
}