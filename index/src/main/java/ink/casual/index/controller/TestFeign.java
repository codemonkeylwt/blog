package ink.casual.index.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author lwt
 * @date 2019/4/10 17:49
 */
@Component
@FeignClient(name = "blog-user",path = "/user")
public interface TestFeign {

    @PostMapping(value = "/{string}")
    String test(@PathVariable("string") String string);

}
