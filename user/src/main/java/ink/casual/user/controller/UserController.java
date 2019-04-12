package ink.casual.user.controller;

import ink.casual.user.common.model.Account;
import ink.casual.user.common.provider.UserControllerProvider;
import ink.casual.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lwt
 * @date 2019/4/10 9:46
 */
@RestController
@RequestMapping(value = "/user")
public class UserController implements UserControllerProvider {

    @Autowired
    private AccountService accountService;

    @Override
    @PostMapping
    public Account register(Account account){
        return accountService.register(account);
    }

}
