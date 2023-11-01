package com.babinska.plannerfortutor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LessonReservationNotFoundException extends PlannerException {

  public LessonReservationNotFoundException(Long id) {
    super("Lesson reservation with %d not exist".formatted(id));
  }
}
