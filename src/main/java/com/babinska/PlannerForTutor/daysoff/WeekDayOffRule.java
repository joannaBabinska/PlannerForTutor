package com.babinska.PlannerForTutor.daysoff;

import com.babinska.PlannerForTutor.PlannerProperties;
import com.babinska.PlannerForTutor.exception.DayIsNotWorkingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
class WeekDayOffRule implements WorkingDay {

  private final PlannerProperties plannerProperties;

  @Override
  public void isWorkingDay(LocalDate date) {
    plannerProperties.getWeekDaysOff().stream()
            .filter(day -> day == date.getDayOfWeek())
            .findFirst()
            .ifPresent(day -> {throw new DayIsNotWorkingException(
                    String.format("%s is not working day, because is %s",date,date.getDayOfWeek()));});
  }

}
