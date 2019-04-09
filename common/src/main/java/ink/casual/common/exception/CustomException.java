package ink.casual.common.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author lwt
 * @date 2019/3/22
 */
public class CustomException extends RuntimeException {

    private CommonReturnCode returnCodeEnum;

    public CustomException(CommonReturnCode returnCodeEnum, Object... param){
        super(returnCodeEnum != null ? Arrays.stream(param).map(Object::toString).collect(Collectors.toList()).toString() : "");
        this.returnCodeEnum = returnCodeEnum;
    }

    public String getCode(){
        return returnCodeEnum.getCode();
    }

    public String getMsg(){
        return returnCodeEnum.getMsg();
    }

}
