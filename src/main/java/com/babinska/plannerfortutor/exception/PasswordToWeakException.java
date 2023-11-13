package com.babinska.plannerfortutor.exception;

public class PasswordToWeakException extends RuntimeException {

  public PasswordToWeakException(final String message) {
    super(message);
  }

}
