package com.babinska.PlannerForTutor;

import com.babinska.PlannerForTutor.lessonoverlap.LessonOverlapService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class Test /*implements CommandLineRunner*/ {
  public static LessonOverlapService lessonOverlapService;
//  @Override
//  public void run(String... args) throws Exception {
//    boolean overlap = lessonOverlapService.isOverlap(LocalDateTime.of(2023, 01, 06, 10, 20, 0),
//            LocalDateTime.of(2023, 01, 06, 11, 20, 0));
//    System.out.println(overlap);
//  }
}
