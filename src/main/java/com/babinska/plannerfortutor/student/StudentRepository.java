package com.babinska.plannerfortutor.student;

import com.babinska.plannerfortutor.student.dto.StudentsDownloadsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface StudentRepository extends JpaRepository<Student,Long> {

  @Query(value = "SELECT lesson_reservation_id FROM lesson_reservation_students WHERE students_id = :id ;",
          nativeQuery = true)
  Set<Long> findLessonIdForTheStudentId(@Param("id" ) Long studentId);

  @Query("SELECT s.firstName as firstname, s.lastName as lastname, s.email as email, s.phoneNumber as phoneNumber, s.schoolClass as schoolClass" +
          " FROM Student s")
  List<StudentsDownloadsView> getAllStudentsDownloads();
}
