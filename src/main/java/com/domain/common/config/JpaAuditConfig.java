package com.domain.common.config;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * CreatedBy, LastModifiedBy 설정
 */
@RequiredArgsConstructor
@EnableJpaAuditing
@Configuration
public class JpaAuditConfig implements AuditorAware<String> {

    private final static String DEFAULT_MODIFIER = "system";

    @Override
    public Optional<String> getCurrentAuditor() {
        // 컨텍스트에 저장된 정보로 값 할당
        return Optional.of(DEFAULT_MODIFIER);
    }
}
