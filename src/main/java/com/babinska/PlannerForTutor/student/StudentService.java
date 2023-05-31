package com.babinska.PlannerForTutor.student;

import com.babinska.PlannerForTutor.exception.StudentNotFoundException;
import com.babinska.PlannerForTutor.student.dto.StudentDto;
import com.babinska.PlannerForTutor.student.dto.StudentDtoMapper;
import com.babinska.PlannerForTutor.student.dto.StudentRegistrationDto;
import com.babinska.PlannerForTutor.student.dto.StudentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;

  public StudentDto getStudentById(Long id) {
    Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    return StudentDtoMapper.map(student);
  }

  public StudentDto saveStudent(StudentRegistrationDto studentRegistrationDto) {
    Student student = StudentDtoMapper.map(studentRegistrationDto);
    Student savedStudent = studentRepository.save(student);
    return StudentDtoMapper.map(savedStudent);
  }

  public StudentDto replaceStudent(StudentRegistrationDto studentRegistrationDto, Long id) {
    Student student1 = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    Student student = StudentDtoMapper.map(studentRegistrationDto);
    student.setId(id);
    Student savedStudent = studentRepository.save(student);
    return StudentDtoMapper.map(savedStudent);
  }

  public void updateStudent(StudentUpdateDto studentUpdateDto) {
    Student studentToSave = StudentDtoMapper.map(studentUpdateDto);
    studentToSave.setId(studentUpdateDto.getId());
    studentRepository.save(studentToSave);
  }

}
