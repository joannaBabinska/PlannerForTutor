package com.babinska.plannerfortutor;

import com.babinska.plannerfortutor.holidayclient.RestClientHolidayClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class PlannerForTutorApplication {

  private final RestClientHolidayClient restClientHolidayClient;

  public static void main(String[] args) {
    SpringApplication.run(PlannerForTutorApplication.class, args);
  }

  @PostConstruct
  void test() {
    restClientHolidayClient.getHolidays()
        .forEach(System.out::println);
  }

}
