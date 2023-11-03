package com.babinska.plannerfortutor.validation.passwordvalidator;

import com.babinska.plannerfortutor.user.Role;
import com.babinska.plannerfortutor.validation.NotificationValidator;
import com.babinska.plannerfortutor.validation.ValidationResult;
import com.babinska.plannerfortutor.vo.Password;

interface PasswordValidator extends NotificationValidator<Password> {

  ValidationResult validate(Password password);

  boolean canValidatePasswordFor(Role role);

}
