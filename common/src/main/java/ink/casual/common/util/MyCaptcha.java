package ink.casual.common.util;

import java.awt.*;
import java.util.Random;

/**
 * 验证码图片生成工具
 *  使用JAVA生成的图片验证码，调用getRandcode方法获取图片验证码，以流的方式传输到前端页面。
 *
 */
class MyCaptcha {

    private Random random = new Random();

    /**
     * 随机产生的字符串
     *
     */
    private static final String RANDOM_STRING = "abcdefgh0123456789";

    /**
     * 干扰线数量
     *
     */
    int lineSize = 40;

    /**
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.BOLD, 18);
    }

    /**
     * 获得颜色
     */
    Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 绘制字符串
     */
    String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(RANDOM_STRING.length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    void drowLine(Graphics g, int captchaW, int captchaH) {
        int x = random.nextInt(captchaW);
        int y = random.nextInt(captchaH);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    private String getRandomString(int num) {
        return String.valueOf(RANDOM_STRING.charAt(num));
    }

}