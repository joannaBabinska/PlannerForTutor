package com.babinska.PlannerForTutor.lesson;

import com.babinska.PlannerForTutor.Planner;
import com.babinska.PlannerForTutor.student.Student;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
public class Lesson {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String topic;
  @Enumerated(EnumType.STRING)
  private LessonType lessonType;
  @ManyToMany
  private Set<Student> students;
  @OneToOne
  private Planner lessonTime;
  private int price;
}
