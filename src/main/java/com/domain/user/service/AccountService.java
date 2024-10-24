package com.domain.user.service;

import com.domain.common.enums.Errors;
import com.domain.common.exception.BaseDuplicateException;
import com.domain.common.exception.BaseNotFoundException;
import com.domain.user.domain.Account;
import com.domain.user.service.port.AccountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 계정 서비스
 */
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Optional<Account> findByLoginId(String loginId) {
        return accountRepository.findByLoginId(loginId);
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String loginId) {
        return findByLoginId(loginId).isPresent();
    }

    @Transactional(readOnly = true)
    public Account getByLoginId(String loginId) {
        return findByLoginId(loginId)
            .orElseThrow(() -> new BaseNotFoundException(Errors.NOT_FOUND_ACCOUNT));
    }

    @Transactional(readOnly = true)
    public void findByLoginIdAndThrowIfExists(String loginId) {
        if (existsByEmail(loginId)) {
            throw new BaseDuplicateException(Errors.DUPLICATED_EMAIL);
        }
    }

}
