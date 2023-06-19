package com.babinska.PlannerForTutor.daysoff;

import com.babinska.PlannerForTutor.PlannerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
class WeekDayOffRule implements WorkingDay {

  private final PlannerProperties plannerProperties;

  @Override
  public boolean isWorkingDay(LocalDate date) {
    return plannerProperties.getWeekDaysOff().stream()
            .noneMatch(day -> day == date.getDayOfWeek());
  }
}
