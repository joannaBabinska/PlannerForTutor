package com.babinska.PlannerForTutor.lessonReservation.dto;

import com.babinska.PlannerForTutor.lessonReservation.LessonReservation;

public class LessonReservationMapper {

  public static LessonReservationDto map(LessonReservation lessonReservation) {
    return LessonReservationDto.builder()
            .id(lessonReservation.getId())
            .lessonType(lessonReservation.getLessonType())
            .topic(lessonReservation.getTopic())
            .startTime(lessonReservation.getStartTime())
            .endTime(lessonReservation.getEndTime())
            .durationInMinutes(lessonReservation.getDurationInMinutes())
            .reservationDate(lessonReservation.getReservationDate())
            .price(lessonReservation.getPrice())
            .build();
  }

  public static LessonReservation mapToLessonReservationRegistrationDto
          (LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    return LessonReservation.builder()
            .lessonType(lessonReservationRegistrationDto.getLessonType())
            .topic(lessonReservationRegistrationDto.getTopic())
            .startTime(lessonReservationRegistrationDto.getStartTime())
            .endTime(lessonReservationRegistrationDto.getEndTime())
            .durationInMinutes(lessonReservationRegistrationDto.getDurationInMinutes())
            .reservationDate(lessonReservationRegistrationDto.getReservationDate())
            .price(lessonReservationRegistrationDto.getPrice())
            .build();
  }
}
