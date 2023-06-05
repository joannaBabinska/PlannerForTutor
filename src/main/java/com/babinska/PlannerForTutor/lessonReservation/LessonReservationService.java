package com.babinska.PlannerForTutor.lessonReservation;

import com.babinska.PlannerForTutor.exception.LessonReservationNotFoundException;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationDto;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationMapper;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonReservationService {

  public final LessonReservationRepository lessonReservationRepository;

  public LessonReservationDto getLessonReservationById(Long id){
    LessonReservation lessonReservation = lessonReservationRepository.findById(id).orElseThrow(() -> new LessonReservationNotFoundException(id));
    return LessonReservationMapper.map(lessonReservation);
  }

  public LessonReservationDto addLessonReservation(LessonReservationRegistrationDto lessonReservationRegistrationDto){
    LessonReservation lessonReservation = LessonReservationMapper.mapToLessonReservation(lessonReservationRegistrationDto);
    LessonReservation savedLessonReservation = lessonReservationRepository.save(lessonReservation);
    return LessonReservationMapper.map(savedLessonReservation);
  }

  public LessonReservationDto replaceLessonReservation
          (Long id, LessonReservationRegistrationDto lessonReservationRegistrationDto){
    getLessonReservationById(id);// sprawdzic czy potrzebne moze samo ogarnie
    LessonReservation lessonReservationToSave = LessonReservationMapper.mapToLessonReservation(lessonReservationRegistrationDto);
    lessonReservationRepository.save(lessonReservationToSave);
    return LessonReservationMapper.map(lessonReservationToSave);
  }
}
