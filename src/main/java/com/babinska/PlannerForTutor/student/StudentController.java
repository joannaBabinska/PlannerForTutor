package com.babinska.PlannerForTutor.student;

import com.babinska.PlannerForTutor.constraint.TrackExecutionTime;
import com.babinska.PlannerForTutor.student.dto.StudentDto;
import com.babinska.PlannerForTutor.student.dto.StudentRegistrationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @GetMapping("/{id}")
  @TrackExecutionTime
  public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
    return ResponseEntity.ok(studentService.getStudentById(id));
  }

  @PostMapping
  public ResponseEntity<StudentDto> addStudent(@Valid @RequestBody StudentRegistrationDto studentRegistrationDto) {
    StudentDto savedStudentDto = studentService.addStudent(studentRegistrationDto);

    URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedStudentDto.id())
            .toUri();

    log.info("Add new student: {}", savedStudentDto);
    return ResponseEntity.created(uri).body(savedStudentDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<StudentDto> replaceStudent(@PathVariable Long id,@Valid @RequestBody StudentRegistrationDto studentRegistrationDto) {
    StudentDto studentDto = studentService.replaceStudent(studentRegistrationDto, id);
    log.info("Replace student with id = {} to student: {}", id, studentDto);
    return ResponseEntity.ok(studentDto);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id,@Valid @RequestBody JsonMergePatch jsonMergePatch)
          throws JsonPatchException, JsonProcessingException {
    StudentDto savedStudentDto = studentService.updateStudent(id, jsonMergePatch);
    log.info("Updated student with id ={}", id);
    return ResponseEntity.ok(savedStudentDto);
  }

  @DeleteMapping("/{id}" )
  public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
    studentService.deleteStudent(id);
    return ResponseEntity.ok().build();
  }

}
