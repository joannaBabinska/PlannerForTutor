package com.babinska.plannerfortutor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DayIsNotWorkingException extends PlannerException {
  public DayIsNotWorkingException(String message) {
    super(message);
  }
}
