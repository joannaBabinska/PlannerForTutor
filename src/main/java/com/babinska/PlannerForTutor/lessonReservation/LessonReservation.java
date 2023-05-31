package com.babinska.PlannerForTutor.lessonReservation;

import com.babinska.PlannerForTutor.lesson.Lesson;
import com.babinska.PlannerForTutor.student.Student;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class LessonReservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name="lesson_id")
  private Lesson lesson;
  @ManyToMany
  private Set<Student> students;
  private String topic;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private int durationInMinutes;
}
