package ink.casual.user.config;

import ink.casual.common.util.CaptchaUtils;
import ink.casual.common.util.RedisService;
import ink.casual.user.common.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author lwt
 * @date 2019/4/15 16:16
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private RedisService redisService;
    @Value("${profiles}")
    private String propertiesName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AccountUtil.setRedisService(redisService);
        CaptchaUtils.setRedisService(redisService);
        CaptchaUtils.setPropertiesName(propertiesName);
    }

}
