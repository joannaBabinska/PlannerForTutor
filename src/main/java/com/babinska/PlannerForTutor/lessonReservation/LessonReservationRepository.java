package com.babinska.PlannerForTutor.lessonReservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface LessonReservationRepository extends JpaRepository<LessonReservation, Long> {

  @Query(value = """
          SELECT SUM(price) FROM lesson_reservation WHERE start_time LIKE CONCAT(:date,'%');
          """, nativeQuery = true)
  BigDecimal findDailySalary(@Param("date") LocalDate date);

  @Query(value = """
          SELECT SUM(price) FROM lesson_reservation WHERE MONTHNAME(start_time) = :month AND YEAR(start_time) = :year ;
          """,nativeQuery = true)
  BigDecimal findMonthlySalary(@Param("month") String month,@Param("year") int year);

  @Query(value = """
          SELECT SUM(duration_in_minutes) FROM lesson_reservation WHERE MONTHNAME(start_time) = :month AND YEAR(start_time) = :year ;
          """,nativeQuery = true)
  Integer findSumOfMinutesPerMonth(@Param("month") String month,@Param("year") int year);
}
