package com.babinska.PlannerForTutor.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
  @Override
  public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
    return contactField == null || contactField.matches("[0-9]+")
            && (contactField.length() > 8) && (contactField.length() < 12);
  }
}
