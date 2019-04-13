package ink.casual.common.model;

import lombok.Getter;

/**
 * @author lwt
 * @date 2019/4/12 17:50
 * 系统前缀枚举
 */
@Getter
public enum PreFixEnum {

    /**
     * 账户ID前缀
     * 短信key前缀
     * 邮件key前缀
     *
     */
    ACCOUNT_ID("A"),SMS("SMS_"),EMAIL("EMAIL_"),PIC("PIC_");

    private String preFix;

    PreFixEnum(String preFix){
        this.preFix = preFix;
    }

}
