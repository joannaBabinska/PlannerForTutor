package com.babinska.plannerfortutor.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

  private final List<String> errors;

  private ValidationResult() {
    errors = new ArrayList<>();
  }

  public static ValidationResult create() {
    return new ValidationResult();
  }

  public void addError(String error) {
    this.errors.add(error);
  }

  public boolean hasErrors() {
    return !this.errors.isEmpty();
  }

  public List<String> getErrors() {
    return new ArrayList<>(this.errors);
  }

}
