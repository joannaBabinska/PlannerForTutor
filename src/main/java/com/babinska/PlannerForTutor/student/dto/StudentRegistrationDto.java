package com.babinska.PlannerForTutor.student.dto;

import com.babinska.PlannerForTutor.student.SchoolClass;
import com.babinska.PlannerForTutor.student.constraint.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Builder
@Data
public class StudentRegistrationDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    @NotNull
    private String email;
    @NotNull
    @PhoneNumber
    private String phoneNumber;
    @Past
    private LocalDate dateOfBirth;
    private SchoolClass schoolClass;
  }

