package com.babinska.PlannerForTutor.lessonoverlap;

import com.babinska.PlannerForTutor.lessonreservation.LessonReservationRepository;
import com.babinska.PlannerForTutor.lessonreservation.LessonTimeView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonOverlapService {

  private final LessonReservationRepository lessonReservationRepository;
  public static final LocalDateTime END_OF_APPLICATION = LocalDateTime.of(2999, 12, 31, 0, 0, 0);
  public static final LocalDateTime START_OF_APPLICATION = LocalDateTime.of(1999, 12, 31, 0, 0, 0);

  public boolean isOverlap(LocalDateTime startTime, LocalDateTime endTime) {

    LocalDateTime dateBeforeStart = allDates().stream()
            .filter(date -> startTime.isAfter(date) || startTime.isEqual(date))
            .max(LocalDateTime::compareTo)
            .orElse(null);

    LocalDateTime dateAfterEnd = allDates().stream()
            .filter(date -> endTime.isBefore(date) || endTime.isEqual(date))
            .min(LocalDateTime::compareTo)
            .orElse(null);

    return !(getLocalDateTimesEnds().contains(dateBeforeStart)
            && !getLocalDateTimesStarts().contains(dateBeforeStart)
            && getLocalDateTimesStarts().contains(dateAfterEnd)
            && !getLocalDateTimesEnds().contains(dateAfterEnd));
  }

  private List<LocalDateTime> allDates() {
    List<LocalDateTime> starts = getLocalDateTimesStarts();
    List<LocalDateTime> ends = getLocalDateTimesEnds();
    starts.addAll(ends);
    return starts;
  }
  private List<LessonTimeView> allLessonTimeData() {
    return lessonReservationRepository.getAllLessonTimeData();
  }

  private List<LocalDateTime> getLocalDateTimesEnds() {

    List<LocalDateTime> ends = allLessonTimeData().stream()
            .map(LessonTimeView::getEndTime)
            .collect(Collectors.toList());
    ends.add(START_OF_APPLICATION);

    return ends;
  }

  private List<LocalDateTime> getLocalDateTimesStarts() {

    List<LocalDateTime> starts = allLessonTimeData().stream()
            .map(LessonTimeView::getStartTime).collect(Collectors.toList());

    starts.add(END_OF_APPLICATION);
    return starts;
  }

}
