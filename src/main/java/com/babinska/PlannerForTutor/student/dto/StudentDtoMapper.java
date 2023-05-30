package com.babinska.PlannerForTutor.student.dto;

import com.babinska.PlannerForTutor.student.Student;

public class StudentDtoMapper {

  public static StudentDto map(Student student){
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
}
