package com.babinska.PlannerForTutor.lessonreservation;

import com.babinska.PlannerForTutor.lessonreservation.dto.LessonReservationDto;
import com.babinska.PlannerForTutor.lessonreservation.dto.LessonReservationRegistrationDto;
import com.babinska.PlannerForTutor.lessonreservation.dto.LessonReservationStudentDto;
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
import java.time.LocalDate;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/lessonsReservation")
@RequiredArgsConstructor
public class LessonReservationController {

  public final LessonReservationService lessonReservationService;

  @GetMapping("/{id}")
  ResponseEntity<LessonReservationDto> getLessonReservationById(@PathVariable Long id) {
    LessonReservationDto lessonReservationDto = lessonReservationService.getLessonReservationById(id);
    return ResponseEntity.ok(lessonReservationDto);
  }

  @GetMapping("/{id}/all")
  ResponseEntity<LessonReservationStudentDto> getAllLessonReservationInformation(@PathVariable Long id) {
    LessonReservationStudentDto lessonReservationAllInformation = lessonReservationService.getAllInformation(id);
    return ResponseEntity.ok(lessonReservationAllInformation);
  }

  @PostMapping
  public ResponseEntity<LessonReservationDto> addLessonReservation
          (@Valid @RequestBody LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    LessonReservationDto lessonReservationDto = lessonReservationService.addLessonReservation(lessonReservationRegistrationDto);

    URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(lessonReservationDto.id())
            .toUri();

    log.info("Add new lesson reservation: {}", lessonReservationDto);
    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<LessonReservationDto> replaceLessonReservation
          (@PathVariable Long id, @Valid @RequestBody LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    LessonReservationDto savedLessonReservationDto = lessonReservationService.replaceLessonReservation(id, lessonReservationRegistrationDto);
    log.info("Replace lesson reservation with id = {} to lesson reservation: {}", id, savedLessonReservationDto);
    return ResponseEntity.ok(savedLessonReservationDto);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<LessonReservationDto> updateLessonReservation(@PathVariable Long id,
  @Valid @RequestBody JsonMergePatch jsonMergePatch) throws JsonPatchException, JsonProcessingException {
    LessonReservationDto lessonReservationDto = lessonReservationService.updateLessonReservation(id, jsonMergePatch);
    log.info("Updated lesson reservation with id ={}",id);
    return ResponseEntity.ok(lessonReservationDto);
  }

  @PatchMapping("/{id}/students")
  public ResponseEntity<LessonReservationStudentDto> addNewStudentToLessonReservation(@PathVariable Long id,
  @RequestBody JsonMergePatch jsonMergePatch) throws JsonPatchException, JsonProcessingException {
    LessonReservationStudentDto lessonReservationStudentDto = lessonReservationService.addNewStudentToLessonReservation(id, jsonMergePatch);
    return ResponseEntity.ok(lessonReservationStudentDto);
  }

  @PatchMapping("/{lessonId}/students/{studentId}")
  public ResponseEntity<LessonReservationStudentDto> addStudentToLessonReservation(@PathVariable Long lessonId,
                                                                                   @PathVariable Long studentId) {
    LessonReservationStudentDto lessonReservationStudentDto = lessonReservationService.addStudentToLessonReservation(lessonId, studentId);
    return ResponseEntity.ok(lessonReservationStudentDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteLessonReservation(@PathVariable Long id){
    lessonReservationService.deleteLessonReservation(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("students")
  ResponseEntity<Set<String>> getStudentForTheDay(@RequestParam LocalDate date) {
    Set<String> students = lessonReservationService.getStudentForTheDay(date);
    return ResponseEntity.ok(students);
  }

  @DeleteMapping("/{lessonId}/student/{studentId}")
  public ResponseEntity<LessonReservationStudentDto> deleteStudentFromLessonReservation(@PathVariable Long lessonId,
                                                                                        @PathVariable Long studentId) {
    LessonReservationStudentDto lessonReservationStudentDto = lessonReservationService.deleteStudentFromLessonReservation(lessonId, studentId);
    return ResponseEntity.ok(lessonReservationStudentDto);
  }

  @DeleteMapping("student/{id}")
  public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
    lessonReservationService.deleteStudent(id);
    return ResponseEntity.ok().build();
  }

}
