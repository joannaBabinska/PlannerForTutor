package com.babinska.PlannerForTutor.statistics;

import com.babinska.PlannerForTutor.lessonReservation.LessonReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class StatisticsService {

  private final LessonReservationRepository lessonReservationRepository;

  public SalaryResponse getDailySalary(LocalDate date) {
    BigDecimal dailySalary = lessonReservationRepository.findDailySalary(date);
    return new SalaryResponse(dailySalary, date.toString());
  }

  public SalaryResponse getMonthlySalary(String month, Integer year) {
    BigDecimal monthlySalary = lessonReservationRepository.findMonthlySalary(month,year);
    return new SalaryResponse(monthlySalary,month + " " + year);
  }

  public HourResponse getHoursPerMonth(String month, Integer year) {
    Integer sumOfMinutesPerMonth = lessonReservationRepository.findSumOfMinutesPerMonth(month, year);
    String hours = LocalTime.MIN.plus(Duration.ofMinutes(sumOfMinutesPerMonth)).toString();
    return new HourResponse(hours, month + " " + year);
  }
}
