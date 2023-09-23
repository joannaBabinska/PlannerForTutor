package com.babinska.plannerfortutor.lessonreservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LessonReservationRepositoryTest {

  @Autowired
  private LessonReservationRepository lessonReservationRepository;

  LessonReservation lessonReservation;

  @BeforeEach
  void saveLessonReservation() {

    lessonReservation = new LessonReservation(
            null,
            LessonType.MATURDAY_COURSE,
            null,
            "test",
            LocalDateTime.of(2023, 12, 12, 15, 25),
            LocalDateTime.of(2023, 12, 12, 16, 25),
            60,
            BigDecimal.valueOf(120));

    lessonReservationRepository.save(lessonReservation);

  }

  @Test
  void shouldSaveLessonReservation() {

    //then
    assertThat(lessonReservation.getId()).isGreaterThan(0);

  }

  @Test
  void shouldFindLessonReservationById() {

    //when
    Long id = lessonReservation.getId();

    //then
    assertThat(lessonReservationRepository.findById(id).get()).isEqualTo(lessonReservation);

  }

  @Test
  void shouldDeleteLessonReservation() {

    Long id = lessonReservation.getId();
    lessonReservationRepository.deleteById(id);

    //then
    assertThat(lessonReservationRepository.findById(id)).isEqualTo(Optional.empty());

  }

  @Test
  void shouldFindLessonReservationByStartDate() {

    //when
    Set<Long> lessonReservationByStartDate = lessonReservationRepository.findLessonReservationByStartDate(LocalDate.of(2023, 12, 12));

    //then
    assertThat(lessonReservationByStartDate.size()).isGreaterThan(0);

  }

}