package com.babinska.PlannerForTutor.statistics;

import com.babinska.PlannerForTutor.constraint.Month;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
  ResponseEntity<FinalResponse> getDailySalary(@PathVariable LocalDate date) {
    FinalResponse finalResponse = statisticsService.getDailySalary(date);
    return ResponseEntity.ok(finalResponse);
  }

  @GetMapping("/salary/monthly")
  ResponseEntity<FinalResponse> getMonthlySalary(@Valid @RequestParam(required = false) @Min(2023) Integer year,
                                                 @Valid @RequestParam(required = false) @Month String month) {
    Integer yearFromParameters = createYearFromParameters(year);
    String monthFromParameters = createMonthFromParameters(month);
    FinalResponse monthlySalary = statisticsService.getMonthlySalary(monthFromParameters, yearFromParameters);
    return ResponseEntity.ok(monthlySalary);

  }

  private static Integer createYearFromParameters(Integer year) {
    return Optional.ofNullable(year).orElse(Year.now().getValue());

  }

  private static String createMonthFromParameters(String month) {
    return Optional.ofNullable(month).orElse(LocalDate.now().getMonth().toString());
  }
}
