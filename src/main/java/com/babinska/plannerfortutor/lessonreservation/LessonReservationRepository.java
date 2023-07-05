package com.babinska.plannerfortutor.lessonreservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface LessonReservationRepository extends JpaRepository<LessonReservation, Long> {

  @Query(value = "SELECT SUM(price) FROM lesson_reservation WHERE start_time LIKE CONCAT(:date,'%');",
          nativeQuery = true)
  BigDecimal findDailySalary(@Param("date" ) LocalDate date);

  @Query(value = "SELECT SUM(price) FROM lesson_reservation WHERE monthname(start_time) = :month AND YEAR(start_time) = :year ;",
          nativeQuery = true)
  BigDecimal findMonthlySalary(@Param("month" ) String month, @Param("year" ) int year);

  @Query(value = "SELECT SUM(duration_in_minutes) FROM lesson_reservation WHERE monthname(start_time) = :month AND YEAR(start_time) = :year ;",
          nativeQuery = true)
  Integer findSumOfMinutesPerMonth(@Param("month" ) String month, @Param("year" ) int year);

  @Query(value = "SELECT SUM(duration_in_minutes) FROM lesson_reservation WHERE start_time BETWEEN :start AND :end ;",
          nativeQuery = true)
  Integer findSumOfMinutesBetweenDates(@Param("start" ) LocalDateTime start, @Param("end" ) LocalDateTime end);

  @Query(value = "SELECT SUM(price) FROM lesson_reservation WHERE start_time BETWEEN :start AND :end ;",
          nativeQuery = true)
  BigDecimal findSalaryBetweenDates(@Param("start" ) LocalDateTime start, @Param("end" ) LocalDateTime end);

  @Query(value = "SELECT id FROM lesson_reservation WHERE start_time LIKE CONCAT(:date,'%') ;",
          nativeQuery = true)
  Set<Long> findLessonReservationByStartDate(@Param("date" ) LocalDate date);

  @Query(value = "SELECT students_id FROM lesson_reservation_students WHERE lesson_reservation_id = :id ;",
          nativeQuery = true)
  Set<Long> findStudentIdForTheLessonId(@Param("id" ) Long lessonId);

  @Query("SELECT lr.startTime as startTime, lr.endTime as endTime FROM LessonReservation lr" )
  List<LessonTimeView> getAllLessonTimeData();

}

