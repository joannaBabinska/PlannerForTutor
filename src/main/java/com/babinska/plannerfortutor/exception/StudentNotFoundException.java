package com.babinska.plannerfortutor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends PlannerException {
  public StudentNotFoundException(Long id) {
    super("Student with %d id not exist".formatted(id));
  }
}
