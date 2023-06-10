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
            .price(lessonReservation.getPrice())
            .build();
  }

  public static LessonReservation mapToLessonReservation
          (LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    return LessonReservation.builder()
            .lessonType(lessonReservationRegistrationDto.lessonType())
            .topic(lessonReservationRegistrationDto.topic())
            .startTime(lessonReservationRegistrationDto.startTime())
            .endTime(lessonReservationRegistrationDto.endTime())
            .durationInMinutes(lessonReservationRegistrationDto.durationInMinutes())
            .price(lessonReservationRegistrationDto.price())
            .build();
  }

  public static LessonReservationRegistrationDto map(LessonReservationDto lessonReservationdto) {
    return LessonReservationRegistrationDto.builder()
            .lessonType(lessonReservationdto.lessonType())
            .topic(lessonReservationdto.topic())
            .startTime(lessonReservationdto.startTime())
            .endTime(lessonReservationdto.endTime())
            .price(lessonReservationdto.price())
            .build();
  }
}
