package com.babinska.PlannerForTutor.daysoff;

import com.babinska.PlannerForTutor.PlannerProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class WeekDayOffRuleTest {

  @Mock
  private PlannerProperties plannerProperties;

  @InjectMocks
  private WeekDayOffRule weekDayOffRule;

  @BeforeEach
  public void beforeEach() {
    Mockito.when(plannerProperties.getWeekDaysOff()).thenReturn(Set.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
  }

  @Test
  void shouldReturnFalseForSunday() {
    //given
    LocalDate date = LocalDate.of(2023, 6, 18);
    //when
    boolean isWorkingDay = weekDayOffRule.isWorkingDay(date);
    //then
    assertFalse(isWorkingDay);
  }

  @Test
  void shouldReturnTrueForMonday() {
    //given
    LocalDate date = LocalDate.of(2023, 6, 19);
    //when
    boolean isWorkingDay = weekDayOffRule.isWorkingDay(date);
    //then
    assertTrue(isWorkingDay);
  }

}