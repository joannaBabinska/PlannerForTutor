package com.babinska.PlannerForTutor.holidayclient;

import java.time.LocalDate;

public record Holiday(LocalDate date,
                      String localName) {
}
