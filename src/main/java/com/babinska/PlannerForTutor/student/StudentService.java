package com.babinska.PlannerForTutor.student;

import com.babinska.PlannerForTutor.exception.StudentNotFoundException;
import com.babinska.PlannerForTutor.lessonreservation.LessonReservationService;
import com.babinska.PlannerForTutor.student.dto.StudentDto;
import com.babinska.PlannerForTutor.student.dto.StudentRegistrationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;
  private final ObjectMapper objectMapper;
  private final LessonReservationService lessonReservationService;


  public StudentDto getStudentById(Long id) {
    Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    return StudentMapper.map(student);
  }

  public StudentDto addStudent(StudentRegistrationDto studentRegistrationDto) {
    Student student = StudentMapper.map(studentRegistrationDto);
    Student savedStudent = studentRepository.save(student);
    return StudentMapper.map(savedStudent);
  }

  public StudentDto replaceStudent(StudentRegistrationDto studentRegistrationDto, Long id) {
    getStudentById(id);
    Student student = StudentMapper.map(studentRegistrationDto);
    student.setId(id);
    Student savedStudent = studentRepository.save(student);
    return StudentMapper.map(savedStudent);
  }

  public StudentDto updateStudent(Long id, JsonMergePatch jsonMergePatch) throws JsonPatchException, JsonProcessingException {
    StudentDto studentDto = getStudentById(id);
    StudentRegistrationDto studentRegistrationDto = StudentMapper.map(studentDto);
    Student studentToSave = applyPatch(jsonMergePatch, studentRegistrationDto);
    studentToSave.setId(id);
    studentRepository.save(studentToSave);
    return StudentMapper.map(studentToSave);
  }

  public void deleteStudent(Long studentId) {
    Set<Long> lessonIdForTheStudent = getLessonsIdForTheStudent(studentId);
    lessonIdForTheStudent.forEach(lessonId -> lessonReservationService.deleteStudentFromLessonReservation(lessonId, studentId));
    studentRepository.deleteById(studentId);
  }

  private Student applyPatch(JsonMergePatch jsonMergePatch, StudentRegistrationDto studentRegistrationDto) throws JsonPatchException, JsonProcessingException {
    JsonNode jsonNode = objectMapper.valueToTree(studentRegistrationDto);
    JsonNode jsonNodePatchedNode = jsonMergePatch.apply(jsonNode);
    return objectMapper.treeToValue(jsonNodePatchedNode, Student.class);
  }


  private Set<Long> getLessonsIdForTheStudent(Long id) {
    return studentRepository.findLessonIdForTheStudentId(id);
  }
}
