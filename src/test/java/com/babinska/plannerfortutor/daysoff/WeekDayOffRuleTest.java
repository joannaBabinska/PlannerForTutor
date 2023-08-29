package com.babinska.plannerfortutor.daysoff;

import com.babinska.plannerfortutor.configuration.PlannerProperties;
import com.babinska.plannerfortutor.exception.DayIsNotWorkingException;
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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    DayIsNotWorkingException exception = assertThrows(DayIsNotWorkingException.class,
        () -> weekDayOffRule.isWorkingDay(date));
    //then
    String expectedMessage = "2023-06-18 is not working day, because is SUNDAY";
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  void shouldReturnTrueForMonday() {
    //given
    LocalDate date = LocalDate.of(2023, 6, 19);
    //expect
    assertDoesNotThrow(() -> weekDayOffRule.isWorkingDay(date));
  }

}