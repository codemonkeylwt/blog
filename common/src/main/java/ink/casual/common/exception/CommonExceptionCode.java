package ink.casual.common.exception;

import lombok.Getter;

/**
 * @author lwt
 * @date 2019/4/13 17:22
 */
@Getter
public enum CommonExceptionCode implements CommonReturnCode {

    /**
     *
     *
     */
    SMS_VERIFY_FAIL("20001", "短信验证码错误。"),
    PIC_VERIFY_FAIL("20002", "图形验证码错误。");

    private String code;

    private String msg;

    CommonExceptionCode(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
