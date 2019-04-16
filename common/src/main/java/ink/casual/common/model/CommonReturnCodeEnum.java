package ink.casual.common.model;

import ink.casual.common.exception.CommonReturnCode;
import lombok.Getter;

/**
 * @author lwt
 * @date 2019/4/16 17:14
 */
@Getter
public enum CommonReturnCodeEnum implements CommonReturnCode {

    /**
     *
     *
     */
    SYSTEM_ERROR("1000", "系统错误。");

    private String code;

    private String msg;

    CommonReturnCodeEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
