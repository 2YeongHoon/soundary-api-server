package com.domain.user.infrastructure;


import com.domain.user.domain.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByLoginId(String loginId);

}
