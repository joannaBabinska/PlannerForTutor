package com.babinska.plannerfortutor.lessonreservation;

import com.babinska.plannerfortutor.aspect.TrackExecutionTime;
import com.babinska.plannerfortutor.lessonreservation.dto.LessonReservationDto;
import com.babinska.plannerfortutor.lessonreservation.dto.LessonReservationRegistrationDto;
import com.babinska.plannerfortutor.lessonreservation.dto.LessonReservationStudentsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.time.LocalDate;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/lessons-reservation" )
@RequiredArgsConstructor
@TrackExecutionTime
public class LessonReservationController {

  public final LessonReservationService lessonReservationService;

  @PreAuthorize("hasPermission(user,read)")
  @GetMapping("/{id}" )
  ResponseEntity<LessonReservationDto> getLessonReservationById(@PathVariable Long id) {
    LessonReservationDto lessonReservationDto = lessonReservationService.getLessonReservationById(id);
    return ResponseEntity.ok(lessonReservationDto);
  }

  @PreAuthorize("hasPermission('user:read')")
  @GetMapping("/{id}/all" )
  ResponseEntity<LessonReservationStudentsDto> getAllLessonReservationInformation(@PathVariable Long id) {
    LessonReservationStudentsDto lessonReservationAllInformation = lessonReservationService.getAllInformation(id);
    return ResponseEntity.ok(lessonReservationAllInformation);
  }

  @PreAuthorize("hasPermission('admin:read')")
  @GetMapping("/students" )
  ResponseEntity<Set<String>> getStudentForTheDay(@RequestParam LocalDate date) {
    Set<String> students = lessonReservationService.getStudentForTheDay(date);
    return ResponseEntity.ok(students);
  }

  @PostMapping
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  public ResponseEntity<LessonReservationDto> addLessonReservation
          (@Valid @RequestBody LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    LessonReservationDto lessonReservationDto = lessonReservationService.addLessonReservation(lessonReservationRegistrationDto);

    URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}" )
            .buildAndExpand(lessonReservationDto.id())
            .toUri();

    log.info("Add new lesson reservation: {}", lessonReservationDto);
    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}" )
  @PreAuthorize("hasPermission('admin:update')")
  public ResponseEntity<LessonReservationDto> replaceLessonReservation
          (@PathVariable Long id, @Valid @RequestBody LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    LessonReservationDto savedLessonReservationDto = lessonReservationService.replaceLessonReservation(id, lessonReservationRegistrationDto);
    log.info("Replace lesson reservation with id = {} to lesson reservation: {}", id, savedLessonReservationDto);
    return ResponseEntity.ok(savedLessonReservationDto);
  }

  @PatchMapping("/{id}" )
  public ResponseEntity<LessonReservationDto> updateLessonReservation(@PathVariable Long id,
                                                                      @Valid @RequestBody JsonMergePatch jsonMergePatch) throws JsonPatchException, JsonProcessingException {
    LessonReservationDto lessonReservationDto = lessonReservationService.updateLessonReservation(id, jsonMergePatch);
    log.info("Updated lesson reservation with id ={}", id);
    return ResponseEntity.ok(lessonReservationDto);
  }

  @PatchMapping("/{id}/students" )
  public ResponseEntity<LessonReservationStudentsDto> addNewStudentToLessonReservation(@PathVariable Long id,
                                                                                       @RequestBody JsonMergePatch jsonMergePatch) throws JsonPatchException, JsonProcessingException {
    LessonReservationStudentsDto lessonReservationStudentsDto = lessonReservationService.addNewStudentToLessonReservation(id, jsonMergePatch);
    return ResponseEntity.ok(lessonReservationStudentsDto);
  }

  @PatchMapping("/{lessonId}/students/{studentId}" )
  public ResponseEntity<LessonReservationStudentsDto> addStudentToLessonReservation(@PathVariable Long lessonId,
                                                                                    @PathVariable Long studentId) {
    LessonReservationStudentsDto lessonReservationStudentsDto = lessonReservationService.addStudentToLessonReservation(lessonId, studentId);
    return ResponseEntity.ok(lessonReservationStudentsDto);
  }

  @DeleteMapping("/{id}" )
  public ResponseEntity<?> deleteLessonReservation(@PathVariable Long id) {
    lessonReservationService.deleteLessonReservation(id);
    return ResponseEntity.ok().build();
  }


  @DeleteMapping("/{lessonId}/student/{studentId}" )
  public ResponseEntity<LessonReservationStudentsDto> deleteStudentFromLessonReservation(@PathVariable Long lessonId,
                                                                                         @PathVariable Long studentId) {
    LessonReservationStudentsDto lessonReservationStudentsDto = lessonReservationService.deleteStudentFromLessonReservation(lessonId, studentId);
    return ResponseEntity.ok(lessonReservationStudentsDto);
  }

}
