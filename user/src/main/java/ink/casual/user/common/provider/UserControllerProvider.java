package ink.casual.user.common.provider;

import ink.casual.user.common.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    Account register(@RequestParam("smsCode") String mobile, @RequestParam("smsCode") String smsCode);

    @PostMapping("/login/{smsCode}")
    Account login(@RequestBody Account account, @PathVariable("smsCode") String smsCode);
}
