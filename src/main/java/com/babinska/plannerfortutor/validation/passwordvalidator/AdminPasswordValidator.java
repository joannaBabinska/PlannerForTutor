package com.babinska.plannerfortutor.validation.passwordvalidator;

import com.babinska.plannerfortutor.user.Role;
import com.babinska.plannerfortutor.validation.ValidationResult;
import com.babinska.plannerfortutor.vo.Password;
import org.springframework.stereotype.Component;

@Component
class AdminPasswordValidator implements PasswordValidator {

  @Override
  public ValidationResult validate(final Password password) {
    final ValidationResult validationResult = ValidationResult.create();

    if (password.isLongerThan(10)) {
      validationResult.addError("Password must have at least 10 chars");
    }

    if (password.isLongerThan(25)) {
      validationResult.addError("Password can have maximum 20 chars");
    }

    if (password.value().chars().filter(Character::isDigit).count() < 2) {
      validationResult.addError("Password must contain two digits");
    }

    if (!password.containsCharacterType(Character::isLowerCase)) {
      validationResult.addError("Password must contain upper case letter");
    }

    if (!password.containsCharacterType(Character::isLowerCase)) {
      validationResult.addError("Password must contain lower case letter");
    }

    if (!password.containsSpecialCharacter()) {
      validationResult.addError("Password must contain special character");
    }

    if (password.containsWhitespaces()) {
      validationResult.addError("Password can't contain whitespaces");
    }

    return validationResult;
  }

  @Override
  public boolean canValidatePasswordFor(final Role role) {
    return role == Role.ADMIN;
  }

}
