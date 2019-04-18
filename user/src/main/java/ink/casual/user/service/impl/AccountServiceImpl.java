package ink.casual.user.service.impl;

import com.alibaba.nacos.common.util.Md5Utils;
import com.google.common.base.Strings;
import ink.casual.common.exception.CustomException;
import ink.casual.common.util.CaptchaUtils;
import ink.casual.common.util.RSAUtil;
import ink.casual.common.util.RedisService;
import ink.casual.common.util.SnowflakeIdWorker;
import ink.casual.user.common.model.Account;
import ink.casual.user.common.util.AccountUtil;
import ink.casual.user.exception.UserExceptionCode;
import ink.casual.user.mapper.AccountMapper;
import ink.casual.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

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

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Account register(String mobile,String smsCode) {
        CaptchaUtils.validatorSMSCode(mobile,smsCode);
        Account account = new Account();
        account.setAccountId(snowflakeIdWorker.nextId());
        account.setMobile(mobile);
        mongoTemplate.save(account);
        AccountUtil.checkInLogin(account);
        return account;
    }

    @Override
    public boolean updatePassword(String accountId, String password){
        AccountUtil.isLogin(accountId);
        Account account = new Account();
        account.setPassword(getMD5Password(password));
        updateAccount(accountId, account);
        return true;
    }

    @Override
    public boolean updateAccountName(String accountId, String accountName){
        AccountUtil.isLogin(accountId);
        Account account = new Account();
        account.setAccountName(accountName);
        updateAccount(accountId, account);
        return true;
    }

    @Override
    public boolean updateEmail(String accountId, String email, boolean emailStatus){
        AccountUtil.isLogin(accountId);
        Account account = new Account();
        account.setEmail(email);
        account.setEmailStatus(emailStatus);
        updateAccount(accountId, account);
        return true;
    }

    private void updateAccount(String accountId, Account account) {
        account.setLcd(new Date());
        accountMapper.updateByExampleSelective(account, new Example(Account.class).createCriteria().andEqualTo("accountId", accountId));
    }

    @Override
    public Account login(Account account,String smsCode) {
        Account accountInDb;
        if (Strings.isNullOrEmpty(smsCode)) {
            account.setPassword(getMD5Password(account.getPassword()));
            accountInDb = mongoTemplate.findOne(new Query(Criteria.byExample(account)), account.getClass());
        }else {
            CaptchaUtils.validatorSMSCode(account.getMobile(),smsCode);
            accountInDb = mongoTemplate.findOne(new Query(Criteria.byExample(account)), account.getClass());
        }
        if (accountInDb == null) {
            throw new CustomException(UserExceptionCode.AUTH_FAIL);
        }
        accountInDb.setPassword(null);
        AccountUtil.checkInLogin(accountInDb);
        return accountInDb;
    }

    private String getMD5Password(String password){
        return Md5Utils.getMD5(RSAUtil.decryptByPrivateKey(password, redisService.getSysValue(RSAUtil.PRIVATE_KEY)));
    }

}
