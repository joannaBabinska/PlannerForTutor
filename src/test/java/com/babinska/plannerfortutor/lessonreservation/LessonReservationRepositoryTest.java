package com.babinska.plannerfortutor.lessonreservation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LessonReservationRepositoryTest {

  @Autowired
  private LessonReservationRepository lessonReservationRepository;

  @Test
  void shouldSaveLessonReservation(){

    //given
    LessonReservation lessonReservation= new LessonReservation(
            null,
            LessonType.MATURDAY_COURSE,
            null,
            "test",
            LocalDateTime.of(2023, 12, 12, 15, 25),
            LocalDateTime.of(2023, 12, 12, 16, 25),
            60,
            BigDecimal.valueOf(120));

    //when
    lessonReservationRepository.save(lessonReservation);

    //then
    assertThat(lessonReservation.getId()).isGreaterThan(0);

  }

}