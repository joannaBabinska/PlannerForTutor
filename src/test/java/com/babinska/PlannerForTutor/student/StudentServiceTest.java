package com.babinska.PlannerForTutor.student;

import com.babinska.PlannerForTutor.student.dto.StudentDto;
import com.babinska.PlannerForTutor.student.dto.StudentRegistrationDto;
import com.babinska.PlannerForTutor.student.dto.StudentUpdateDto;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

  @Autowired
  private StudentService studentService;

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

    //when
    StudentDto savedStudent = studentService.saveStudent(studentRegistrationDto);

    //then
    Student studentInDatabase = studentRepository.findById(savedStudent.getId()).get();
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
    StudentDto savedStudent = studentService.saveStudent(studentToSave);
    StudentDto replacingStudent = studentService.replaceStudent(studentToReplace, savedStudent.getId());

    //then
    assertAll(
            () -> assertEquals("testReplaceFirstName", replacingStudent.getFirstName()),
            () -> assertEquals("testReplaceLastName", replacingStudent.getLastName()),
            () -> assertEquals(LocalDate.of(2008, 12, 1), replacingStudent.getDateOfBirth()),
            () -> assertEquals("testReplace@email.com", replacingStudent.getEmail()),
            () -> assertEquals("123456789", replacingStudent.getPhoneNumber()),
            () -> assertEquals(SchoolClass.ELEMENTARY_SCHOOL_8TH_GRADE, replacingStudent.getSchoolClass())
    );
  }

  @Test
  public void shouldDeleteStudent() {
    //given
    StudentRegistrationDto studentToSave = StudentRegistrationDto.builder()
            .firstName("testFirstName")
            .lastName("testLastName")
            .dateOfBirth(LocalDate.of(2008, 12, 1))
            .email("test@email.com")
            .phoneNumber("589996325")
            .schoolClass(SchoolClass.ELEMENTARY_SCHOOL_7TH_GRADE)
            .build();

    //when
    StudentDto savedStudent = studentService.saveStudent(studentToSave);
    studentService.deleteStudent(savedStudent.getId());

    //then
    Optional<Student> foundStudent = studentRepository.findById(savedStudent.getId());
    assertTrue(foundStudent.isEmpty());
  }
//todo - dodać ten test bardziej do kontrolera

//  @Test
//  public void shouldUpdateSomeInformation() {
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
//    StudentUpdateDto studentUpdateDto = StudentUpdateDto.builder()
//            .id(1L)
//            .firstName("testChangedFirstName")
//            .schoolClass(SchoolClass.ELEMENTARY_SCHOOL_8TH_GRADE)
//            .build();
//
//    //when
//    StudentDto savedStudent = studentService.saveStudent(studentToSave);
//    studentService.updateStudent(studentUpdateDto);
//
//    //then
//    Student updatedStudent = studentRepository.findById(studentUpdateDto.getId()).get();
//    assertAll(
//            () -> assertEquals("testChangedFirstName", updatedStudent.getFirstName()),
//            () -> assertEquals(SchoolClass.ELEMENTARY_SCHOOL_8TH_GRADE, updatedStudent.getSchoolClass())
    );


  }

}