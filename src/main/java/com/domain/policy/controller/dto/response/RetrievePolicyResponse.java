package com.domain.policy.controller.dto.response;

import com.domain.common.enums.YesOrNo;
import com.domain.policy.domain.Policy;
import lombok.Getter;

@Getter
public class RetrievePolicyResponse {

    private final Long policyId;
    private final String code;
    private final String name;
    private final int version;
    private final String content;
    private final YesOrNo requiredYn;
    private final YesOrNo useYn;

    private RetrievePolicyResponse(Policy policy) {
        this.policyId = policy.getId();
        this.code = policy.getCode();
        this.name = policy.getName();
        this.version = policy.getVersion();
        this.content = policy.getContent();
        this.requiredYn = policy.getRequiredYn();
        this.useYn = policy.getUserYn();
    }

    public static RetrievePolicyResponse from(Policy policy) {
        return new RetrievePolicyResponse(policy);
    }
}
