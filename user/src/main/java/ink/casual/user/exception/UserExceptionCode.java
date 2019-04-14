package ink.casual.user.exception;

import ink.casual.common.exception.CommonReturnCode;
import lombok.Getter;

/**
 * @author lwt
 * @date 2019/4/14 15:31
 */
@Getter
public enum UserExceptionCode implements CommonReturnCode {

    /**
     *
     *
     */
    AUTH_FAIL("21001", "用户名/密码错误。");

    private String code;

    private String msg;

    UserExceptionCode(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
