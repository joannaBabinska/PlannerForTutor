package com.babinska.PlannerForTutor.lesson;

import com.babinska.PlannerForTutor.exception.LessonNotFoundException;
import com.babinska.PlannerForTutor.lesson.dto.LessonDto;
import com.babinska.PlannerForTutor.lesson.dto.LessonMapper;
import com.babinska.PlannerForTutor.lesson.dto.LessonRegisteredDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {

  private final LessonRepository lessonRepository;

  public LessonDto getLessonById(Long id) {
    Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new LessonNotFoundException(id));
    return LessonMapper.map(lesson);
  }

  public List<LessonDto> getAllLessons() {
    List<Lesson> lessonList = lessonRepository.findAll();
    return lessonList.stream().map(LessonMapper::map).collect(Collectors.toList());
  }

  public LessonDto addLesson(LessonRegisteredDto lessonRegisteredDto) {
    Lesson lessonToSave = LessonMapper.map(lessonRegisteredDto);
    Lesson savedLesson = lessonRepository.save(lessonToSave);
    return LessonMapper.map(savedLesson);
  }


}
