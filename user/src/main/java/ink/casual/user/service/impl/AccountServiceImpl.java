package ink.casual.user.service.impl;

import ink.casual.common.model.PreFixEnum;
import ink.casual.common.util.RSAUtil;
import ink.casual.common.util.SnowflakeIdWorker;
import ink.casual.user.common.model.Account;
import ink.casual.user.mapper.AccountMapper;
import ink.casual.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lwt
 * @date 2019/4/12 17:42
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public Account register(Account account) {
        account.setAccountId(PreFixEnum.ACCOUNT_ID.getPreFix() + snowflakeIdWorker.nextId());
        new String(RSAUtil.decryptByPrivateKey(account.getPassword(),""));
        accountMapper.insert(account);
        account.setPassword(null);
        account.setRegisterDate(null);
        account.setLcd(null);
        return account;
    }

}
