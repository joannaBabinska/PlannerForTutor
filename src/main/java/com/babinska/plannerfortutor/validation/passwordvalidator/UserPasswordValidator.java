package com.babinska.plannerfortutor.validation.passwordvalidator;

import com.babinska.plannerfortutor.user.Role;
import com.babinska.plannerfortutor.validation.ValidationResult;
import org.springframework.stereotype.Component;

@Component
class UserPasswordValidator implements PasswordValidator {

  @Override
  public ValidationResult validate(final String password) {
    final ValidationResult validationResult = ValidationResult.create();

    if (password.length() < 8) {
      validationResult.addError("Password must have at least 8 chars");
    }

    if (password.length() > 20) {
      validationResult.addError("Password can have maximum 20 chars");
    }

    if (password.chars().noneMatch(Character::isDigit)) {
      validationResult.addError("Password must contain digit");
    }

    if (password.chars().noneMatch(Character::isUpperCase)) {
      validationResult.addError("Password must contain upper case letter");
    }

    if (password.chars().noneMatch(Character::isLowerCase)) {
      validationResult.addError("Password must contain lower case letter");
    }

    if (password.contains(" ")) {
      validationResult.addError("Password cant contain whitespaces");
    }

    return validationResult;
  }

  @Override
  public boolean canValidatePasswordFor(final Role role) {
    return role == Role.USER;
  }

}
