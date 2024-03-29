package com.babinska.plannerfortutor.student;

import com.babinska.plannerfortutor.AbstractIntegrationTest;
import com.babinska.plannerfortutor.message.mq.RabbitMQJsonProducer;
import com.babinska.plannerfortutor.student.dto.StudentDto;
import com.babinska.plannerfortutor.student.dto.StudentRegistrationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentServiceTest extends AbstractIntegrationTest {

  @Autowired
  private StudentService studentService;

  @Autowired
  private StudentRepository studentRepository;

  @MockBean
  private RabbitMQJsonProducer rabbitMQJsonProducer;


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

    //when
    StudentDto savedStudent = studentService.addStudent(studentRegistrationDto);

    //then
    Student studentInDatabase = studentRepository.findById(savedStudent.id()).get();
    assertAll(
            () -> assertEquals("testFirstName", studentInDatabase.getFirstName()),
            () -> assertEquals("testLastName", studentInDatabase.getLastName()),
            () -> assertEquals(LocalDate.of(2008, 12, 1), studentInDatabase.getDateOfBirth()),
            () -> assertEquals("test@email.com", studentInDatabase.getEmail()),
            () -> assertEquals("589996325", studentInDatabase.getPhoneNumber()),
            () -> assertEquals(SchoolClass.ELEMENTARY_SCHOOL_7TH_GRADE, studentInDatabase.getSchoolClass())
    );
  }

  @Test
  public void shouldReplaceStudent() {
    //given
    StudentRegistrationDto studentToSave = StudentRegistrationDto.builder()
            .firstName("testFirstName")
            .lastName("testLastName")
            .dateOfBirth(LocalDate.of(2008, 12, 1))
            .email("test@email.com")
            .phoneNumber("589996325")
            .schoolClass(SchoolClass.ELEMENTARY_SCHOOL_7TH_GRADE)
            .build();

    StudentRegistrationDto studentToReplace = StudentRegistrationDto.builder()
            .firstName("testReplaceFirstName")
            .lastName("testReplaceLastName")
            .dateOfBirth(LocalDate.of(2008, 12, 1))
            .email("testReplace@email.com")
            .phoneNumber("123456789")
            .schoolClass(SchoolClass.ELEMENTARY_SCHOOL_8TH_GRADE)
            .build();

    //when
    StudentDto savedStudent = studentService.addStudent(studentToSave);
    StudentDto replacingStudent = studentService.replaceStudent(studentToReplace, savedStudent.id());

    //then
    assertAll(
            () -> assertEquals("testReplaceFirstName", replacingStudent.firstName()),
            () -> assertEquals("testReplaceLastName", replacingStudent.lastName()),
            () -> assertEquals(LocalDate.of(2008, 12, 1), replacingStudent.dateOfBirth()),
            () -> assertEquals("testReplace@email.com", replacingStudent.email()),
            () -> assertEquals("123456789", replacingStudent.phoneNumber()),
            () -> assertEquals(SchoolClass.ELEMENTARY_SCHOOL_8TH_GRADE, replacingStudent.schoolClass())
    );
  }

//  @Test
//  public void shouldDeleteStudent() {
//    //given
//    StudentRegistrationDto studentToSave = StudentRegistrationDto.builder()
//            .firstName("testFirstName")
//            .lastName("testLastName")
//            .dateOfBirth(LocalDate.of(2008, 12, 1))
//            .email("test@email.com")
//            .phoneNumber("589996325")
//            .schoolClass(SchoolClass.ELEMENTARY_SCHOOL_7TH_GRADE)
//            .build();
//
//    //when
//    StudentDto savedStudent = studentService.addStudent(studentToSave);
//    studentService.deleteStudent(savedStudent.id());
//
//    //then
//    Optional<Student> foundStudent = studentRepository.findById(savedStudent.id());
//    assertTrue(foundStudent.isEmpty());
//  }
}

