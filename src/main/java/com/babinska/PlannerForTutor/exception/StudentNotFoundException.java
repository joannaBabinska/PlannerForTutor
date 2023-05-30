package com.babinska.PlannerForTutor.exception;

public class StudentNotFoundException extends RuntimeException{
  public StudentNotFoundException(Long id){
    super("Student with %d id not exist".formatted(id));
  }
}
