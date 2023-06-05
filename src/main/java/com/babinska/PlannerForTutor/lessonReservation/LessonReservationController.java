package com.babinska.PlannerForTutor.lessonReservation;

import com.babinska.PlannerForTutor.exception.LessonReservationNotFoundException;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationDto;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @ExceptionHandler(LessonReservationNotFoundException.class)
  public ResponseEntity<?> handle(LessonReservationNotFoundException ex) {
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<LessonReservationDto> addLessonReservation
          (@RequestBody LessonReservationRegistrationDto lessonReservationRegistrationDto){
    LessonReservationDto lessonReservationDto = lessonReservationService.addLessonReservation(lessonReservationRegistrationDto);
    return ResponseEntity.ok(lessonReservationDto);
  }


}
