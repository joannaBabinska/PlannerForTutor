package com.babinska.PlannerForTutor.lesson.dto;

import com.babinska.PlannerForTutor.lesson.Lesson;

public class LessonMapper {
  public static LessonDto map(Lesson lesson) {
    return LessonDto.builder()
            .id(lesson.getId())
            .lessonType(lesson.getLessonType())
            .price(lesson.getPrice())
            .build();
  }

  public static Lesson map(LessonRegisteredDto lessonRegisteredDto) {
    return Lesson.builder()
            .lessonType(lessonRegisteredDto.getLessonType())
            .price(lessonRegisteredDto.getPrice())
            .build();
  }
}
