package com.domain.policy.service;

import com.domain.policy.domain.Policy;
import com.domain.user.domain.Account;
import com.domain.user.domain.UserPolicy;
import com.domain.user.service.AccountService;
import com.domain.user.service.AuthService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PolicyManagementService {

    private final AuthService authService;
    private final AccountService accountService;
    private final PolicyService policyService;

    public void create(List<Long> policyIds) {
        final String loginId = authService.getLoginId();
        Account account = accountService.getByLoginId(loginId);

        List<Policy> policyList = policyService.getAllByPolicyId(policyIds);

        for (Policy policy : policyList) {
            UserPolicy userPolicy = UserPolicy.of(account.getUser(), policy);
            policy.addUserPolicy(userPolicy);
        }

        policyService.saveAll(policyList);
    }
}
