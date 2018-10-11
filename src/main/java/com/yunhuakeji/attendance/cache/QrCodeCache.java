package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.service.bizservice.RedisService;
import com.yunhuakeji.attendance.util.DateUtil;
import com.yunhuakeji.attendance.util.QRCodeUtil;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class QrCodeCache implements ApplicationListener<ContextRefreshedEvent> {

  private static final Logger logger = LoggerFactory.getLogger(QrCodeCache.class);

  private BufferedImage image;

  /**
   * 二维码大小
   */
  private int IMG_SIZE = 474;
  private String qrCode;

  @Autowired
  private RedisService redisService;

  /**
   * 延迟20s执行
   */
  private long delay;
  private final String QR_CODE_KEY = "qrCodeKey_";

  /**
   * 生成周期可配置（单位秒）
   */
  @Value("${qrcode.active}")
  private int qrcodeActive = 20;

  private Timer timer;

  /**
   * 生成打卡token
   *
   * @return
   */
  private static String GetGUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  public QrCodeCache() {

  }

  public BufferedImage getImage() {
    return image;
  }

  public String getQrCode() {
    return qrCode;
  }

  private void setQrCode(String qrCode) {
    this.qrCode = qrCode;
  }

  public boolean checkQrCode(String qrCode) {
    if (qrCode == null) {
      return false;
    }
    return qrCode.equals(this.qrCode);
  }


  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    try {
      int second = DateUtil.getCurrSecond();
      delay = 20 - second % 20;
      String currTime = DateUtil.currHhmmssToLong() + "";
      timer = new Timer();
      timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
          try {
            String qrCode = GetGUID();
            String result = redisService.getAndSet(QR_CODE_KEY + currTime, qrCode);
            redisService.expire(QR_CODE_KEY + currTime, 60, TimeUnit.SECONDS);
            if (result == null) {
              setQrCode(qrCode);
            } else {
              setQrCode(result);
            }
            logger.info("最终的二维码:" + getQrCode());

            image = QRCodeUtil.create2DCode(getQrCode(), IMG_SIZE);
          } catch (Exception e) {
            logger.error("生成二维码出错.", e);
          }
        }
      }, delay * 1000, qrcodeActive * 1000);

    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
  }
}


