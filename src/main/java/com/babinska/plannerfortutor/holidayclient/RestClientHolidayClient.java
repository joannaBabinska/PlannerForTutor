package com.babinska.plannerfortutor.holidayclient;

import com.babinska.plannerfortutor.configuration.PlannerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "holidayClient", name = "type", havingValue = "rest-client")
public class RestClientHolidayClient implements HolidayClient {

  private final RestClient restClient;
  private final PlannerProperties plannerProperties;

  @Override
  public Set<Holiday> getHolidays() {
    final Holiday[] response = restClient.get()
        .uri(plannerProperties.getHolidayApiUrl())
        .retrieve()
        .body(Holiday[].class);
    return Arrays.stream(response)
        .collect(Collectors.toSet());
  }

}
