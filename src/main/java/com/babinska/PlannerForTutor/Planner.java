package com.babinska.PlannerForTutor;

import com.babinska.PlannerForTutor.lesson.Lesson;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Planner {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Lesson lessonId;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private int duration;
}
