package com.babinska.plannerfortutor.validation.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class MonthValidator implements ConstraintValidator<Month, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) return true;
    java.time.Month[] months = java.time.Month.values();
    return Arrays.stream(months).anyMatch(month -> month.name().equalsIgnoreCase(value));
  }
}