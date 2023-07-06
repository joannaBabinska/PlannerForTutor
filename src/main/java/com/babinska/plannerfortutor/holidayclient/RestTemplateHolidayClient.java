package com.babinska.plannerfortutor.holidayclient;

import com.babinska.plannerfortutor.configuration.PlannerProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Component
public class RestTemplateHolidayClient  implements HolidayClient {

  private final RestTemplate restTemplate;
  private final PlannerProperties plannerProperties;

  public RestTemplateHolidayClient(RestTemplate restTemplate, PlannerProperties plannerProperties) {
    this.restTemplate = restTemplate;
    this.plannerProperties = plannerProperties;
  }

  @Override
  public Set<Holiday> getHolidays() {
    return Set.of(restTemplate.getForObject(plannerProperties.getHolidayApiUrl(), Holiday[].class));
  }
}
