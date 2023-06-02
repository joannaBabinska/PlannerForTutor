package com.babinska.PlannerForTutor.lesson.dto;

import com.babinska.PlannerForTutor.lesson.LessonType;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LessonDto {
  private Long id;
  private LessonType lessonType;
  @Min(50)
  private double price;
}
