package com.babinska.PlannerForTutor.daysoff;

import com.babinska.PlannerForTutor.PlannerProperties;
import com.babinska.PlannerForTutor.holidayclient.Holiday;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
class HolidayRule implements WorkingDay {

  private final RestTemplate restTemplate;
  private final PlannerProperties plannerProperties;

  @Override
  public boolean isWorkingDay(LocalDate date) {

    Set<Holiday> holidays = Set.of(restTemplate.getForObject(plannerProperties.getUrlHoliday(), Holiday[].class));
    log.debug("Downloaded holidays: {}", holidays);
    return holidays.stream()
            .map(Holiday::date)
            .noneMatch(holidayDate -> holidayDate.isEqual(date));
  }
}
