package com.babinska.PlannerForTutor.student;

import com.babinska.PlannerForTutor.exception.StudentNotFoundException;
import com.babinska.PlannerForTutor.student.dto.StudentDto;
import com.babinska.PlannerForTutor.student.dto.StudentRegistrationDto;
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

  @GetMapping("/{id}")
  public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
    return ResponseEntity.ok(studentService.getStudentById(id));
  }

  @PostMapping("")
  public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentRegistrationDto studentRegistrationDto){
    StudentDto savedStudentDto = studentService.saveStudent(studentRegistrationDto);

    URI uri =  ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedStudentDto.getId())
            .toUri();

    return ResponseEntity.created(uri).body(savedStudentDto);
  }

  @ExceptionHandler(StudentNotFoundException.class)
  public ResponseEntity<String> handle(StudentNotFoundException ex){
    return ResponseEntity.notFound().build();
  }


}
