package ink.casual.index.controller;

import ink.casual.user.common.model.Account;
import ink.casual.user.common.provider.UserControllerProvider;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserControllerProvider userControllerProvider;

    @PostMapping
    public Account register(Account account){
        return userControllerProvider.register(account);
    }

}
