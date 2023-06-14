package com.babinska.PlannerForTutor.statistics;

import com.babinska.PlannerForTutor.constraint.Month;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
@Validated
public class StatisticsController {

  private final StatisticsService statisticsService;

  @GetMapping("/salary/{date}")
  ResponseEntity<SalaryResponse> getDailySalary(@PathVariable LocalDate date) {
    SalaryResponse salaryResponse = statisticsService.getDailySalary(date);
    return ResponseEntity.ok(salaryResponse);
  }

  @GetMapping("/salary/monthly")
  ResponseEntity<SalaryResponse> getMonthlySalary(@RequestParam(required = false) @Min(2023) Integer year,
                                                  @RequestParam(required = false) @Month String month) {
    Integer yearFromParameters = createYearFromParameters(year);
    String monthFromParameters = createMonthFromParameters(month);
    SalaryResponse monthlySalary = statisticsService.getMonthlySalary(monthFromParameters, yearFromParameters);
    return ResponseEntity.ok(monthlySalary);
  }

  @GetMapping("/salary/perTerm")
  ResponseEntity<SalaryResponse> getSalaryPerTerm(@RequestParam LocalDate start,
                                                  @RequestParam(required = false) LocalDate end) {
    LocalDate endFromParameters = createEndDateFromParameters(end);
    return ResponseEntity.ok(statisticsService.getSalaryPerTerm(start, endFromParameters));
  }

  @GetMapping("/hours/perMonth")
  ResponseEntity<HourResponse> getHoursPerMonth(@RequestParam(required = false) @Month String month,
                                                @RequestParam(required = false) @Min(2023) Integer year) {

    String monthFromParameters = createMonthFromParameters(month);
    Integer yearFromParameters = createYearFromParameters(year);
    return ResponseEntity.ok(statisticsService.getHoursPerMonth(monthFromParameters, yearFromParameters));
  }

  @GetMapping("/hours/perTerm")
  ResponseEntity<HourResponse> getHoursPerTerm(@RequestParam LocalDate start,
                                               @RequestParam(required = false) LocalDate end) {
    LocalDate endFromParameters = createEndDateFromParameters(end);
    return ResponseEntity.ok(statisticsService.getHoursPerTerm(start, endFromParameters));
  }

  private LocalDate createEndDateFromParameters(LocalDate end) {
    return Optional.ofNullable(end).orElse(LocalDate.now());
  }

  private static Integer createYearFromParameters(Integer year) {
    return Optional.ofNullable(year).orElse(Year.now().getValue());
  }

  private static String createMonthFromParameters(String month) {
    return Optional.ofNullable(month).orElse(LocalDate.now().getMonth().toString());
  }
}
