package com.babinska.plannerfortutor.lessonreservation;

import com.babinska.plannerfortutor.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class LessonReservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(EnumType.STRING)
  private LessonType lessonType;
  @ManyToMany(cascade = {CascadeType.PERSIST,
          CascadeType.MERGE,
          CascadeType.REFRESH,
          CascadeType.DETACH})
  private Set<Student> students;
  private String topic;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private int durationInMinutes;
  private BigDecimal price;
}
