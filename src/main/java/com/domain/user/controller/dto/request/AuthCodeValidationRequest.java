package com.domain.user.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthCodeValidationRequest(@Email String email, @NotBlank String authCode) {

}
