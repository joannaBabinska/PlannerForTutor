package com.babinska.PlannerForTutor.exception;

public class LessonNotFoundException extends RuntimeException{

  public LessonNotFoundException(Long id){
    super("Lesson %d not exist".formatted(id));
  }
}
