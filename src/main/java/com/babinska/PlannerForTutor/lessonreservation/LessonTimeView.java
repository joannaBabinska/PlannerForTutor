package com.babinska.PlannerForTutor.lessonreservation;

import java.time.LocalDateTime;

public interface LessonTimeView {

  LocalDateTime getStartTime();
  LocalDateTime getEndTime();
}
