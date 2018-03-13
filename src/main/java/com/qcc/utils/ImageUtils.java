package com.qcc.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ImageUtils {
    public final static String WIDTH = "width";
    public final static String HEIGHT = "height";
    /**
     * 采用指定宽度、高度或压缩比例 的方式对图片进行压缩
     * @param imgdist 目标图片地址
     * @param rate 压缩比例
     */
    public static void reduceImg(InputStream inputStream, File dest, Float rate) {
        int widthdist = 0, heightdist = 0;
        FileOutputStream out = null;
        BufferedImage src = null;
        try {

            // 如果rate不为空说明是按比例压缩
            src = javax.imageio.ImageIO.read(inputStream);
            if (rate != null && rate > 0) {
                // 获取文件高度和宽度
                    widthdist = (int) (src.getWidth(null) * rate);
                    heightdist = (int) (src.getHeight(null) * rate);
            }
            // 开始读取文件并进行压缩
            BufferedImage tag = new BufferedImage((int) widthdist,
                    (int) heightdist, BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(
                    src.getScaledInstance(widthdist, heightdist,
                            Image.SCALE_SMOOTH), 0, 0, null);

            out = new FileOutputStream(dest);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally {
            if (src != null) {
            }
        }
    }
}
