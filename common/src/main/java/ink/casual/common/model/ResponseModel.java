package ink.casual.common.model;

import lombok.Data;

/**
 * @author lwt
 * @date 2019/1/28 17:39
 * 统一响应返回模型
 */
@Data
public class ResponseModel<T> {

    String code = CommonReturnCodeEnum.SYSTEM_ERROR.getCode();

    T data;

    String msg;

    public ResponseModel() {
    }

    public ResponseModel(String code) {
        this.code = code;
    }

    public ResponseModel(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
