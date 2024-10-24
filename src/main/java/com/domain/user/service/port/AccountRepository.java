package com.domain.user.service.port;

import com.domain.user.domain.Account;
import java.util.Optional;

public interface AccountRepository {

    Optional<Account> findByLoginId(String loginId);
    void update(Account account);
}
