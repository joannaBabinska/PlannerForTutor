package com.babinska.PlannerForTutor.statistics;

import com.babinska.PlannerForTutor.constraint.Month;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.Optional;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

  private final StatisticsService statisticsService;

  @GetMapping("/salary/{date}")
  ResponseEntity<SalaryResponse> getDailySalary(@PathVariable LocalDate date) {
    SalaryResponse salaryResponse = statisticsService.getDailySalary(date);
    return ResponseEntity.ok(salaryResponse);
  }

  @GetMapping("/salary/monthly")
  ResponseEntity<SalaryResponse> getMonthlySalary(@Valid @RequestParam(required = false) @Min(2023) Integer year,
                                                  @Valid @RequestParam(required = false) @Month String month) {
    Integer yearFromParameters = createYearFromParameters(year);
    String monthFromParameters = createMonthFromParameters(month);
    SalaryResponse monthlySalary = statisticsService.getMonthlySalary(monthFromParameters, yearFromParameters);
    return ResponseEntity.ok(monthlySalary);

  }
  @GetMapping("/hours/perMonth")
  ResponseEntity<HourResponse> getHoursPerMonth(@RequestParam(required = false) @Month String month,
                                                @RequestParam(required = false) @Min(2023) Integer year){

    String monthFromParameters = createMonthFromParameters(month);
    Integer yearFromParameters = createYearFromParameters(year);
    return ResponseEntity.ok(statisticsService.getHoursPerMonth(monthFromParameters, yearFromParameters));
  }


  private static Integer createYearFromParameters(Integer year) {
    return Optional.ofNullable(year).orElse(Year.now().getValue());

  }

  private static String createMonthFromParameters(String month) {
    return Optional.ofNullable(month).orElse(LocalDate.now().getMonth().toString());
  }
}
