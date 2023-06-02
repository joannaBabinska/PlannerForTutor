package com.babinska.PlannerForTutor.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {

  private final LessonRepository lessonRepository;
}
