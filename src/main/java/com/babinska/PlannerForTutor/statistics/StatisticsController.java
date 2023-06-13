package com.babinska.PlannerForTutor.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

  private final StatisticsService statisticsService;

  @GetMapping("/salary/{date}")
  ResponseEntity<FinalResponse> getDailySalary(@PathVariable LocalDate date){
    FinalResponse finalResponse = statisticsService.getDailySalary(date);
    return ResponseEntity.ok(finalResponse);
  }
}
