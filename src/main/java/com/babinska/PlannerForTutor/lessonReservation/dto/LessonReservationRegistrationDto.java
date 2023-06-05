package com.babinska.PlannerForTutor.lessonReservation.dto;

import com.babinska.PlannerForTutor.lessonReservation.LessonType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
//@Builder
public class LessonReservationRegistrationDto {
  @Enumerated(EnumType.STRING)
  private LessonType lessonType;
  private String topic;
  private LocalTime startTime;
  private LocalTime endTime;
  private LocalDate reservationDate;
  private int durationInMinutes;
  private double price;

  public LessonReservationRegistrationDto(LessonType lessonType, String topic, LocalTime startTime, LocalTime endTime,
                                          LocalDate reservationDate,double price) {
    this.lessonType = lessonType;
    this.topic = topic;
    this.startTime = startTime;
    this.endTime = endTime;
    this.reservationDate = reservationDate;
    this.durationInMinutes = duration(startTime, endTime);
    this.price = price;
  }

  private int duration(LocalTime startTime,LocalTime endTime){
    long duration = Duration.between(startTime, endTime).toMinutes();
    return (int) duration;
  }

  public int getDurationInMinutes() {
    return duration(this.startTime, this.endTime);
  }
}
