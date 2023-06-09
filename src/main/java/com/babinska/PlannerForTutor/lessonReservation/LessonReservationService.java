package com.babinska.PlannerForTutor.lessonReservation;

import com.babinska.PlannerForTutor.exception.LessonReservationNotFoundException;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationDto;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationMapper;
import com.babinska.PlannerForTutor.lessonReservation.dto.LessonReservationRegistrationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class LessonReservationService {

  public final LessonReservationRepository lessonReservationRepository;
  public final ObjectMapper objectMapper;

  public LessonReservationDto getLessonReservationById(Long id) {
    LessonReservation lessonReservation = lessonReservationRepository.findById(id).orElseThrow(() -> new LessonReservationNotFoundException(id));
    return LessonReservationMapper.map(lessonReservation);
  }

  public LessonReservationDto addLessonReservation(LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    LessonReservation lessonReservation = LessonReservationMapper.mapToLessonReservation(lessonReservationRegistrationDto);
    LessonReservation savedLessonReservation = lessonReservationRepository.save(lessonReservation);
    return LessonReservationMapper.map(savedLessonReservation);
  }

  public LessonReservationDto replaceLessonReservation
          (Long id, LessonReservationRegistrationDto lessonReservationRegistrationDto) {
    getLessonReservationById(id);// sprawdzic czy potrzebne moze samo ogarnie
    LessonReservation lessonReservationToSave = LessonReservationMapper.mapToLessonReservation(lessonReservationRegistrationDto);
    lessonReservationRepository.save(lessonReservationToSave);
    return LessonReservationMapper.map(lessonReservationToSave);
  }

  public LessonReservationDto updateLessonReservation(Long id, JsonMergePatch jsonMergePatch) throws JsonPatchException, JsonProcessingException {
    LessonReservationDto lessonReservationDto = getLessonReservationById(id);
    LessonReservationRegistrationDto lessonReservationRegistrationDto = LessonReservationMapper.map(lessonReservationDto);
    LessonReservation updatedLessonReservation = applyPath(lessonReservationRegistrationDto, jsonMergePatch);
    updatedLessonReservation.setId(id);
    LessonReservation savedLessonReservation = lessonReservationRepository.save(updatedLessonReservation);
    return LessonReservationMapper.map(savedLessonReservation);
  }

  private LessonReservation applyPath(LessonReservationRegistrationDto lessonReservationRegistrationDto, JsonMergePatch jsonMergePatch)
          throws JsonPatchException, JsonProcessingException {
    JsonNode jsonNode = objectMapper.valueToTree(lessonReservationRegistrationDto);
    JsonNode applyPath = jsonMergePatch.apply(jsonNode);
    LessonReservation lessonReservation = objectMapper.treeToValue(applyPath, LessonReservation.class);
    lessonReservation.setDurationInMinutes(calculateDuration(lessonReservation.getEndTime(), lessonReservation.getStartTime()));
    return lessonReservation;
  }

  private int calculateDuration(LocalTime endTime, LocalTime startTime) {
    return (int) Duration.between(startTime, endTime).toMinutes();
  }
}
