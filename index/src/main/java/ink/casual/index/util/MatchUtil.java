package ink.casual.index.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lwt
 * @date 2019/4/17 11:03
 */
public class MatchUtil {

    /**
     * 用户名验证规则（字母开头 + 数字/字母/下划线）
     *
     */
    public static final String AccountName_REGEX = "^[A-Za-z][A-Za-z1-9_-]+$";

    /**
     * 邮箱验证规则
     *
     */
    public static final String EMAIL_REGEX = "[a-zA-Z_]+[0-9]*@(([a-zA-z0-9]-*)+\\.){1,3}[a-zA-z\\-]+";

    /**
     * 手机号验证规则
     *
     */
    public static final String MOBILE_REGEX = "^1[3|4|5|8][0-9]\\d{8}$";

    public static boolean matchAccountName(String str){
        return match(str,AccountName_REGEX);
    }

    public static boolean matchEmail(String str){
        return match(str,EMAIL_REGEX);
    }

    public static boolean matchMobile(String str){
        return match(str,MOBILE_REGEX);
    }

    public static boolean match(String str,String regEx){
        // 编译正则表达式,忽略大小写
        Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pat.matcher(str);
        // 字符串是否与正则表达式相匹配
        return matcher.matches();
    }

}
