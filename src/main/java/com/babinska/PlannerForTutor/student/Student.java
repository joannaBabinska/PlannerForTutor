package com.babinska.PlannerForTutor.student;

import com.babinska.PlannerForTutor.lesson.Lesson;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private LocalDate dateOfBirth;
  @ManyToMany
  private Set<Lesson> lessons;
  @Enumerated(EnumType.STRING)
  private SchoolClass schoolClass;

}
