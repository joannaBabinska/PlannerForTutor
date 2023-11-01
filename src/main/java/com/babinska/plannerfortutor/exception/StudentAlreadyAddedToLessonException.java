package com.babinska.plannerfortutor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class StudentAlreadyAddedToLessonException extends PlannerException {

  public StudentAlreadyAddedToLessonException(Long id) {
    super("Student with id %d already added".formatted(id));
  }
}
