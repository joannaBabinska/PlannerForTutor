package com.babinska.plannerfortutor.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
class ControllerAdvisor {

  @ExceptionHandler(PlannerException.class)
  public ResponseEntity<Object> handle(PlannerException ex) {
    return ResponseEntity
        .status(resolveHttpStatusCode(ex).orElse(HttpStatus.BAD_REQUEST))
        .body(ex.getMessage());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<List<String>> handle(ConstraintViolationException ex) {

    List<String> errors = ex.getConstraintViolations().stream()
            .map(constraintViolation -> String.format("%s wartość '%s' %s", constraintViolation.getPropertyPath(),
                    constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
            .collect(Collectors.toList());

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<String>> handle(MethodArgumentNotValidException ex) {

    List<String> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> String.format("%s = %s %s", fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()))
            .collect(Collectors.toList());

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  private Optional<HttpStatus> resolveHttpStatusCode(final PlannerException ex) {
    if (ex.getClass().isAnnotationPresent(ResponseStatus.class)) {
      ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
      return Optional.of(responseStatus.value());
    }
    return Optional.empty();
  }

}
