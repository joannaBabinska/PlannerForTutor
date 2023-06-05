package com.babinska.PlannerForTutor.lessonReservation.dto;

import com.babinska.PlannerForTutor.lessonReservation.LessonType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class LessonReservationDto {
  private Long id;
  @Enumerated(EnumType.STRING)
  private LessonType lessonType;
  private String topic;
  private LocalTime startTime;
  private LocalTime endTime;
  private LocalDate reservationDate;
  private int durationInMinutes;
  private double price;
}

