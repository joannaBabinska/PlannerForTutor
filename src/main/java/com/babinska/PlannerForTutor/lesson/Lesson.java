package com.babinska.PlannerForTutor.lesson;

import com.babinska.PlannerForTutor.student.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Set;

@Entity
@Data
public class Lesson {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String topic;
  private LessonType lessonType;
  private Set<Student> students;
  private Planner lessonTime;
  private int price;
}
