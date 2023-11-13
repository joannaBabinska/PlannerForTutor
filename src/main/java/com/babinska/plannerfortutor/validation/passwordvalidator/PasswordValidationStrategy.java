package com.babinska.plannerfortutor.validation.passwordvalidator;

import com.babinska.plannerfortutor.exception.PasswordToWeakException;
import com.babinska.plannerfortutor.user.Role;
import com.babinska.plannerfortutor.validation.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PasswordValidationStrategy {

  private final List<PasswordValidator> passwordValidators;

  public void validate(String password, Role role) {
    final ValidationResult validationResult = passwordValidators.stream()
        .filter(passwordValidator -> passwordValidator.canValidatePasswordFor(role))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("No validator found for: " + role))
        .validate(password);

    if (validationResult.hasErrors()) {
      String messages = String.join(", ", validationResult.getErrors());
      throw new PasswordToWeakException("Password is not strong enough: " +  messages);
    }
  }

}
