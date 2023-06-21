package com.babinska.PlannerForTutor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor {

  @ExceptionHandler(DayIsNotWorkingException.class)
  public ResponseEntity<?> handle (DayIsNotWorkingException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).build();
  }

  @ExceptionHandler(LessonReservationNotFoundException.class)
  public ResponseEntity<?> handle(LessonReservationNotFoundException ex) {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(StudentNotFoundException.class)
  public ResponseEntity<?> handle(StudentNotFoundException ex) {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(StudentAlreadyAddedToLessonException.class)
  public ResponseEntity<?> handle(StudentAlreadyAddedToLessonException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).build();
  }

  @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
  public ResponseEntity<List<String>> handle(jakarta.validation.ConstraintViolationException ex) {

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

}
