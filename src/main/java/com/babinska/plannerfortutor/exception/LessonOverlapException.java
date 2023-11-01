package com.babinska.plannerfortutor.exception;

public class LessonOverlapException extends PlannerException {

  public LessonOverlapException() {
    super("Lesson overlap - can't add this lesson");
  }
}
