package com.babinska.PlannerForTutor.daysoff;

import com.babinska.PlannerForTutor.vacation.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
class VacationRule implements WorkingDay{

  private final VacationRepository vacationRepository;

  @Override
  public boolean isWorkingDay(LocalDate date) {
    return !vacationRepository.existsByVacationDay(date);
  }
}
