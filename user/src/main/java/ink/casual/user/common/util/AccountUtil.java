package ink.casual.user.common.util;

import ink.casual.common.model.PreFixEnum;
import ink.casual.common.util.RedisService;
import ink.casual.user.common.model.Account;

import java.time.Duration;

/**
 * @author lwt
 * @date 2019/4/14 15:02
 */
public class AccountUtil {

    private static RedisService redisService;

    public static void setRedisService(RedisService redisService) {
        AccountUtil.redisService = redisService;
    }

    public static boolean isLogin(String accountId){
        return redisService.hasKey(PreFixEnum.SESSION.getPreFix().concat(accountId));
    }

    public static void checkInLogin(Account account){
        redisService.set(PreFixEnum.SESSION.getPreFix().concat(String.valueOf(account.getAccountId())),account, Duration.ofDays(3));
    }

    public static void removeLogin(String accountId){
        redisService.deleteKey(PreFixEnum.SESSION.getPreFix().concat(accountId));
    }

    public static boolean verifyPassword(String p1,String p2){
        return p1.equals(p2);
    }

}
