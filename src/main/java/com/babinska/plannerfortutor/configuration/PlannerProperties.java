package com.babinska.plannerfortutor.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;
import java.util.Set;

@ConfigurationProperties(prefix = "planner")
@Component
@Getter
@Setter
public class PlannerProperties {

  private Set<DayOfWeek> weekDaysOff;
  private String holidayApiUrl;
}
