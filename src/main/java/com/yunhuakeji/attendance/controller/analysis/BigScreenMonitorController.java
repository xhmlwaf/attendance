package com.yunhuakeji.attendance.controller.analysis;

import com.yunhuakeji.attendance.biz.BigScreenMonitorBiz;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.BigScreenMonitorStatRspDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(value = "大屏监控模块接口")
@RestController
public class BigScreenMonitorController {

    @Autowired
    private BigScreenMonitorBiz bigScreenMonitorBiz;

    @GetMapping("/big-screen-monitor/data-stat")
    @ApiOperation(value = "大屏幕数据统计")
    public Result<BigScreenMonitorStatRspDTO> getBigScreenMonitorStat() {

        return bigScreenMonitorBiz.getBigScreenMonitorStat();
    }

    @GetMapping("/big-screen-monitor/copy-writing")
    @ApiOperation(value = "大屏幕文案")
    public Result<String> getCopyWriting() {

        return bigScreenMonitorBiz.getCopyWriting();
    }

    @GetMapping("/big-screen-monitor/qrcode-image")
    @ApiOperation(value = "二维码图片")
    public void getQrcodeImg(HttpServletResponse response) {
        bigScreenMonitorBiz.getQrcodeImg(response);
    }

}
