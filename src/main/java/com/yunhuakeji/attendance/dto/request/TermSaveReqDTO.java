package com.yunhuakeji.attendance.dto.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TermSaveReqDTO {

  private Byte termNumber;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date startDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date endDate;

}
