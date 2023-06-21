package com.babinska.PlannerForTutor.lessonoverlap;

import com.babinska.PlannerForTutor.lessonreservation.LessonReservationRepository;
import com.babinska.PlannerForTutor.lessonreservation.LessonTimeView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonOverlapService {

  private final LessonReservationRepository lessonReservationRepository;
  public static final LocalDateTime END_OF_APPLICATION = LocalDateTime.of(2999, 12, 31, 0, 0, 0);
  public static final LocalDateTime START_OF_APPLICATION = LocalDateTime.of(1999, 12, 31, 0, 0, 0);

  public boolean isOverlap(LocalDateTime startTime, LocalDateTime endTime) {

    LocalDateTime dateBeforeStart = getLast(allDates().stream()
            .filter(date -> startTime.isAfter(date) || startTime.isEqual(date)).sorted().toList());
    LocalDateTime dateAfterEnd = allDates().stream()
            .filter(date -> endTime.isBefore(date) || endTime.isEqual(date)).sorted().toList().get(0);

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

  private List<LocalDateTime> getLocalDateTimesEnds() {
    List<LocalDateTime> ends = new java.util.ArrayList<>(lessonReservationRepository.getAllLessonTimeData().stream()
            .map(LessonTimeView::getEndTime).toList());
    ends.add(START_OF_APPLICATION);
    return ends;
  }

  private List<LocalDateTime> getLocalDateTimesStarts() {
    List<LocalDateTime> starts = new ArrayList<>(lessonReservationRepository.getAllLessonTimeData().stream()
            .map(LessonTimeView::getStartTime).toList());
    starts.add(END_OF_APPLICATION);
    return starts;
  }

  private static <T> T getLast(List<T> list) {
    return list != null && !list.isEmpty() ? list.get(list.size() - 1) : null;
  }

}
