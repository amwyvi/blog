package com.scs.web.blog.util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author
 * @ClassName VerifyCode
 * @Description 验证码生成
 * @Date 2019/11/18
 * @Version 1.0
 **/
public class ImageUtil {
    public static BufferedImage getImage(int width, int height, String content) {
        //创建指定大小和图片模式的缓冲图片对象
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //绘图对象
        Graphics2D graphics = (Graphics2D) img.getGraphics();
        //设置颜色
        graphics.setColor(new Color(117, 171, 216));
        //绘制填充矩形
        graphics.fillRect(0, 0, width, height);
        //设置画笔颜色
        graphics.setPaint(new Color(102, 109, 111));
        //设置字体
        Font font = new Font("微软雅黑", Font.BOLD, 40);
        graphics.setFont(font);
        //在指定位置绘制字符串
        graphics.drawString(content, width / 3, height / 2);
        return img;
    }
}
