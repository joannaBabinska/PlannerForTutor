package com.babinska.plannerfortutor.exception;

public abstract class PlannerException extends RuntimeException {

  protected PlannerException(final String message) {
    super(message);
  }

}
