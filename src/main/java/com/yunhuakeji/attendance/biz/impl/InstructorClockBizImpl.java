package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.InstructorClockBiz;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorClock;
import com.yunhuakeji.attendance.dto.request.InstructorClockReqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorClockStatRsqDTO;
import com.yunhuakeji.attendance.service.bizservice.InstructorClockService;
import com.yunhuakeji.attendance.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class InstructorClockBizImpl implements InstructorClockBiz {

    @Autowired
    private InstructorClockService instructorClockService;

    @Override
    public Result<InstructorClockStatRsqDTO> statByInstructor(Long instructorId) {
        int statCount = instructorClockService.statByInstructor(instructorId);
        InstructorClockStatRsqDTO rsqDTO = new InstructorClockStatRsqDTO();
        rsqDTO.setTotalClockCount(statCount);
        return Result.success(rsqDTO);
    }

    @Override
    public Result instructorClock(InstructorClockReqDTO req) {

        String qrCode = req.getQrCode();
        //TODO 校验二维码是否正确

        int yearMonthDay = DateUtil.getYearMonthDayForCurrDay();
        List<InstructorClock> instructorClockList =
                instructorClockService.list(req.getInstructorId(), yearMonthDay);
        if (!CollectionUtils.isEmpty(instructorClockList)) {
            //TODO 已经打卡了
        }
        InstructorClock instructorClock = new InstructorClock();
        instructorClock.setClockTime(new Date());
        instructorClock.setInstructorId(req.getInstructorId());
        instructorClock.setStatDate((long) yearMonthDay);
        instructorClockService.save(instructorClock);

        return Result.success(null);
    }
}
