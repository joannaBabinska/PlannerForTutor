package com.babinska.PlannerForTutor.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface StudentRepository extends JpaRepository<Student,Long> {

  @Query(value = "SELECT lesson_reservation_id FROM lesson_reservation_students WHERE students_id = :id ;",
          nativeQuery = true)
  Set<Long> findLessonIdForTheStudentId(@Param("id" ) Long studentId);

}
