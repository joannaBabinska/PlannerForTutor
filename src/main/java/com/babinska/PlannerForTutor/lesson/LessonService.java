package com.babinska.PlannerForTutor.lesson;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonService extends JpaRepository<Lesson, Long> {
}
