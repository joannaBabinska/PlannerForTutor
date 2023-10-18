package com.babinska.plannerfortutor.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotBlank
  @Size(min = 3, max = 50)
  private String firstname;

  @NotBlank
  @Size(min = 3, max = 50)
  private String lastname;

  @Email
  @NotNull
  private String email;

  @NotBlank
  private String password;
}
