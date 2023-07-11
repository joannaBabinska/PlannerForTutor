package com.babinska.plannerfortutor.webclient;

import com.babinska.plannerfortutor.holidayclient.Holiday;
import com.babinska.plannerfortutor.holidayclient.HolidayClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
@ConditionalOnProperty(prefix = "holidayClient", name = "type", havingValue = "web-client")
public class RestTemplateWebClient implements HolidayClient {

  private final RestTemplate restTemplate;

  public RestTemplateWebClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public HolidayClient holidayClient2() {
    return new RestTemplateWebClient(restTemplate);
  }

  @Override
  public Set<Holiday> getHolidays() {
    return null;
  }

}
