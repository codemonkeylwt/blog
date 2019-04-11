package ink.casual.user.common.provider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author lwt
 * @date 2019/4/11 9:09
 */
@Component
@FeignClient(value = "blog-user",path = "/user")
public interface UserControllerProvider {

    @PostMapping(value = "/{string}")
    String test(@PathVariable("string") String string);

}
