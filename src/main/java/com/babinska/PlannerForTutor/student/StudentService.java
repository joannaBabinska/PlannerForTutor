package com.babinska.PlannerForTutor.student;

import com.babinska.PlannerForTutor.exception.StudentNotFoundException;
import com.babinska.PlannerForTutor.student.dto.StudentDto;
import com.babinska.PlannerForTutor.student.dto.StudentMapper;
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
    return StudentMapper.map(student);
  }

  public StudentDto saveStudent(StudentRegistrationDto studentRegistrationDto) {
    Student student = StudentMapper.map(studentRegistrationDto);
    Student savedStudent = studentRepository.save(student);
    return StudentMapper.map(savedStudent);
  }

  public StudentDto replaceStudent(StudentRegistrationDto studentRegistrationDto, Long id) {
     studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    Student student = StudentMapper.map(studentRegistrationDto);
    student.setId(id);
    Student savedStudent = studentRepository.save(student);
    return StudentMapper.map(savedStudent);
  }

  public void updateStudent(StudentUpdateDto studentUpdateDto) {
    studentRepository.findById(studentUpdateDto.getId()).orElseThrow(() -> new StudentNotFoundException(studentUpdateDto.getId()));
    Student studentToSave = StudentMapper.map(studentUpdateDto);
    studentToSave.setId(studentUpdateDto.getId());
    studentRepository.save(studentToSave);
  }

  public void deleteStudent(Long id){
    Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    studentRepository.delete(student);
  }

}
