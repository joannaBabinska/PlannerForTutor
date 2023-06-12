package com.babinska.PlannerForTutor.lessonReservation;

import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationDto;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationRegistrationDto;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationStudentDto;

public class LessonReservationMapper {

  public static LessonReservationStudentDto mapToLessonReservationStudentDto(LessonReservationDto lessonReservationDto){
    return LessonReservationStudentDto.builder()
            .lessonType(lessonReservationDto.lessonType())
            .topic(lessonReservationDto.topic())
            .startTime(lessonReservationDto.startTime())
            .endTime(lessonReservationDto.endTime())
            .reservationDate(lessonReservationDto.reservationDate())
            .price(lessonReservationDto.price())
            .build();
  }
  public static LessonReservationStudentDto mapToLessonReservationStudentDto(LessonReservation lessonReservation){
    return LessonReservationStudentDto.builder()
            .id(lessonReservation.getId())
            .lessonType(lessonReservation.getLessonType())
            .topic(lessonReservation.getTopic())
            .students(lessonReservation.getStudents())
            .startTime(lessonReservation.getStartTime())
            .endTime(lessonReservation.getEndTime())
            .durationInMinutes(lessonReservation.getDurationInMinutes())
            .reservationDate(lessonReservation.getReservationDate())
            .price(lessonReservation.getPrice())
            .build();
  }

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

  public static LessonReservation mapToLessonReservation
          (LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    return LessonReservation.builder()
            .lessonType(lessonReservationRegistrationDto.lessonType())
            .topic(lessonReservationRegistrationDto.topic())
            .startTime(lessonReservationRegistrationDto.startTime())
            .endTime(lessonReservationRegistrationDto.endTime())
            .durationInMinutes(lessonReservationRegistrationDto.durationInMinutes())
            .reservationDate(lessonReservationRegistrationDto.reservationDate())
            .price(lessonReservationRegistrationDto.price())
            .build();
  }

  public static LessonReservationRegistrationDto map(LessonReservationDto lessonReservationDto) {
    return LessonReservationRegistrationDto.builder()
            .lessonType(lessonReservationDto.lessonType())
            .topic(lessonReservationDto.topic())
            .startTime(lessonReservationDto.startTime())
            .endTime(lessonReservationDto.endTime())
            .reservationDate(lessonReservationDto.reservationDate())
            .price(lessonReservationDto.price())
            .build();
  }
}
