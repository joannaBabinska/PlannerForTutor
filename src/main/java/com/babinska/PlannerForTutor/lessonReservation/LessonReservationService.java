package com.babinska.PlannerForTutor.lessonReservation;

import com.babinska.PlannerForTutor.exception.LessonReservationNotFoundException;
import com.babinska.PlannerForTutor.exception.StudentAlreadyAddedToLessonException;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationDto;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationRegistrationDto;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationStudentDto;
import com.babinska.PlannerForTutor.student.Student;
import com.babinska.PlannerForTutor.student.StudentMapper;
import com.babinska.PlannerForTutor.student.StudentService;
import com.babinska.PlannerForTutor.student.dto.StudentDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class LessonReservationService {

  public final LessonReservationRepository lessonReservationRepository;
  public final ObjectMapper objectMapper;
  public final StudentService studentService;

  public LessonReservationDto getLessonReservationById(Long id) {
    LessonReservation lessonReservation = lessonReservationRepository.findById(id).orElseThrow(() -> new LessonReservationNotFoundException(id));
    return LessonReservationMapper.map(lessonReservation);
  }

  public LessonReservationStudentDto getAllInformation(Long id){
    LessonReservation lessonReservation = lessonReservationRepository.findById(id).orElseThrow(() -> new LessonReservationNotFoundException(id));
    return LessonReservationMapper.mapToLessonReservationStudentDto(lessonReservation);
  }

  public LessonReservationDto addLessonReservation(LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    LessonReservation lessonReservation = LessonReservationMapper.mapToLessonReservation(lessonReservationRegistrationDto);
    LessonReservation savedLessonReservation = lessonReservationRepository.save(lessonReservation);
    return LessonReservationMapper.map(savedLessonReservation);
  }

  public void deleteLessonReservation(Long id) {
    lessonReservationRepository.deleteById(id);
  }

  public LessonReservationDto replaceLessonReservation
          (Long id, LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    getLessonReservationById(id);
    LessonReservation lessonReservationToSave = LessonReservationMapper.mapToLessonReservation(lessonReservationRegistrationDto);
    lessonReservationToSave.setId(id);
    lessonReservationRepository.save(lessonReservationToSave);
    return LessonReservationMapper.map(lessonReservationToSave);
  }

  public LessonReservationDto updateLessonReservation(Long id, JsonMergePatch jsonMergePatch) throws JsonPatchException, JsonProcessingException {
    LessonReservationDto lessonReservationDto = getLessonReservationById(id);
    LessonReservationRegistrationDto lessonReservationRegistrationDto = LessonReservationMapper.map(lessonReservationDto);
    LessonReservation updatedLessonReservation = applyPatch(lessonReservationRegistrationDto, jsonMergePatch);
    updatedLessonReservation.setId(id);

    LessonReservation savedLessonReservation = lessonReservationRepository.save(updatedLessonReservation);
    return LessonReservationMapper.map(savedLessonReservation);
  }

  public LessonReservationStudentDto addNewStudentToLessonReservation(Long id, JsonMergePatch jsonMergePatch) throws JsonPatchException, JsonProcessingException {
    LessonReservationDto lessonReservationDto = getLessonReservationById(id);
    LessonReservationStudentDto lessonReservationStudentDto = LessonReservationMapper.mapToLessonReservationStudentDto(lessonReservationDto);
    LessonReservation lessonReservation = applyPatch(lessonReservationStudentDto, jsonMergePatch);
    lessonReservation.setId(id);

    lessonReservation.getStudents().
            forEach(student -> studentService.addStudent(StudentMapper.mapToStudentRegistrationDto(student)));
    LessonReservation savedLessonReservation = lessonReservationRepository.save(lessonReservation);

    return LessonReservationMapper.mapToLessonReservationStudentDto(savedLessonReservation);
  }

  public LessonReservationStudentDto addStudentToLessonReservation(Long lessonId, Long studentId) {
    StudentDto studentDto = studentService.getStudentById(studentId);
    Student student = StudentMapper.mapToStudent(studentDto);
    student.setId(studentId);

    LessonReservationStudentDto lessonReservationStudentDto = getAllInformation(lessonId);
    if (lessonReservationStudentDto.students().stream()
            .anyMatch(savedStudent -> savedStudent.getId().equals(studentId))){
      throw new StudentAlreadyAddedToLessonException(studentId);
    }
    lessonReservationStudentDto.students().add(student);
    LessonReservation lessonReservation = LessonReservationMapper.mapToLessonReservation(lessonReservationStudentDto);
    lessonReservationRepository.save(lessonReservation);

    return lessonReservationStudentDto;
  }

  private LessonReservation applyPatch(LessonReservationRegistrationDto lessonReservationRegistrationDto, JsonMergePatch jsonMergePatch)
          throws JsonPatchException, JsonProcessingException {
    JsonNode jsonNode = objectMapper.valueToTree(lessonReservationRegistrationDto);
    JsonNode applyPath = jsonMergePatch.apply(jsonNode);
    LessonReservation lessonReservation = objectMapper.treeToValue(applyPath, LessonReservation.class);
    lessonReservation.setDurationInMinutes(calculateDuration(lessonReservation.getEndTime(), lessonReservation.getStartTime()));
    return lessonReservation;
  }

  private LessonReservation applyPatch(LessonReservationStudentDto lessonReservationStudentDto, JsonMergePatch jsonMergePatch)
          throws JsonPatchException, JsonProcessingException {
    JsonNode jsonNode = objectMapper.valueToTree(lessonReservationStudentDto);
    JsonNode applyPath = jsonMergePatch.apply(jsonNode);
    LessonReservation lessonReservation = objectMapper.treeToValue(applyPath, LessonReservation.class);
    lessonReservation.setDurationInMinutes(calculateDuration(lessonReservation.getEndTime(), lessonReservation.getStartTime()));
    return lessonReservation;
  }

  private int calculateDuration(LocalTime endTime, LocalTime startTime) {
    return (int) Duration.between(startTime, endTime).toMinutes();
  }



}
