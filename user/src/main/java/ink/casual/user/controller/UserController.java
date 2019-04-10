package ink.casual.user.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lwt
 * @date 2019/4/10 9:46
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @PostMapping(value = "/{string}")
    public String test(@PathVariable("string") String string){
        return "OK:".concat(string);
    }

}
