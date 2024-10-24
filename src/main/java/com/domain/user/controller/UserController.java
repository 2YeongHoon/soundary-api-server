package com.domain.user.controller;

import com.domain.common.dto.SuccessResponse;
import com.domain.common.utils.SuccessResponseUtils;
import com.domain.user.controller.dto.request.UpdateRequest;
import com.domain.user.controller.dto.response.UserInfoResponse;
import com.domain.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @GetMapping(value = "/me")
    public ResponseEntity<SuccessResponse<UserInfoResponse>> me() {
        return SuccessResponseUtils.successResponse(authService.me());
    }

    @PutMapping
    public ResponseEntity<SuccessResponse> update(@RequestBody @Valid UpdateRequest request) {
        String loginId = authService.getLoginId();
        authService.update(request, loginId);
        return SuccessResponseUtils.successResponse();
    }
}
