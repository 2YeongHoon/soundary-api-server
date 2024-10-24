package com.domain.policy.service;

import com.domain.policy.controller.dto.response.RetrievePolicyResponse;
import com.domain.policy.domain.Policy;
import com.domain.policy.service.port.PolicyRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;

    public List<RetrievePolicyResponse> getLatestPolicyList() {
        List<Policy> policyList = policyRepository.getLatestPolicyList();

        List<RetrievePolicyResponse> result = policyList.stream()
            .map(this::toRetrievePolicyResponse)
            .collect(Collectors.toList());

        return result;
    }

    public List<Policy> getAllByPolicyId(List<Long> ids) {
        return policyRepository.getAllByPolicyId(ids);
    }

    public void saveAll(List<Policy> policyList) {
        policyRepository.saveAll(policyList);
    }

    private RetrievePolicyResponse toRetrievePolicyResponse(Policy policy) {
        return RetrievePolicyResponse.from(policy);
    }
}
