package com.babinska.plannerfortutor.message;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@AllArgsConstructor
@Builder
@Data
public class Email {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
//@ManyToOne
//  private Long studentId;
  private String topic;
  private String message;
//  private LessonReservation lessonReservation;
//  private LocalDateTime messageTime;
}
