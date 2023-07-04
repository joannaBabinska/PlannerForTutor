package com.babinska.PlannerForTutor.daysoff;

import com.babinska.PlannerForTutor.PlannerProperties;
import com.babinska.PlannerForTutor.exception.DayIsNotWorkingException;
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
  public void isWorkingDay(LocalDate date) {

    Set<Holiday> holidays = Set.of(restTemplate.getForObject(plannerProperties.getUrlHoliday(), Holiday[].class));
    log.debug("Downloaded holidays: {}", holidays);
    holidays.stream()
            .map(Holiday::date)
            .filter(holidayDate -> holidayDate.isEqual(date))
            .findFirst()
            .ifPresent(day -> {
              throw new DayIsNotWorkingException(
                      String.format("%s is not working day, because is \"%s\" ", date,
                              getHolidayName(date, holidays)));
            });
  }

  private static String getHolidayName(LocalDate date, Set<Holiday> holidays) {
    return holidays.stream()
            .filter(holiday -> holiday.date().isEqual(date))
            .map(Holiday::localName)
            .findFirst()
            .orElse("No name holiday");
  }
}
