package com.babinska.plannerfortutor.vacation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacationService {

  private final VacationRepository vacationRepository;


  public List<Vacation> getVacationDates() {
    return vacationRepository.findAll();
  }
}
