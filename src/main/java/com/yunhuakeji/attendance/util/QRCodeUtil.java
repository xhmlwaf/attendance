package com.yunhuakeji.attendance.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QRCodeUtil {

  private static final Logger logger = LoggerFactory.getLogger(QRCodeUtil.class);

  public static BufferedImage create2DCode(String str, int size) throws WriterException {

    HashMap hints = new HashMap();
    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");    //指定字符编码为“utf-8”
    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  //指定二维码的纠错等级为中级
    hints.put(EncodeHintType.MARGIN, 2);    //设置图片的边距

    try {
      BitMatrix bitMatrix = new MultiFormatWriter()
          .encode(str, BarcodeFormat.QR_CODE, size, size, hints);
      return toBufferedImage(bitMatrix);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
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
}

