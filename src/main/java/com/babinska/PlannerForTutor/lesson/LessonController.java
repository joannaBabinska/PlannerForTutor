package com.babinska.PlannerForTutor.lesson;

import com.babinska.PlannerForTutor.exception.LessonNotFoundException;
import com.babinska.PlannerForTutor.lesson.dto.LessonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {

  private final LessonService lessonService;

  @GetMapping("/{id}")
  public ResponseEntity<LessonDto> getLessonById(@PathVariable Long id){
    return ResponseEntity.ok(lessonService.getLessonById(id));
  }

  @GetMapping
  public ResponseEntity<?> getAllLesson(){
    return ResponseEntity.ok(lessonService.getAllLessons());
  }

  @ExceptionHandler(LessonNotFoundException.class)
  public ResponseEntity<String> handle(LessonNotFoundException ex) {
    return ResponseEntity.notFound().build();
  }
}
