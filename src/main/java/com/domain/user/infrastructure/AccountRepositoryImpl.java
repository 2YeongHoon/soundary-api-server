package com.domain.user.infrastructure;

import com.domain.user.domain.Account;
import com.domain.user.service.port.AccountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;

    @Override
    public Optional<Account> findByLoginId(String loginId) {
        return accountJpaRepository.findByLoginId(loginId);
    }

    @Override
    public void update(Account account) {
        accountJpaRepository.save(account);
    }
}
