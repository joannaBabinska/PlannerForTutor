package com.babinska.plannerfortutor.holidayclient;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@ConditionalOnProperty(prefix = "holidayClient", name = "type", havingValue = "web-client")
class WebClientHolidayClient implements HolidayClient {

  @Override
  public Set<Holiday> getHolidays() {
    throw new UnsupportedOperationException("Not implemented yet!"); // TODO implement
  }

}
