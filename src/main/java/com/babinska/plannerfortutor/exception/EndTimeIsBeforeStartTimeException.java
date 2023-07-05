package com.babinska.plannerfortutor.exception;

public class EndTimeIsBeforeStartTimeException extends RuntimeException {
  public EndTimeIsBeforeStartTimeException() {
    super("End time is before start time");
  }
}