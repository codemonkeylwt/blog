package ink.casual.index.controller;

import ink.casual.index.service.UserService;
import ink.casual.user.common.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lwt
 * @date 2019/4/9 16:15
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Account register(@RequestBody Account account, @RequestParam("smsCode") String smsCode){
        return userService.register(account,smsCode);
    }

    @PostMapping("/login")
    public Account login(@RequestBody Account account, @RequestParam("smsCode") String smsCode){
        return userService.login(account, smsCode);
    }
}
