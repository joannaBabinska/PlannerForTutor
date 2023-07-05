package com.babinska.plannerfortutor.student.dto;

import com.babinska.plannerfortutor.student.SchoolClass;
import com.babinska.plannerfortutor.validation.constraint.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import java.time.LocalDate;

@Builder
public record StudentRegistrationDto(@NotNull String firstName,
                              @NotNull String lastName,
                              @Email @NotNull String email,
                              @NotNull @PhoneNumber String phoneNumber,
                              @Past LocalDate dateOfBirth,
                              SchoolClass schoolClass) {
}


