package com.babinska.PlannerForTutor.student;

import com.babinska.PlannerForTutor.exception.StudentNotFoundException;
import com.babinska.PlannerForTutor.student.dto.StudentDto;
import com.babinska.PlannerForTutor.student.dto.StudentDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;

  public StudentDto getStudentById(Long id){
    Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    return StudentDtoMapper.map(student);
  }
}
