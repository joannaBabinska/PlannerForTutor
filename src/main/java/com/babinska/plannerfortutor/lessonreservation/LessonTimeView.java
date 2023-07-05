package com.babinska.plannerfortutor.lessonreservation;

import java.time.LocalDateTime;

public interface LessonTimeView {

  LocalDateTime getStartTime();
  LocalDateTime getEndTime();
}
