package com.babinska.PlannerForTutor.student.dto;

import com.babinska.PlannerForTutor.student.SchoolClass;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class StudentDto {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private LocalDate dateOfBirth;
//  private Set<Lesson> lessons;
  private SchoolClass schoolClass;
}
