package com.babinska.plannerfortutor.exception;

public class StudentNotFoundException extends RuntimeException {
  public StudentNotFoundException(Long id) {
    super("Student with %d id not exist".formatted(id));
  }
}
