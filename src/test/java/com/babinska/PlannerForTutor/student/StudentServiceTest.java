package com.babinska.PlannerForTutor.student;

import com.babinska.PlannerForTutor.student.dto.StudentDtoMapper;
import com.babinska.PlannerForTutor.student.dto.StudentRegistrationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

  @Autowired
  private StudentService underTest;

  @Autowired
  private StudentRepository studentRepository;

  @Test
  public void shouldSaveNewStudent() {
    //given
    StudentRegistrationDto studentRegistrationDto = StudentRegistrationDto.builder()
            .firstName("testFirstName")
            .lastName("testLastName")
            .dateOfBirth(LocalDate.of(2008, 12, 1))
            .email("test@email.com")
            .phoneNumber("589996325")
            .schoolClass(SchoolClass.ELEMENTARY_SCHOOL_7TH_GRADE)
            .build();
    Student studentToSave = StudentDtoMapper.map(studentRegistrationDto);

    //when
    studentRepository.save(studentToSave);

    //then
    Student savedStudent = studentRepository.findAll().stream()
            .filter(student -> student.getFirstName().equals("testFirstName"))
            .findFirst()
            .get();

    assertAll(
            () -> assertEquals("testFirstName", savedStudent.getFirstName()),
            () -> assertEquals("testLastName",  savedStudent.getLastName()),
            () -> assertEquals(LocalDate.of(2008,12,1), savedStudent.getDateOfBirth()),
            () -> assertEquals("test@email.com", savedStudent.getEmail()),
            () -> assertEquals("589996325", savedStudent.getPhoneNumber()),
            () -> assertEquals(SchoolClass.ELEMENTARY_SCHOOL_7TH_GRADE, savedStudent.getSchoolClass())
    );
  }
}