package com.babinska.PlannerForTutor.daysoff;

import java.time.LocalDate;

interface WorkingDay {

  boolean isWorkingDay(LocalDate date);
}
