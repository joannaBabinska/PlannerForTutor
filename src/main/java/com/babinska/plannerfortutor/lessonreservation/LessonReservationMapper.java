package com.babinska.plannerfortutor.lessonreservation;

import com.babinska.plannerfortutor.lessonreservation.dto.LessonReservationDto;
import com.babinska.plannerfortutor.lessonreservation.dto.LessonReservationRegistrationDto;
import com.babinska.plannerfortutor.lessonreservation.dto.LessonReservationStudentsDto;
import com.babinska.plannerfortutor.lessonreservation.dto.LessonReservationStudentDto;
import com.babinska.plannerfortutor.student.Student;
import com.babinska.plannerfortutor.student.StudentMapper;
import com.babinska.plannerfortutor.student.dto.StudentDto;
import com.babinska.plannerfortutor.student.dto.StudentWelcomeMessageDto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class LessonReservationMapper {

  public static LessonReservationStudentsDto mapToLessonReservationStudentDto(LessonReservationDto lessonReservationDto){
    return LessonReservationStudentsDto.builder()
            .lessonType(lessonReservationDto.lessonType())
            .topic(lessonReservationDto.topic())
            .startTime(lessonReservationDto.startTime())
            .endTime(lessonReservationDto.endTime())
            .reservationDate(lessonReservationDto.reservationDate())
            .price(lessonReservationDto.price())
            .build();
  }

  public static LessonReservation map(LessonReservationStudentsDto lessonReservationStudentsDto){
    return LessonReservation.builder()
            .lessonType(lessonReservationStudentsDto.lessonType())
            .topic(lessonReservationStudentsDto.topic())
            .startTime(LocalDateTime.of(lessonReservationStudentsDto.reservationDate(),
                    lessonReservationStudentsDto.startTime()))
            .endTime(LocalDateTime.of(lessonReservationStudentsDto.reservationDate(),
                    lessonReservationStudentsDto.endTime()))
            .students(mapToStudent(lessonReservationStudentsDto.students()))
            .price(lessonReservationStudentsDto.price())
            .build();
  }

  public static LessonReservationStudentsDto mapToLessonReservationStudentDto(LessonReservation lessonReservation){
    return LessonReservationStudentsDto.builder()
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
  public static LessonReservationStudentDto map(LessonReservation lessonReservation, StudentWelcomeMessageDto studentWelcomeMessageDto){
    return LessonReservationStudentDto.builder()
            .id(lessonReservation.getId())
            .lessonType(lessonReservation.getLessonType())
            .student(studentWelcomeMessageDto)
            .topic(lessonReservation.getTopic())
            .startTime(lessonReservation.getStartTime().toLocalTime().toString())
            .endTime(lessonReservation.getEndTime().toLocalTime().toString())
            .durationInMinutes(lessonReservation.getDurationInMinutes())
            .reservationDate(lessonReservation.getStartTime().toLocalDate().toString())
            .price(lessonReservation.getPrice())
            .build();
  }

  private static Set<StudentDto> mapToStudentDto(Set<Student> students){
    return students.stream().map(StudentMapper::map).collect(Collectors.toSet());
  }
  private static Set<Student> mapToStudent(Set<StudentDto> students){
    return students.stream().map(StudentMapper::mapToStudent).collect(Collectors.toSet());
  }

}
