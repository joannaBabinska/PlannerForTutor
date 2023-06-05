package com.babinska.PlannerForTutor.lessonReservation;


import com.babinska.PlannerForTutor.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
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
  @ManyToMany
  private Set<Student> students;
  private String topic;
  private LocalTime startTime;
  private LocalTime endTime;
  private LocalDate reservationDate;
  private int durationInMinutes;
  private double price;
}
