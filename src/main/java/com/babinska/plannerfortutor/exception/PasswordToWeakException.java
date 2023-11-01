package com.babinska.plannerfortutor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordToWeakException extends PlannerException {

  public PasswordToWeakException(final String message) {
    super(message);
  }

}
