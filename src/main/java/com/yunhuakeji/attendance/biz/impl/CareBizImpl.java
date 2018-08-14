package com.yunhuakeji.attendance.biz.impl;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.biz.CareBiz;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.bizdao.model.Care;
import com.yunhuakeji.attendance.dto.request.CareUpdateReqDTO;
import com.yunhuakeji.attendance.dto.response.CareTaskBaseInfoDTO;
import com.yunhuakeji.attendance.service.bizservice.CareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CareBizImpl implements CareBiz {

  @Autowired
  private CareService careService;

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


}
