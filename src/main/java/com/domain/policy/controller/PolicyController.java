package com.domain.policy.controller;

import com.domain.common.dto.SuccessResponse;
import com.domain.common.utils.SuccessResponseUtils;
import com.domain.policy.controller.dto.response.RetrievePolicyResponse;
import com.domain.policy.service.PolicyManagementService;
import com.domain.policy.service.PolicyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/policy")
@RestController
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;
    private final PolicyManagementService policyManagementService;

    @GetMapping
    public ResponseEntity<List<RetrievePolicyResponse>> retrieve() {
        return ResponseEntity.ok(policyService.getLatestPolicyList());
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> create(@RequestBody List<Long> policyIds) {
        policyManagementService.create(policyIds);
        return SuccessResponseUtils.successResponse();
    }

}
