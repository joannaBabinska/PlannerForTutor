package com.babinska.plannerfortutor.vacation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vacation")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class VacationController {

  private final VacationService vacationService;

  @GetMapping
  ResponseEntity<List<Vacation>> getVacationDate(){
  return ResponseEntity.ok(vacationService.getVacationDates());
  }
}
