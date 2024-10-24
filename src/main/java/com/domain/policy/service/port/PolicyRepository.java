package com.domain.policy.service.port;

import com.domain.policy.domain.Policy;
import java.util.List;

public interface PolicyRepository {

    void saveAll(List<Policy> policyList);
    List<Policy> getAllByPolicyId(List<Long> ids);
    List<Policy> getLatestPolicyList();
}
