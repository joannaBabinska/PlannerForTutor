package com.babinska.plannerfortutor.lessonreservation;

import com.babinska.plannerfortutor.aspect.TrackExecutionTime;
import com.babinska.plannerfortutor.validation.Validator;
import com.babinska.plannerfortutor.exception.LessonReservationNotFoundException;
import com.babinska.plannerfortutor.exception.StudentAlreadyAddedToLessonException;
import com.babinska.plannerfortutor.exception.StudentNotFoundException;
import com.babinska.plannerfortutor.lessonreservation.dto.LessonReservationDto;
import com.babinska.plannerfortutor.lessonreservation.dto.LessonReservationRegistrationDto;
import com.babinska.plannerfortutor.lessonreservation.dto.LessonReservationStudentDto;
import com.babinska.plannerfortutor.student.Student;
import com.babinska.plannerfortutor.student.StudentMapper;
import com.babinska.plannerfortutor.student.StudentRepository;
import com.babinska.plannerfortutor.student.dto.StudentRegistrationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@TrackExecutionTime
public class LessonReservationService {

  private final LessonReservationRepository lessonReservationRepository;
  private final ObjectMapper objectMapper;
  private final StudentRepository studentRepository;
  private final Validator validator;

  public LessonReservationDto getLessonReservationById(Long id) {
    LessonReservation lessonReservation = lessonReservationRepository.findById(id).orElseThrow(() -> new LessonReservationNotFoundException(id));
    return LessonReservationMapper.map(lessonReservation);
  }

  public LessonReservationStudentDto getAllInformation(Long id) {
    LessonReservation lessonReservation = lessonReservationRepository.findById(id).orElseThrow(() -> new LessonReservationNotFoundException(id));
    return LessonReservationMapper.mapToLessonReservationStudentDto(lessonReservation);
  }

  public LessonReservationDto addLessonReservation(LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    validator.validate(lessonReservationRegistrationDto);
    LessonReservation lessonReservation = LessonReservationMapper.mapToLessonReservation(lessonReservationRegistrationDto);
    LessonReservation savedLessonReservation = lessonReservationRepository.save(lessonReservation);
    return LessonReservationMapper.map(savedLessonReservation);
  }

  public void deleteLessonReservation(Long id) {
    lessonReservationRepository.deleteById(id);
  }

  public LessonReservationDto replaceLessonReservation
          (Long id, LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    validator.validate(lessonReservationRegistrationDto);
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
    LessonReservation lessonReservation = lessonReservationRepository.findById(id).orElseThrow(() -> new LessonReservationNotFoundException(id));
    Student newStudent = applyPatch(jsonMergePatch);

    lessonReservation.getStudents().add(newStudent);
    LessonReservation savedLessonReservation = lessonReservationRepository.save(lessonReservation);

    return LessonReservationMapper.mapToLessonReservationStudentDto(savedLessonReservation);
  }

  public LessonReservationStudentDto addStudentToLessonReservation(Long lessonId, Long studentId) {
    Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
    LessonReservation lessonReservation = lessonReservationRepository.findById(lessonId).orElseThrow(() -> new LessonReservationNotFoundException(lessonId));

    if (studentAlreadyExistInLesson(studentId, lessonReservation)) {
      throw new StudentAlreadyAddedToLessonException(studentId);
    }
    lessonReservation.getStudents().add(student);
    lessonReservationRepository.save(lessonReservation);

    return LessonReservationMapper.mapToLessonReservationStudentDto(lessonReservation);
  }

  public Set<String> getStudentForTheDay(LocalDate date) {
    Set<Long> lessonReservationsId = lessonReservationRepository.findLessonReservationByStartDate(date);
    return lessonReservationsId.stream()
            .map(lessonReservationRepository::findStudentIdForTheLessonId)
            .flatMap(Collection::stream)
            .map(studentId ->studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId)))
            .map(student -> String.format("%s %s", student.getFirstName(), student.getLastName()))
            .collect(Collectors.toSet());
  }

  public LessonReservationStudentDto deleteStudentFromLessonReservation(Long lessonId, Long studentId) {
    LessonReservation lessonReservation = lessonReservationRepository.findById(lessonId).orElseThrow(() -> new LessonReservationNotFoundException(lessonId));
    Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
    lessonReservation.getStudents().remove(student);
    lessonReservationRepository.save(lessonReservation);
    return LessonReservationMapper.mapToLessonReservationStudentDto(lessonReservation);
  }

  private static boolean studentAlreadyExistInLesson(Long studentId, LessonReservation lessonReservation) {
    return lessonReservation.getStudents().stream()
            .anyMatch(savedStudent -> savedStudent.getId().equals(studentId));
  }

  private LessonReservation applyPatch(LessonReservationRegistrationDto lessonReservationRegistrationDto, JsonMergePatch jsonMergePatch)
          throws JsonPatchException, JsonProcessingException {
    JsonNode jsonNode = objectMapper.valueToTree(lessonReservationRegistrationDto);
    JsonNode applyPath = jsonMergePatch.apply(jsonNode);
    LessonReservationRegistrationDto updatedLessonReservation = objectMapper.treeToValue(applyPath, LessonReservationRegistrationDto.class);
    LessonReservation lessonReservation = LessonReservationMapper.mapToLessonReservation(updatedLessonReservation);
    lessonReservation.setDurationInMinutes(calculateDuration(lessonReservation.getEndTime().toLocalTime(),
            lessonReservation.getStartTime().toLocalTime()));
    return lessonReservation;
  }

  private Student applyPatch( JsonMergePatch jsonMergePatch)
          throws JsonPatchException, JsonProcessingException {
    Student student = new Student();
    StudentRegistrationDto studentRegistrationDto = StudentMapper.mapToStudentRegistrationDto(student);
    JsonNode jsonNode = objectMapper.valueToTree(studentRegistrationDto);
    JsonNode applyPath = jsonMergePatch.apply(jsonNode);
    return objectMapper.treeToValue(applyPath, Student.class);
  }

  private int calculateDuration(LocalTime endTime, LocalTime startTime) {
    return (int) Duration.between(startTime, endTime).toMinutes();
  }

}


