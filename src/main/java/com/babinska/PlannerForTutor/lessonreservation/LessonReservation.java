package com.babinska.PlannerForTutor.lessonreservation;

import com.babinska.PlannerForTutor.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonReservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(EnumType.STRING)
  private LessonType lessonType;
  @ManyToMany(cascade = {CascadeType.PERSIST,
          CascadeType.MERGE,
          CascadeType.REFRESH,
          CascadeType.DETACH })
  private Set<Student> students;
  private String topic;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private int durationInMinutes;
  private BigDecimal price;
}
