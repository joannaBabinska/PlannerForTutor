package com.babinska.PlannerForTutor.daysoff;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DaysOffService {

  private final List<WorkingDay> workingDayRules;

  public boolean isDayWorking(LocalDate date) {
    return workingDayRules.stream()
            .allMatch(rule -> rule.isWorkingDay(date));
  }
}
