package com.babinska.PlannerForTutor.student;

import com.babinska.PlannerForTutor.student.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @GetMapping("/{id}")
  public StudentDto getStudentById(@PathVariable Long id) {
    return studentService.getStudentById(id);
  }

}
