package com.babinska.PlannerForTutor.lessonReservation;

import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationDto;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationRegistrationDto;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationStudentDto;
import com.babinska.PlannerForTutor.student.Student;
import com.babinska.PlannerForTutor.student.StudentMapper;
import com.babinska.PlannerForTutor.student.dto.StudentDto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

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
            .students(mapToStudentDto(lessonReservation.getStudents()))
            .startTime(lessonReservation.getStartTime().toLocalTime())
            .endTime(lessonReservation.getEndTime().toLocalTime())
            .durationInMinutes(lessonReservation.getDurationInMinutes())
            .reservationDate(lessonReservation.getStartTime().toLocalDate())
            .price(lessonReservation.getPrice())
            .build();
  }

  public static LessonReservationDto map(LessonReservation lessonReservation) {
    return LessonReservationDto.builder()
            .id(lessonReservation.getId())
            .lessonType(lessonReservation.getLessonType())
            .topic(lessonReservation.getTopic())
            .startTime(lessonReservation.getStartTime().toLocalTime())
            .endTime(lessonReservation.getEndTime().toLocalTime())
            .durationInMinutes(lessonReservation.getDurationInMinutes())
            .reservationDate(lessonReservation.getStartTime().toLocalDate())
            .price(lessonReservation.getPrice())
            .build();
  }

  public static LessonReservation mapToLessonReservation
          (LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    return LessonReservation.builder()
            .lessonType(lessonReservationRegistrationDto.lessonType())
            .topic(lessonReservationRegistrationDto.topic())
            .startTime(LocalDateTime.of(lessonReservationRegistrationDto.reservationDate(),
                    lessonReservationRegistrationDto.startTime()))
            .endTime(LocalDateTime.of(lessonReservationRegistrationDto.reservationDate(),
                    lessonReservationRegistrationDto.endTime()))
            .durationInMinutes(lessonReservationRegistrationDto.durationInMinutes())
            .price(lessonReservationRegistrationDto.price())
            .build();
  }

  public static LessonReservation mapToLessonReservation
          (LessonReservationStudentDto lessonReservationStudentDto) {
    return LessonReservation.builder()
            .id(lessonReservationStudentDto.id())
            .lessonType(lessonReservationStudentDto.lessonType())
            .topic(lessonReservationStudentDto.topic())
            .students(mapToStudent(lessonReservationStudentDto.students()))
            .startTime(LocalDateTime.of(lessonReservationStudentDto.reservationDate(),lessonReservationStudentDto.startTime()))
            .endTime(LocalDateTime.of(lessonReservationStudentDto.reservationDate(),lessonReservationStudentDto.endTime()))
            .durationInMinutes(lessonReservationStudentDto.durationInMinutes())
            .price(lessonReservationStudentDto.price())
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

  private static Set<Student> mapToStudent(Set<StudentDto> studentsDto){
    return studentsDto.stream().map(StudentMapper::mapToStudent).collect(Collectors.toSet());
  }

  private static Set<StudentDto> mapToStudentDto(Set<Student> students){
    return students.stream().map(StudentMapper::map).collect(Collectors.toSet());
  }

}
