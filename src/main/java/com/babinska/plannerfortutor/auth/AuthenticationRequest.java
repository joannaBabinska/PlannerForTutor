package com.babinska.plannerfortutor.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
class AuthenticationRequest {

  @Email
  @NotNull
  private String email;

  @NotBlank
  private String password;
}
