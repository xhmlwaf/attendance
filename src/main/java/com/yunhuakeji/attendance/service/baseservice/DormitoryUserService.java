package com.yunhuakeji.attendance.service.baseservice;

import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryUser;

import java.util.List;

public interface DormitoryUserService {

  /**
   * 插入记录
   *
   * @param record
   * @return
   */
  int insert(DormitoryUser record);

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
  int updateByPrimaryKeySelective(DormitoryUser record);

  /**
   * 根据主键查询
   *
   * @param id
   * @return
   */
  DormitoryUser selectByPrimaryKey(String id);

  /**
   * 根据ID列表查询
   *
   * @param ids
   * @return
   */
  List<DormitoryUser> selectByPrimaryKeyList(List<String> ids);

  /**
   * 分页查询
   *
   * @param record
   * @param pageNo
   * @param pageSize
   * @param orderByClause
   * @return
   */
  Page<DormitoryUser> selectByRecordForPage(DormitoryUser record, int pageNo, int pageSize, String orderByClause);

  /**
   * 列表查询
   *
   * @param record
   * @return
   */
  List<DormitoryUser> selectByRecordForList(DormitoryUser record);

  /**
   * 根据参数分页查询
   *
   * @param dormitoryId
   * @param userId
   * @param state
   * @param pageNo
   * @param pageSize
   * @param orderByClause
   * @return
   */
  Page<DormitoryUser> selectByParamsForPage(Long dormitoryId, Long userId, String state, int pageNo, int pageSize, String orderByClause);

  /**
   * 根据参数列表查询
   *
   * @param dormitoryId
   * @param userId
   * @param state
   * @return
   */
  List<DormitoryUser> selectByParamsForList(Long dormitoryId, Long userId, String state);

  List<DormitoryUser> listByDormitoryIds(List<Long> dormitoryIds);

  List<DormitoryUser> listByDormitoryId(long dormitoryId);

  List<DormitoryUser> listByUserIds(List<Long> userIds);

}