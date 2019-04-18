package ink.casual.user.service;

import ink.casual.user.common.model.Account;

/**
 * @author lwt
 * @date 2019/4/12 17:41
 */
public interface AccountService {

    Account register(String mobile,String smsCode);

    boolean updatePassword(String accountId, String password);

    boolean updateAccountName(String accountId, String accountName);

    boolean updateEmail(String accountId, String email, boolean emailStatus);

    Account login(Account account, String smsCode);
}
