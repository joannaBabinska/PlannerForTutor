package com.babinska.plannerfortutor.exception;

public class StudentAlreadyAddedToLessonException extends RuntimeException {
  public StudentAlreadyAddedToLessonException(Long id) {
    super("Student with id %d already added".formatted(id));
  }
}
