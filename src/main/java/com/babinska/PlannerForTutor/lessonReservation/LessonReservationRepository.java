package com.babinska.PlannerForTutor.lessonReservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface LessonReservationRepository extends JpaRepository<LessonReservation,Long> {

  @Query(value = """
          Select sum(price) from lesson_Reservation where start_time LIKE CONCAT(:date,'%');
          """, nativeQuery = true)
  BigDecimal findDailySalary(@Param("date")LocalDate date);
}
