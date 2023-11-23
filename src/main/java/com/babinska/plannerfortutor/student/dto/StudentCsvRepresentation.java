package com.babinska.plannerfortutor.student.dto;

import com.babinska.plannerfortutor.student.SchoolClass;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class StudentCsvRepresentation {
    @CsvBindByName(column = "firstname")
    private String firstName;
    @CsvBindByName(column = "lastname")
    private String lastName;
    @CsvBindByName(column = "email")
    private String email;
    @CsvBindByName(column = "phoneNumber")
    private String phoneNumber;
    @CsvBindByName(column = "dateOfBirth")
    @CsvDate("dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @CsvBindByName(column = "schoolClass")
    private SchoolClass schoolClass;

}
