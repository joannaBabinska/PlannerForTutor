package com.babinska.PlannerForTutor.lessonReservation;

import com.babinska.PlannerForTutor.exception.LessonReservationNotFoundException;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationDto;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationRegistrationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping
  public ResponseEntity<LessonReservationDto> addLessonReservation
          (@Valid @RequestBody LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    LessonReservationDto lessonReservationDto = lessonReservationService.addLessonReservation(lessonReservationRegistrationDto);
    log.info("Add new lesson reservation: {}", lessonReservationDto);
    return ResponseEntity.ok(lessonReservationDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<LessonReservationDto> replaceLessonReservation
          (@PathVariable Long id, @Valid @RequestBody LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    LessonReservationDto savedLessonReservationDto = lessonReservationService.replaceLessonReservation(id, lessonReservationRegistrationDto);
    log.info("Replace lesson reservation with id = {} to lesson reservation: {}", id, savedLessonReservationDto);
    return ResponseEntity.ok(savedLessonReservationDto);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<LessonReservationDto> updateLessonReservation(@PathVariable Long id, @Valid @RequestBody JsonMergePatch jsonMergePatch) throws JsonPatchException, JsonProcessingException {
    LessonReservationDto lessonReservationDto = lessonReservationService.updateLessonReservation(id, jsonMergePatch);
    log.info("Updated lesson reservation with id ={}",id);
    return ResponseEntity.ok(lessonReservationDto);
  }

  @ExceptionHandler(LessonReservationNotFoundException.class)
  public ResponseEntity<?> handle(LessonReservationNotFoundException ex) {
    return ResponseEntity.notFound().build();
  }

}
