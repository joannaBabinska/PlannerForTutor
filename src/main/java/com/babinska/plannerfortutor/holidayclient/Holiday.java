package com.babinska.plannerfortutor.holidayclient;

import java.time.LocalDate;

public record Holiday(LocalDate date,
                      String localName) {
}
