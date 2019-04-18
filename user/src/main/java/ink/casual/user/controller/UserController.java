package ink.casual.user.controller;

import ink.casual.user.common.model.Account;
import ink.casual.user.common.provider.UserControllerProvider;
import ink.casual.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Account register(@RequestParam("mobile") String mobile, @RequestParam("smsCode") String smsCode){
        return accountService.register(mobile,smsCode);
    }

    @Override
    @PostMapping("/login")
    public Account login(@RequestBody Account account, @RequestParam("smsCode") String smsCode){
        return accountService.login(account,smsCode);
    }

}
