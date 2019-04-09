package ink.casual.index.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lwt
 * @date 2019/4/9 16:15
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @PostMapping
    public String register(){
        return "ok";
    }

}
