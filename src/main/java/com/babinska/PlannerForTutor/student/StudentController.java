package com.babinska.PlannerForTutor.student;

import com.babinska.PlannerForTutor.exception.StudentNotFoundException;
import com.babinska.PlannerForTutor.student.dto.StudentDto;
import com.babinska.PlannerForTutor.student.dto.StudentDtoMapper;
import com.babinska.PlannerForTutor.student.dto.StudentRegistrationDto;
import com.babinska.PlannerForTutor.student.dto.StudentUpdateDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;
  private final ObjectMapper objectMapper;

  @GetMapping("/{id}")
  public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
    return ResponseEntity.ok(studentService.getStudentById(id));
  }

  @PostMapping("")
  public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentRegistrationDto studentRegistrationDto) {
    StudentDto savedStudentDto = studentService.saveStudent(studentRegistrationDto);

    URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedStudentDto.getId())
            .toUri();

    return ResponseEntity.created(uri).body(savedStudentDto);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<StudentUpdateDto> updateStudent(@PathVariable Long id, @RequestBody JsonMergePatch jsonMergePatch) throws JsonPatchException, JsonProcessingException {
    StudentDto studentDto = studentService.getStudentById(id);
    StudentUpdateDto studentUpdateDto = StudentDtoMapper.map(studentDto);
    StudentUpdateDto studentPatched = applyPath(studentUpdateDto, jsonMergePatch);
    studentService.updateStudent(studentPatched);
    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler(StudentNotFoundException.class)
  public ResponseEntity<String> handle(StudentNotFoundException ex) {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(JsonPatchException.class)
  public ResponseEntity<?> handle(JsonPatchException ex) {
    return ResponseEntity.internalServerError().build();
  }

  @ExceptionHandler(JsonPatchException.class)
  public ResponseEntity<?> handle(JsonProcessingException ex) {
    return ResponseEntity.internalServerError().build();
  }

  private StudentUpdateDto applyPath(StudentUpdateDto studentUpdateDto, JsonMergePatch jsonMergePatch)
          throws JsonProcessingException, JsonPatchException {
    JsonNode jsonNode = objectMapper.valueToTree(studentUpdateDto);
    JsonNode jsonNodePatchedNode = jsonMergePatch.apply(jsonNode);
    return objectMapper.treeToValue(jsonNodePatchedNode, StudentUpdateDto.class);
  }


}
