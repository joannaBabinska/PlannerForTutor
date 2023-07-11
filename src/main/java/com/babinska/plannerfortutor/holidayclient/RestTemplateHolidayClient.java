package com.babinska.plannerfortutor.holidayclient;

import com.babinska.plannerfortutor.configuration.PlannerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "holidayClient", name = "type", havingValue = "rest-template")
public class RestTemplateHolidayClient implements HolidayClient {

  private final RestTemplate restTemplate;
  private final PlannerProperties plannerProperties;

  public HolidayClient holidayClient() {
    return new RestTemplateHolidayClient(restTemplate, plannerProperties);
  }

  @Override
  public Set<Holiday> getHolidays() {
    return Set.of(restTemplate.getForObject(plannerProperties.getHolidayApiUrl(), Holiday[].class));
  }

}
