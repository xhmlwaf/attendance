package com.yunhuakeji.attendance.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import com.yunhuakeji.attendance.aspect.RequestLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * 二维码生成工具类
 */
public class QRCodeUtil {

  private static final Logger logger = LoggerFactory.getLogger(QRCodeUtil.class);

  /**
   * 用字符串生成二维码
   *
   * @param str
   * @return
   * @throws WriterException
   * @author zhouzhe@lenovo-cw.com
   */
  public static BufferedImage Create2DCode(String str, int size) throws WriterException {

    HashMap hints = new HashMap();
    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");    //指定字符编码为“utf-8”
    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  //指定二维码的纠错等级为中级
    hints.put(EncodeHintType.MARGIN, 2);    //设置图片的边距
    /**
     * 生成二维码
     */
    try {
      BitMatrix bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, size, size, hints);
      return toBufferedImage(bitMatrix);
    } catch (Exception e) {
      logger.error("生成二维码错误.", e);
    }
    return null;
  }


  public static BufferedImage toBufferedImage(BitMatrix matrix) {
    int width = matrix.getWidth();
    int height = matrix.getHeight();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setRGB(x, y, matrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
      }
    }
    return image;
  }

  /**
   * 测试代码
   *
   * @throws WriterException
   */
  public static void main(String[] args) throws IOException, WriterException {
    File f = new File("d:\\a.jpg");
    OutputStream outputStream = new FileOutputStream(f);
    ImageIO.write(Create2DCode("f2e3bcdc-9082-4c8e-ace3-2474e003733a", 474), "jpeg", outputStream);
  }
}

