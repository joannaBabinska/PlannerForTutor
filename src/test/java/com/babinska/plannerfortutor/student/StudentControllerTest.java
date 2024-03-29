package com.babinska.plannerfortutor.student;

import com.babinska.plannerfortutor.AbstractIntegrationTest;
import com.babinska.plannerfortutor.message.mq.RabbitMQJsonProducer;
import com.babinska.plannerfortutor.student.dto.StudentDto;
import com.babinska.plannerfortutor.student.dto.StudentRegistrationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentControllerTest extends AbstractIntegrationTest {

  @Autowired
  StudentService studentService;

  @Autowired
  StudentController studentController;

  @Autowired
  StudentRepository studentRepository;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  RabbitMQJsonProducer rabbitMQJsonProducer;

  @Disabled
  @Test
  public void shouldUpdateSomeInformation() throws JsonPatchException, JsonProcessingException {
    //given
    StudentRegistrationDto studentToSave = StudentRegistrationDto.builder()
            .firstName("testFirstName")
            .lastName("testLastName")
            .dateOfBirth(LocalDate.of(2008, 12, 1))
            .email("test@email.com")
            .phoneNumber("589996325")
            .schoolClass(SchoolClass.ELEMENTARY_SCHOOL_7TH_GRADE)
            .build();

    StudentDto savedStudent = studentService.addStudent(studentToSave);

    StudentRegistrationDto studentUpdateDto = StudentRegistrationDto.builder()
            .firstName("testChangedFirstName")
            .schoolClass(SchoolClass.ELEMENTARY_SCHOOL_8TH_GRADE)
            .build();

    String val = """
             {
            "schoolClass": "HIGH_SCHOOL_4TH_GRADE",
            "firstName" : "testChangedFirstName"
             }
             """;

    //when
    studentController.updateStudent(savedStudent.id(), JsonMergePatch.fromJson(objectMapper.readTree(val)
    ));
    //then
    Student updatedStudent = studentRepository.findById(savedStudent.id()).get();
    assertAll(
            () -> assertEquals("testChangedFirstName", updatedStudent.getFirstName()),
            () -> assertEquals(SchoolClass.HIGH_SCHOOL_4TH_GRADE, updatedStudent.getSchoolClass())
    );

  }
}