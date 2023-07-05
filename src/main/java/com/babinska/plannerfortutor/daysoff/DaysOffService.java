package com.babinska.plannerfortutor.daysoff;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DaysOffService {

  private final List<WorkingDay> workingDayRules;

  public void isDayWorking(LocalDate date) {
         workingDayRules
            .forEach(rule -> rule.isWorkingDay(date));
  }
}
