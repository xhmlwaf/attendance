package com.yunhuakeji.attendance.cache;

import com.google.zxing.WriterException;

import com.yunhuakeji.attendance.util.QRCodeUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Component
public class QrCodeCache {

  private BufferedImage image;

  private Timer timer;

  /**
   * 延迟20s执行
   */
  private long delay = 20 * 1000;

  @Value("${qrcode.active}")
  private int qrcodeActive = 20;

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
          try {
            image = QRCodeUtil.createQrCode(qrCode, 500);
          } catch (WriterException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
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
}
