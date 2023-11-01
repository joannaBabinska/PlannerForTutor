package com.babinska.plannerfortutor.exception;

public class EndTimeIsBeforeStartTimeException extends PlannerException {
  public EndTimeIsBeforeStartTimeException() {
    super("End time is before start time");
  }
}