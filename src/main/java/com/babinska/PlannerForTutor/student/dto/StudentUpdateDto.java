package com.babinska.PlannerForTutor.student.dto;

import com.babinska.PlannerForTutor.student.SchoolClass;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Builder
@Data
public class StudentUpdateDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private SchoolClass schoolClass;
  }

