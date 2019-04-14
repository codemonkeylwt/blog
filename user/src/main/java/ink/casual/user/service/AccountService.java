package ink.casual.user.service;

import ink.casual.user.common.model.Account;

/**
 * @author lwt
 * @date 2019/4/12 17:41
 */
public interface AccountService {

    Account register(Account account);

    Account login(Account account);
}
