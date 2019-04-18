package ink.casual.index.service.impl;

import ink.casual.index.service.UserService;
import ink.casual.index.util.MatchUtil;
import ink.casual.user.common.model.Account;
import ink.casual.user.common.provider.UserControllerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lwt
 * @date 2019/4/17 11:01
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserControllerProvider userControllerProvider;

    @Override
    public Account register(Account account,String smsCode) {
        return userControllerProvider.register(account,smsCode);
    }

    @Override
    public Account login(Account account,String smsCode) {
        String accountName = account.getAccountName();
        if (!MatchUtil.matchAccountName(accountName)){
            account.setAccountName(null);
        }
        if (MatchUtil.matchEmail(accountName)){
            account.setEmail(accountName);
        }
        if (MatchUtil.matchMobile(accountName)){
            account.setMobile(accountName);
        }
        return userControllerProvider.login(account, smsCode);
    }

}
