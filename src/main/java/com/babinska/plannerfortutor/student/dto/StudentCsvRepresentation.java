package com.babinska.plannerfortutor.student.dto;

import com.babinska.plannerfortutor.student.SchoolClass;
import com.opencsv.bean.CsvBindByName;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record StudentCsvRepresentation(
        @CsvBindByName(column = "firstname")
        String firstName,
        @CsvBindByName(column = "lastname")
        String lastName,
        @CsvBindByName(column = "email")
        String email,
        @CsvBindByName(column = "phoneNumber")
        String phoneNumber,
        @CsvBindByName(column = "dateOfBirth")
        LocalDate dateOfBirth,
        @CsvBindByName(column = "schoolClass")
        SchoolClass schoolClass
) {
}
