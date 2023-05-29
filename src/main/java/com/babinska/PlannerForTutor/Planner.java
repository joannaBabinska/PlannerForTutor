package com.babinska.PlannerForTutor;

import com.babinska.PlannerForTutor.lesson.Lesson;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Planner {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne
  private Lesson lesson;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private int duration;
}
