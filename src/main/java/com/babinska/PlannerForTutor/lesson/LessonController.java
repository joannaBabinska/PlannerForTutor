package com.babinska.PlannerForTutor.lesson;

import com.babinska.PlannerForTutor.exception.LessonNotFoundException;
import com.babinska.PlannerForTutor.lesson.dto.LessonDto;
import com.babinska.PlannerForTutor.lesson.dto.LessonRegisteredDto;
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

  @PostMapping
  public ResponseEntity<LessonDto> addLesson(@RequestBody LessonRegisteredDto lessonRegisteredDto){
    return ResponseEntity.ok(lessonService.addLesson(lessonRegisteredDto));
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
