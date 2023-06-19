package com.babinska.PlannerForTutor.exception;

public class StudentAlreadyAddedToLessonException extends RuntimeException {
  public StudentAlreadyAddedToLessonException(Long id) {
    super("Student with id %d already added".formatted(id));
  }
}
