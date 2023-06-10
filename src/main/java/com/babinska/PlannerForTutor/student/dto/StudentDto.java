package com.babinska.PlannerForTutor.student.dto;

import com.babinska.PlannerForTutor.student.SchoolClass;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record StudentDto(Long id,
                  String firstName,
                  String lastName,
                  String email,
                  String phoneNumber,
                  LocalDate dateOfBirth,
                  SchoolClass schoolClass) {
}
