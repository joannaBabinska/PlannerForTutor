package com.babinska.plannerfortutor.validation;

public interface NotificationValidator<T> {

  ValidationResult validate(T type);

}
