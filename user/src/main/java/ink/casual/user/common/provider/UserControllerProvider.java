package ink.casual.user.common.provider;

import ink.casual.user.common.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author lwt
 * @date 2019/4/11 9:09
 */
@FeignClient(value = "blog-user",path = "/user")
public interface UserControllerProvider {

    /**
     * 账号注册接口
     *
     */
    @PostMapping
    Account register(Account account);

}
