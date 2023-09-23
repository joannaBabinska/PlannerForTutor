package com.babinska.plannerfortutor.lessonreservation;

import com.babinska.plannerfortutor.exception.LessonReservationNotFoundException;
import com.babinska.plannerfortutor.lessonreservation.dto.LessonReservationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LessonReservationServiceTest {

  @InjectMocks
  LessonReservationService lessonReservationService;

  @Mock
  LessonReservationRepository lessonReservationRepository;

  @Test
  void shouldReturnLessonReservation() {

    //given
    LessonReservation lessonReservation = new LessonReservation
            (9999L,
                    LessonType.MATURDAY_COURSE,
                    null,
                    "test",
                    LocalDateTime.of(2023, 12, 12, 15, 25),
                    LocalDateTime.of(2023, 12, 12, 16, 25),
                    60,
                    BigDecimal.valueOf(120));

    //when
    when(lessonReservationRepository.findById(9999L)).thenReturn(Optional.of(lessonReservation));

    //then
    LessonReservationDto lessonReservationById = lessonReservationService.getLessonReservationById(9999L);
    assertNotNull(lessonReservationById);
    assertEquals(lessonReservationById.lessonType(), LessonType.MATURDAY_COURSE);

  }

  @Test
  void shouldReturnLessonReservationNotFoundException() {

    //when
    when(lessonReservationRepository.findById(9999L)).thenReturn(Optional.empty());

    //then
    assertThrows(LessonReservationNotFoundException.class, () -> lessonReservationService.getLessonReservationById(9999L));

  }

}