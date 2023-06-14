package com.babinska.PlannerForTutor.statistics;

import com.babinska.PlannerForTutor.lessonReservation.LessonReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StatisticsService {

  private final LessonReservationRepository lessonReservationRepository;

  public FinalResponse getDailySalary(LocalDate date) {
    BigDecimal dailySalary = lessonReservationRepository.findDailySalary(date);
    return new FinalResponse(dailySalary, date.toString());
  }

  public FinalResponse getMonthlySalary(String month, int year) {
    BigDecimal monthlySalary = lessonReservationRepository.findMonthlySalary(month,year);
    return new FinalResponse(monthlySalary,month + " " + year);
  }
}
