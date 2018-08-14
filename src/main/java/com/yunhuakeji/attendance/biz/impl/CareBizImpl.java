package com.yunhuakeji.attendance.biz.impl;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.biz.BusinessUtil;
import com.yunhuakeji.attendance.biz.CareBiz;
import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.cache.MajorCacheService;
import com.yunhuakeji.attendance.cache.OrgCacheService;
import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.dao.basedao.model.CollegeInfo;
import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;
import com.yunhuakeji.attendance.dao.basedao.model.UserClass;
import com.yunhuakeji.attendance.dao.bizdao.model.Care;
import com.yunhuakeji.attendance.dto.request.CareUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.DeleteCareReqDTO;
import com.yunhuakeji.attendance.dto.request.StartCareReqDTO;
import com.yunhuakeji.attendance.dto.response.CareTaskBaseInfoDTO;
import com.yunhuakeji.attendance.enums.CareStatus;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.service.baseservice.UserClassService;
import com.yunhuakeji.attendance.service.bizservice.CareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CareBizImpl implements CareBiz {

  @Autowired
  private CareService careService;

  @Autowired
  private UserClassService userClassService;

  @Autowired
  private ClassCacheService classCacheService;

  @Autowired
  private OrgCacheService orgCacheService;

  @Autowired
  private MajorCacheService majorCacheService;

  @Override
  public PagedResult<CareTaskBaseInfoDTO> listByInstructor(Long instructorId, Byte careStatus, Integer pageNo, Integer pageSize) {

    PageInfo pageInfo = careService.pageByInstructor(instructorId, careStatus, pageNo, pageSize);
    List<Care> careList = pageInfo.getList();

    //4.组装结果并返回
    Page<CareTaskBaseInfoDTO> careTaskBaseInfoDTOPage = new Page<>();
    careTaskBaseInfoDTOPage.setPageNo(pageNo);
    careTaskBaseInfoDTOPage.setTotalPages(pageInfo.getPages());
    careTaskBaseInfoDTOPage.setPageSize(pageSize);
    careTaskBaseInfoDTOPage.setTotalCount((int) pageInfo.getTotal());

    List<CareTaskBaseInfoDTO> careTaskBaseInfoDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(pageInfo.getList())) {
      for (Care care : careList) {
        CareTaskBaseInfoDTO dto = convert(care);
        careTaskBaseInfoDTOList.add(dto);
      }
    }
    careTaskBaseInfoDTOPage.setResult(careTaskBaseInfoDTOList);
    return PagedResult.success(careTaskBaseInfoDTOPage);
  }

  private CareTaskBaseInfoDTO convert(Care care) {
    CareTaskBaseInfoDTO dto = new CareTaskBaseInfoDTO();
    dto.setCareId(care.getId());
    dto.setDealDate(care.getDealTime());
    dto.setInstructorId(care.getInstructorId());
    dto.setStudentId(care.getStudentId());
    //TODO dto.setInstructorName();
    dto.setRemark(care.getRemark());
    dto.setStatus(care.getCareStatus().byteValue());
    dto.setTaskDate(care.getOriginateTime());
    //TODO dto.setStudentName();
    //TODO 很多其他的信息

    return dto;
  }

  @Override
  public Result updateCare(CareUpdateReqDTO reqDTO) {
    Care care = new Care();
    care.setId(reqDTO.getCareId());
    care.setRemark(reqDTO.getRemark());
    careService.update(care);
    return Result.success(null);
  }

  @Override
  public PagedResult<CareTaskBaseInfoDTO> listByStudent(Long studentId, Integer pageNo, Integer pageSize) {
    PageInfo pageInfo = careService.pageByStudent(studentId, pageNo, pageSize);
    List<Care> careList = pageInfo.getList();

    //4.组装结果并返回
    Page<CareTaskBaseInfoDTO> careTaskBaseInfoDTOPage = new Page<>();
    careTaskBaseInfoDTOPage.setPageNo(pageNo);
    careTaskBaseInfoDTOPage.setTotalPages(pageInfo.getPages());
    careTaskBaseInfoDTOPage.setPageSize(pageSize);
    careTaskBaseInfoDTOPage.setTotalCount((int) pageInfo.getTotal());

    List<CareTaskBaseInfoDTO> careTaskBaseInfoDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(pageInfo.getList())) {
      for (Care care : careList) {
        CareTaskBaseInfoDTO dto = convert(care);
        careTaskBaseInfoDTOList.add(dto);
      }
    }
    careTaskBaseInfoDTOPage.setResult(careTaskBaseInfoDTOList);
    return PagedResult.success(careTaskBaseInfoDTOPage);
  }

  @Override
  public Result startCare(StartCareReqDTO startCareReqDTO) {

    List<Care> careList = new ArrayList<>();
    List<Long> studentIds = startCareReqDTO.getStudentIds();
    if(!CollectionUtils.isEmpty(studentIds)){
      List<UserClass> userClassList = userClassService.listByUserIds(studentIds);
      Map<Long, Long> userClassMap = BusinessUtil.getUserClassMap(userClassList);
      Map<Long, ClassInfo> classInfoMap = classCacheService.getClassInfoMap();
      Map<Long, MajorInfo> majorInfoMap = majorCacheService.getMajorInfoMap();
      for(Long id:studentIds){
        Care care = new Care();
        care.setStudentId(id);
        care.setOriginatorId(startCareReqDTO.getOperatorId());
        care.setCareStatus(CareStatus.NO.getType());
        care.setOriginateTime(new Date());
        ClassInfo classInfo = classInfoMap.get(userClassMap.get(id));
        if(classInfo!=null){
          care.setMajorId(classInfo.getMajorId());
          care.setInstructorId(classInfo.getInstructorId());
          MajorInfo majorInfo = majorInfoMap.get(classInfo.getMajorId());
          if(majorInfo!=null){
            care.setOrgId(majorInfo.getOrgId());
          }
        }
        careList.add(care);
      }
      careService.batchInsert(careList);
    }
    return Result.success();
  }

  @Override
  public Result deleteCare(DeleteCareReqDTO deleteCareReqDTO) {
    List<Long> careIds = deleteCareReqDTO.getCareIds();
    List<Care> careList = careService.listByIds(careIds);
    if(!CollectionUtils.isEmpty(careList)){
      for(Care care:careList){
        if(CareStatus.YES.getType()==care.getCareStatus()){
          throw new BusinessException(ErrorCode.CARE_CAN_NOT_DELETE);
        }
      }
      careService.batchDelete(careIds);
    }
    return  Result.success();
  }


}
