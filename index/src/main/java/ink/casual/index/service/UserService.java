package ink.casual.index.service;

import ink.casual.user.common.model.Account;

/**
 * @author lwt
 * @date 2019/4/17 11:00
 */
public interface UserService {

    Account register(Account account,String smsCode);

    Account login(Account account,String smsCode);

}
