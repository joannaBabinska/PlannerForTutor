package com.babinska.plannerfortutor.student;

import com.babinska.plannerfortutor.student.dto.StudentDto;
import com.babinska.plannerfortutor.student.dto.StudentRegistrationDto;

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
  public static StudentRegistrationDto mapToStudentRegistrationDto(Student student) {
    return StudentRegistrationDto.builder()
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

  public static Student mapToStudent(StudentDto studentDto) {
    return Student.builder()
            .firstName(studentDto.firstName())
            .lastName(studentDto.lastName())
            .email(studentDto.email())
            .phoneNumber(studentDto.phoneNumber())
            .dateOfBirth(studentDto.dateOfBirth())
            .schoolClass(studentDto.schoolClass())
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
