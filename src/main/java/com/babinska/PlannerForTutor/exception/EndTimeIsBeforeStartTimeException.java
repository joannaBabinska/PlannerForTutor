package com.babinska.PlannerForTutor.exception;

public class EndTimeIsBeforeStartTimeException extends RuntimeException {
  public EndTimeIsBeforeStartTimeException() {
    super("End time is before start time");
  }
}