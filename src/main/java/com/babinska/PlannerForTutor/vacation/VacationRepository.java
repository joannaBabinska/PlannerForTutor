package com.babinska.PlannerForTutor.vacation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface VacationRepository extends JpaRepository<Vacation,Long> {

  boolean existsByVacationDay(LocalDate date);
}
