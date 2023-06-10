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
            .firstName(studentRegistrationDto.firstName())
            .lastName(studentRegistrationDto.lastName())
            .email(studentRegistrationDto.email())
            .phoneNumber(studentRegistrationDto.phoneNumber())
            .dateOfBirth(studentRegistrationDto.dateOfBirth())
            .schoolClass(studentRegistrationDto.schoolClass())
            .build();
  }


  public static StudentRegistrationDto map(StudentDto student) {
    return StudentRegistrationDto.builder()
            .firstName(student.firstName())
            .lastName(student.lastName())
            .email(student.email())
            .phoneNumber(student.phoneNumber())
            .dateOfBirth(student.dateOfBirth())
            .schoolClass(student.schoolClass())
            .build();
  }

}
