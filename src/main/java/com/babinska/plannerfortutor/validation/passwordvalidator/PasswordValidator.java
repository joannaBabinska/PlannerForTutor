package com.babinska.plannerfortutor.validation.passwordvalidator;

import com.babinska.plannerfortutor.user.Role;
import com.babinska.plannerfortutor.validation.NotificationValidator;
import com.babinska.plannerfortutor.validation.ValidationResult;

interface PasswordValidator extends NotificationValidator<String> {

  ValidationResult validate(String password);

  boolean canValidatePasswordFor(Role role);

}
