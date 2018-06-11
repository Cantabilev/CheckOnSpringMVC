package com.cms.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/3/22 16:26
 *
 * 关于验证码的工具类
 */
public class CaptchaUtil {

    private CaptchaUtil(){}

    /*
     * 随机字符字典
     */
    private static final char[] CHARS = {'0','1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    /*
     * 随机数
     */
    private static Random random = new Random();

    /*
     * 获取6位随机数
     */
    private static String getRandomString()
    {
        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < 4; i++)
        {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }

    /*
     * 获取随机数颜色
     */
    private static Color getRandomColor()
    {
        return new Color(255,255,255);
    }

    /*
     * 返回某颜色的反色
     */
    private static Color getReverseColor(Color c)
    {
        return new Color(255 - c.getRed(), 255 - c.getGreen(),
                255- c.getBlue());
    }

    public static void outputCaptcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("image/jpeg");

        String randomString = getRandomString();
        request.getSession(true).setAttribute("randomString", randomString);
        int width = 154;
        int height = 62;

        Color color = getRandomColor();
        Color reverse = getReverseColor(color);

        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        Font font=new Font(Font.SANS_SERIF, Font.BOLD, 50);
        g.setFont(font);
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.setColor(reverse);
        FontMetrics fm = g.getFontMetrics(font);
        int textWidth = fm.stringWidth(randomString);
        int widthX = (width - textWidth) / 2;
        g.drawString(randomString, widthX, 50);
        for (int i = 0, n = random.nextInt(100); i < n; i++) {
            g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
        }

        // 转成JPEG格式
        ServletOutputStream out = response.getOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(bi);
        out.flush();
    }
}