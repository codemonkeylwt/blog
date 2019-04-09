package ink.casual.common.exception;

/**
 * @author lwt
 * @date 2019/3/22 9:50
 */
public interface CommonReturnCode {
    /**
     * 获得错误码
     * @return 错误码
     */
    String getCode();

    /**
     * 获得错误信息
     * @return 错误信息
     */
    String getMsg();
}
