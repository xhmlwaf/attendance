package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.service.bizservice.RedisService;
import com.yunhuakeji.attendance.util.DateUtil;
import com.yunhuakeji.attendance.util.QRCodeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class QrCodeCache {

  private static final Logger logger = LoggerFactory.getLogger(QrCodeCache.class);

  private BufferedImage image;

  /**
   * 二维码大小
   */
  private int IMG_SIZE = 474;
  private String qrCode;

  private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

  @Autowired
  private RedisService redisService;

  /**
   * 延迟20s执行
   */
  private long delay = 20 * 1000;
  private final String QR_CODE_KEY = "qrCodeKey_";

  /**
   * 生成周期可配置（单位秒）
   */
  @Value("${qrcode.active}")
  private int qrcodeActive = 20;

  /**
   * 生成打卡token
   *
   * @return
   */
  private static String GetGUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  public QrCodeCache() {
    try {
      int second = DateUtil.getCurrSecond();
      delay = 20 - second % 20;
      String currTime = DateUtil.currHhmmssToLong() + "";
      scheduledExecutorService.scheduleAtFixedRate(() -> {
        String qrCode = GetGUID();
        String result = redisService.getAndSet(QR_CODE_KEY + currTime, qrCode);
        redisService.expire(QR_CODE_KEY + currTime, 60, TimeUnit.SECONDS);
        if (result == null) {
          setQrCode(qrCode);
        } else {
          setQrCode(result);
        }
        try {
          image = QRCodeUtil.create2DCode(qrCode, IMG_SIZE);
        } catch (Exception e) {
          logger.error("生成二维码出错.", e);
        }

      }, delay, qrcodeActive, TimeUnit.SECONDS);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public BufferedImage getImage() {
    return image;
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

  public static void main(String[] args) {
    int second = DateUtil.getCurrSecond();
    int delay = 20 - second % 20;
    int period = 20;
    System.out.println("现在秒：" + second);
    System.out.println("延迟秒：" + delay);
//    Timer timer = new Timer();
//    timer.schedule(new TimerTask() {
//      @Override
//      public void run() {
//        System.out.println("当前秒:"+DateUtil.getCurrSecond());
//      }
//    }, delay, 20 * 1000);
//

    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    scheduledExecutorService.scheduleAtFixedRate(() -> {
      System.out.println("当前秒:" + DateUtil.getCurrSecond());
    }, delay, period, TimeUnit.SECONDS);


  }

}


