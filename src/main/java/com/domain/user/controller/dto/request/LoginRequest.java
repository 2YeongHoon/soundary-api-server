package com.domain.user.controller.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank @Email String loginId, @NotBlank String password) {

}
