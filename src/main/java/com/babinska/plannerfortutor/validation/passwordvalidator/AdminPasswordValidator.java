package com.babinska.plannerfortutor.validation.passwordvalidator;

import com.babinska.plannerfortutor.user.Role;
import com.babinska.plannerfortutor.validation.ValidationResult;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
class AdminPasswordValidator implements PasswordValidator {

  @Override
  public ValidationResult validate(final String password) {
    final ValidationResult validationResult = ValidationResult.create();

    if (password.length() < 10) {
      validationResult.addError("Password must have at least 10 chars");
    }

    if (password.length() > 25) {
      validationResult.addError("Password can have maximum 20 chars");
    }

    if (password.chars().filter(Character::isDigit).count() < 2) {
      validationResult.addError("Password must contain two digits");
    }

    if (password.chars().noneMatch(Character::isUpperCase)) {
      validationResult.addError("Password must contain upper case letter");
    }

    if (password.chars().noneMatch(Character::isLowerCase)) {
      validationResult.addError("Password must contain lower case letter");
    }

    if (!Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]").matcher(password).find()) {
      validationResult.addError("Password must contain special character");
    }

    if (password.contains(" ")) {
      validationResult.addError("Password cant contain whitespaces");
    }

    return validationResult;
  }

  @Override
  public boolean canValidatePasswordFor(final Role role) {
    return role == Role.ADMIN;
  }

}
