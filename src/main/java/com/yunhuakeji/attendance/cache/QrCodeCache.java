package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.util.QRCodeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Component
public class QrCodeCache {

  private static final Logger logger = LoggerFactory.getLogger(QrCodeCache.class);

  private BufferedImage image;

  private String qrCode;
  private Timer timer;

  /**
   * 延迟20s执行
   */
  private long delay = 20 * 1000;

  @Value("${qrcode.active}")
  private int qrcodeActive = 2000;

  /**
   * 生成token
   *
   * @return
   */
  private static String GetGUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  public QrCodeCache() {
    try {
      timer = new Timer();
      timer.schedule(new TimerTask() {
        @Override
        public void run() {
          String qrCode = GetGUID();
          setQrCode(qrCode);
          try {
            image = QRCodeUtil.Create2DCode(qrCode, 474);
          } catch (Exception e) {
            logger.error("生成二维码出错.", e);
          }
        }
      }, delay, qrcodeActive * 1000);
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
    return qrCode.equals(getQrCode());
  }

  public String getQrCode() {
    return qrCode;
  }
}


