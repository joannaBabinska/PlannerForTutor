package com.babinska.plannerfortutor.exception;

public class LessonReservationNotFoundException extends RuntimeException {
  public LessonReservationNotFoundException(Long id) {
    super("Lesson reservation with %d not exist".formatted(id));
  }
}
