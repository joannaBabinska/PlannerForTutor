package com.babinska.plannerfortutor.daysoff;

import com.babinska.plannerfortutor.exception.DayIsNotWorkingException;
import com.babinska.plannerfortutor.vacation.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
class VacationRule implements WorkingDay {

  private final VacationRepository vacationRepository;

  @Override
  public void isWorkingDay(LocalDate date) {
     if(vacationRepository.existsByVacationDay(date)) {
       throw new DayIsNotWorkingException(String.format("%s is employee vacation",date));
     }
  }
}
