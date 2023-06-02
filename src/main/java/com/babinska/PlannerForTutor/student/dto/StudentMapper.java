package com.babinska.PlannerForTutor.student.dto;

import com.babinska.PlannerForTutor.student.Student;

public class StudentMapper {

  public static StudentDto map(Student student) {
    return StudentDto.builder()
            .id(student.getId())
            .firstName(student.getFirstName())
            .lastName(student.getLastName())
            .email(student.getEmail())
            .phoneNumber(student.getPhoneNumber())
            .dateOfBirth(student.getDateOfBirth())
            .schoolClass(student.getSchoolClass())
            .build();
  }

  public static Student map(StudentRegistrationDto studentRegistrationDto) {
    return Student.builder()
            .firstName(studentRegistrationDto.getFirstName())
            .lastName(studentRegistrationDto.getLastName())
            .email(studentRegistrationDto.getEmail())
            .phoneNumber(studentRegistrationDto.getPhoneNumber())
            .dateOfBirth(studentRegistrationDto.getDateOfBirth())
            .schoolClass(studentRegistrationDto.getSchoolClass())
            .build();
  }

  public static StudentUpdateDto map(StudentDto student) {
    return StudentUpdateDto.builder()
            .id(student.getId())
            .firstName(student.getFirstName())
            .lastName(student.getLastName())
            .email(student.getEmail())
            .phoneNumber(student.getPhoneNumber())
            .dateOfBirth(student.getDateOfBirth())
            .schoolClass(student.getSchoolClass())
            .build();
  }

  public static Student map(StudentUpdateDto studentUpdateDto) {
    return Student.builder()
            .firstName(studentUpdateDto.getFirstName())
            .lastName(studentUpdateDto.getLastName())
            .email(studentUpdateDto.getEmail())
            .phoneNumber(studentUpdateDto.getPhoneNumber())
            .dateOfBirth(studentUpdateDto.getDateOfBirth())
            .schoolClass(studentUpdateDto.getSchoolClass())
            .build();
  }
}
