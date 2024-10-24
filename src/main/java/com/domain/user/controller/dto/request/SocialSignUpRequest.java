package com.domain.user.controller.dto.request;

import com.domain.user.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SocialSignUpRequest {

    @NotBlank
    private String authorizationCode;

    @NotBlank
    private AccountType type;
}
