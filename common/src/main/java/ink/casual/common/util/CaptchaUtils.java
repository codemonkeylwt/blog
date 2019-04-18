package ink.casual.common.util;

import com.google.common.base.Strings;
import ink.casual.common.exception.CommonExceptionCode;
import ink.casual.common.exception.CustomException;
import ink.casual.common.model.PreFixEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.util.Objects;
import java.util.Random;

/**
 * 类名称: CaptchaUtils
 * 类描述: 验证码工具类
 *
 */
public class CaptchaUtils {

    private static Logger logger = LoggerFactory.getLogger(CaptchaUtils.class);

    private static String propertiesName;

    private static RedisService redisService;

    /**
     * 生产环境配置变量名
     */
    private static final String PROD = "prod";
    /**
     * 预发环境配置变量名
     */
    private static final String PRE = "pre";
    /**
     * 开发/测试环境固定短信验证码
     */
    private static final String SMS_CODE = "0000";

    public static void setRedisService(RedisService redisService){
        CaptchaUtils.redisService = redisService;
    }

    public static void setPropertiesName(String propertiesName){
        CaptchaUtils.propertiesName = propertiesName;
    }

    /**
     * 通过手机号校验短信验证码
     * @param mobile 手机号
     * @param smsCode 输入的验证码
     */
    public static void validatorSMSCode(String mobile, String smsCode)  {
        String smsSessionId = PreFixEnum.SMS.getPreFix().concat(mobile);
        if (PROD.equals(propertiesName) || PRE.equals(propertiesName)) {
            Object redisSmsCode = redisService.get(smsSessionId);
            if (null == redisSmsCode) {
                throw new CustomException(CommonExceptionCode.SMS_VERIFY_FAIL,mobile,smsCode);
            }
            if (redisSmsCode.toString().equals(smsCode)) {
                redisService.deleteKey(smsSessionId);
            }else {
                throw new CustomException(CommonExceptionCode.SMS_VERIFY_FAIL,mobile,smsCode);
            }
        }else {
            Object redisSmsCode = redisService.get(smsSessionId);
            if (null == redisSmsCode) {
                throw new CustomException(CommonExceptionCode.SMS_VERIFY_FAIL,mobile,smsCode);
            }
            if (SMS_CODE.equals(smsCode)) {
                redisService.deleteKey(smsSessionId);
            }else {
                throw new CustomException(CommonExceptionCode.SMS_VERIFY_FAIL,mobile,smsCode);
            }
        }
    }

    /**
     * 生成6位短信验证码
     * @param mobile 手机号
     * @return 6位短信验证码
     */
    public static String generateSMSCode(String mobile){
        String smsSessionId = PreFixEnum.SMS.getPreFix().concat(mobile);
        String code = Objects.requireNonNullElseGet(redisService.get(smsSessionId), () -> String.valueOf((int) ((Math.random() * 9 + 1) * 100000)));
        logger.info("----手机号码: {} ---- 短信验证码: {}",  mobile, code);
        redisService.set(smsSessionId, code,  Duration.ofMinutes(5));
        return code;
    }

    /**
     * 生成图形验证码
     *
     * @param mobile 手机号
     * @return 图形验证码
     */
    public static BufferedImage generatorCaptcha(String mobile) {
        //系统-验证码宽度
        int captchaW = Integer.parseInt(redisService.getSysValue("captcha_num_with"));
        //系统-验证码高度
        int captchaH = Integer.parseInt(redisService.getSysValue("captcha_num_height"));
        //系统-验证码是否增加干扰线【Y-是，N-否】
        String noise = redisService.getSysValue("captcha_noise");
        MyCaptcha myCaptcha = new MyCaptcha();
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(captchaW, captchaH, BufferedImage.TYPE_INT_BGR);
        // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics graphics = image.getGraphics();
        graphics.fillRect(0, 0, captchaW, captchaH);
        graphics.setColor(myCaptcha.getRandColor(110, 133));
        if ("Y".equals(noise)) {
            // 绘制干扰线
            for (int i = 0; i <= myCaptcha.lineSize; i++) {
                myCaptcha.drowLine(graphics, captchaW, captchaH);
            }
        }
        //验证码
        String picCode = "";
        //获取图形验证码位数
        int length = getRandom(4);
        for (int i = 1; i <= length; i++) {
            picCode = myCaptcha.drowString(graphics, picCode, i);
        }
        //销毁
        graphics.dispose();
        //生成图形验证码的key
        String picKey = PreFixEnum.PIC.getPreFix().concat(mobile);
        //设置图形验证码过期时间
        redisService.set(picKey, picCode, Duration.ofMinutes(5));
        logger.info("-------picKey:{}-------picCode:{}-------", length, picKey, picCode);
        return image;
    }

    /**
     * 获取图片验证码长度
     *
     */
    private static int getRandom(int max) {
        int min = 4;
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 图片验证码验证
     *
     * @param picCode 图形验证码
     * @param mobile 手机号
     */
    public static void validPic(String mobile, String picCode) {
        String picSessionId = PreFixEnum.PIC.getPreFix().concat(mobile);
        String redisPicCode = redisService.get(picSessionId);
        if (!Strings.isNullOrEmpty(picCode) && picCode.equalsIgnoreCase(redisPicCode)) {
            redisService.deleteKey(picSessionId);
        }else {
            throw new CustomException(CommonExceptionCode.PIC_VERIFY_FAIL,mobile,picCode);
        }
    }

}

