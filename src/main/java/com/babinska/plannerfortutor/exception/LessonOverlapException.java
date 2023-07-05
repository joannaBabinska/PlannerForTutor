package com.babinska.plannerfortutor.exception;

public class LessonOverlapException extends RuntimeException {
  public LessonOverlapException() {
    super("Lesson overlap - can't add this lesson");
  }
}
