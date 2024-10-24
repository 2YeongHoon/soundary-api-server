package com.domain.policy.infrastructure;

import com.domain.policy.domain.Policy;
import com.domain.policy.service.port.PolicyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PolicyRepositoryImpl implements PolicyRepository {

    private final PolicyJpaRepository policyJpaRepository;

    @Override
    public void saveAll(List<Policy> policyList) {
        policyJpaRepository.saveAll(policyList);
    }

    @Override
    public List<Policy> getAllByPolicyId(List<Long> ids) {
        return policyJpaRepository.findAllById(ids);
    }

    @Override
    public List<Policy> getLatestPolicyList() {
        return policyJpaRepository.getLatestPolicyList();
    }
}
