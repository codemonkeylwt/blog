package ink.casual.user.service.impl;

import com.alibaba.nacos.common.util.Md5Utils;
import ink.casual.common.exception.CustomException;
import ink.casual.common.model.PreFixEnum;
import ink.casual.common.util.RSAUtil;
import ink.casual.common.util.RedisService;
import ink.casual.common.util.SnowflakeIdWorker;
import ink.casual.user.common.model.Account;
import ink.casual.user.common.util.AccountUtil;
import ink.casual.user.exception.UserExceptionCode;
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

    @Autowired
    private RedisService redisService;

    @Override
    public Account register(Account account) {
        account.setAccountId(PreFixEnum.ACCOUNT_ID.getPreFix()+snowflakeIdWorker.nextId());
        account.setPassword(getMD5Password(account.getPassword()));
        accountMapper.insert(account);
        account = accountMapper.selectOne(account);
        account.setPassword(null);
        AccountUtil.checkInLogin(account);
        return account;
    }

    @Override
    public Account login(Account account) {
        Account account1 = accountMapper.selectOne(account);
        if(AccountUtil.verifyPassword(getMD5Password(account.getPassword()), account1.getPassword())){
            account1.setPassword(null);
            AccountUtil.checkInLogin(account1);
            return account1;
        }else {
            throw new CustomException(UserExceptionCode.AUTH_FAIL);
        }
    }

    private String getMD5Password(String password){
        return Md5Utils.getMD5(RSAUtil.decryptByPrivateKey(password, redisService.getSysValue(RSAUtil.PUBLIC_KEY)));
    }

}
