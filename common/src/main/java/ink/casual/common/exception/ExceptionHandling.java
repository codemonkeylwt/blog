package ink.casual.common.exception;

import ink.casual.common.model.CommonReturnCodeEnum;
import ink.casual.common.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lwt
 * @date 2019/4/16 15:30
 */
@ControllerAdvice
public class ExceptionHandling {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandling.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseModel<String> systemExceptionHandler(Exception ex) {
        LOGGER.error("has error",ex);
        ResponseModel<String> responseModel = new ResponseModel<>();
        responseModel.setCode(CommonReturnCodeEnum.SYSTEM_ERROR.getCode());
        responseModel.setMsg(ex.getLocalizedMessage());
        return responseModel;
    }

    @ResponseBody
    @ExceptionHandler(value = CustomException.class)
    public ResponseModel<String> custonExceptionHandler(CustomException ex) {
        LOGGER.warn("result:{},param:{}",ex.getMsg(),ex.getMessage());
        ResponseModel<String> responseModel = new ResponseModel<>();
        responseModel.setCode(ex.getCode());
        responseModel.setMsg(ex.getMsg());
        return responseModel;
    }
}
